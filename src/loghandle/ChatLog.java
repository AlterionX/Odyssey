package loghandle;

import com.sun.javafx.UnmodifiableArrayList;

import java.rmi.UnexpectedException;
import java.util.*;

/**
 * Handles file input for the chatlog being displayed.
 * @author Benjamin Xu
 */
public class ChatLog implements Runnable{
    //Instance specific, non-static, final/unmodifiable
    // (no thread issues for non static methods! Still issues with constructor -- solved with factory methods)
    /**
     * Name of the conversation.
     */
    private final String convoName;
    /**
     * List of speakers
     */
    private final UnmodifiableArrayList<String> speakerList;
    /**
     * Conversation lines
     */
    private final UnmodifiableArrayList<ChatLogEntry> convo;
    //Must use factories
    /**
     * Initialized a new log from a path to a .oclog file.
     * @param convoPath Path to the .oclog file containing the conversation.
     * @throws UnexpectedException Malformatted .oclog file.
     */
    ChatLog(String convoPath) throws UnexpectedException {
        System.out.println("Processing files: " + convoPath);
        List<String> data = ChatLogManager.readAllLinesFromFile(ChatLogManager.getHomeDir(), convoPath);

        //Parse header
        convoName = data.get(0);
        String[] speakerListPre = (data.get(1).split(":")[1] + "\tSystem").split("\t");
        speakerList = new UnmodifiableArrayList<>(speakerListPre, speakerListPre.length);
        int normalTime;
        try {
            normalTime = Integer.parseInt(data.get(2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new UnexpectedException("Bad file format. A number is not found on the third line.");
        }

        //Check for empty line as required by format
        if (data.get(3).length() != 0) {
            throw new UnexpectedException("Bad file format. Lacking line between header and body.");
        }
        ArrayList<ChatLogEntry> entries = new ArrayList<>();
        //Parse non-empty lines
        for (int i = 4; i < data.size(); i++) {
            if (data.get(i).length() != 0) {
                entries.add(new ChatLogEntry(data.get(i), normalTime, speakerList.size() - 1));
            }
        }
        convo = new UnmodifiableArrayList<>(entries.toArray(new ChatLogEntry[]{}), entries.size());
    }

    //These should not change after initialization -- no set
    /**
     * Get the name of the conversation.
     * @return A String of the conversation name.
     */
    public String getConvoName() {
        return convoName;
    }
    /**
     * Get the number of lines within the conversation. (Number of entries)
     * @return The number of entries.
     */
    public int getLineCount() {
        return convo.size();
    }
    /**
     * Get the lined corresponding to the given index.
     * @param i The given index.
     * @return The Entry representing the line.
     */
    public ChatLogEntry getLine(int i) {
        return convo.get(i);
    }
    /**
     * Get the speaker corresponding to a given index.
     * @param i The given index.
     * @return The speaker's name.
     */
    public String getSpeaker(int i) {
        return speakerList.get(i);
    }
    /**
     * Print the name of the speaker with the given index.
     * @param speakerIndex Index of the requested speaker.
     */
    private void printSpeaker(int speakerIndex) {
        System.out.println(speakerList.get(speakerIndex));
    }
    /**
     * Print the conversation with delays in-between the lines, to standard out.
     */
    void printWithDelays() {
        System.out.println("This is a conversation named: " + getConvoName());
        int lastSpeaker = -1;
        for (ChatLogEntry entry: convo) {
            try {
                Thread.sleep(entry.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lastSpeaker != entry.getSpeaker()) {
                lastSpeaker = entry.getSpeaker();
                printSpeaker(lastSpeaker);
            }
            System.out.println(entry.getText());
        }
    }

    //Interface methods
    @Override
    public void run() {
        printWithDelays();
    }
}
