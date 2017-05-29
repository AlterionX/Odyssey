package gui;

import com.sun.javafx.UnmodifiableArrayList;
import loghandle.ChatLog;
import loghandle.ChatLogEntry;
import loghandle.ChatLogManager;

import java.util.*;

public final class ConversationManager {
    //List of categories will never change
    private final UnmodifiableArrayList<String> categories;
    public List<String> getCategoryNames() {
        return categories;
    }

    //For modifying selected categories
    private int selectedCategory = -1;
    private final Object categoryManagementLock = new Object();
    private final List<String> convoList = new ArrayList<>();
    private final Map<String, String> nameToPath = new HashMap<>();

    //For handling printing
    private final Object printingManagementLock = new Object();
    private final Deque<ChatLog> logSequence = new ArrayDeque<>();
    private int lastSpeaker = -1;
    private int nextEntry = 0;
    private ChatLog nextLog;

    ConversationManager() {
        List<String> stuff = ChatLogManager.getCategoryNames();
        categories = new UnmodifiableArrayList<>(
                stuff.toArray(new String[]{}), stuff.size()
        );
        setConvoListFromCategory(0);
    }

    //General category management, only one can happen at a time
    public List<String> setConvoListFromCategory(int category) {
        synchronized (categoryManagementLock) {
            if (selectedCategory != category) {
                convoList.clear();
                selectedCategory = category;
                List<String> conversationsPathList = ChatLogManager.getCategoryConvos(categories.get(category));
                for (int i = 0; i < conversationsPathList.size(); i++) {
                    convoList.add(ChatLogManager.getInstance(conversationsPathList.get(i), false).getConvoName());
                    if (!nameToPath.containsKey(convoList.get(i))) {
                        nameToPath.put(convoList.get(i), conversationsPathList.get(i));
                    }
                }
            }
        }
        return convoList;
    }

    //General feeder management, only one can happen at a time
    public void primeLogs(int selectedItem) {
        synchronized (printingManagementLock) {
            if (selectedItem == 0) {
                for (String name : convoList) {
                    System.out.println(nameToPath);
                    if (nameToPath.containsKey(name)) {
                        logSequence.add(ChatLogManager.getInstance(nameToPath.get(name), false));
                    }
                }
            } else {
                //Get only selected item
                logSequence.add(ChatLogManager.getInstance(nameToPath.get(convoList.get(selectedItem)), false));
            }
            advance();
        }
    }
    public void resetPrintingState() {
        synchronized (printingManagementLock) {
            logSequence.clear();
            lastSpeaker = -1;
            nextEntry = 0;
            nextLog = null;
        }
    }
    void advance() {
        synchronized (printingManagementLock) {
            while (hasNextConvo() && (nextLog == null || !hasNextEntry())) {
                nextLog = getNextConvo();
            }
        }
    }
    boolean hasNextEntry() {
        synchronized (printingManagementLock) {
            return nextEntry < nextLog.getLineCount();
        }
    }
    ChatLogEntry getNextEntry() {
        synchronized (printingManagementLock) {
            ChatLogEntry cle = nextLog.getLine(nextEntry++);
            lastSpeaker = cle.getSpeaker();
            return cle;
        }
    }
    //Everything beyond this point should be relatively thread-safe
    private ChatLog getNextConvo() {
        synchronized (printingManagementLock) {
            lastSpeaker = -1;
            return (nextLog = logSequence.poll());
        }
    }
    private boolean hasNextConvo() {
        synchronized (printingManagementLock) {
            return logSequence.peek() != null;
        }
    }

    boolean hasLogs() {
        synchronized (printingManagementLock) {
            return !logSequence.isEmpty() || nextLog != null;
        }
    }
}