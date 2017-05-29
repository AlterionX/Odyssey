package loghandle;

import java.rmi.UnexpectedException;

/**
 * A data container for holding a line of a conversation.
 */
public class ChatLogEntry {
    private int delay;
    private int speaker;
    private String line;

    /**
     * Creates an entry of the chatlog
     * @param line The line to print
     * @param basicDelay Default waiting time before the line is printed
     * @param systemInd The index of the system speaker, a narrator of sorts
     * @throws UnexpectedException Incorrect file format. Either the first 2 portions had colons or are not integers
     *                                  or we are missing one or more colons.
     */
    ChatLogEntry(String line, int basicDelay, int systemInd) throws UnexpectedException {
        String[] segments = line.split(":", 3);
        if (segments.length < 3) {
            throw new UnexpectedException("Bad file format. Not enough parts. "
                    + "Line: " + line + ", Parts: " + segments.length);
        }
        if (segments[0].length() == 0) {
            delay = basicDelay;
        } else {
            delay = Integer.parseInt(segments[0]);
        }
        if (segments[1].length() == 0) {
            speaker = systemInd;
        } else {
            speaker = Integer.parseInt(segments[1]);
        }
        this.line = segments[2];
    }

    /**
     * Returns the delay before the requested line should be printed.
     * @return The stored delay.
     */
    public int getDelay() {
        return delay;
    }
    /**
     * Returns the index of the speaker of the text. The list of speakers is located within the ChatLog.
     * @return The stored index of the speaker.
     */
    public int getSpeaker() {
        return speaker;
    }
    /**
     * Returns the text that the entry should display.
     * @return The text "spoken" by the speaker.
     */
    public String getText() {
        return line;
    }

    @Override
    public String toString() {
        return "{Delay:" + getDelay() + ",Speaker:" + getSpeaker() + ",LineText:" + getText()+"}";
    }
}
