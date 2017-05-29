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
    private List<String> convoList = new ArrayList<>();
    private Map<String, String> nameToPath = new HashMap<>();

    ConversationManager() {
        List<String> stuff = ChatLogManager.getCategoryNames();
        categories = new UnmodifiableArrayList<>(
                stuff.toArray(new String[]{}), stuff.size()
        );
        setConvoListFromCategory(0);
    }

    //For handling scrolling
    private final Object printingManagementLock = new Object();
    private Deque<ChatLog> logSequence = new ArrayDeque<>();
    private int lastSpeaker = -1;
    private int nextEntry = 0;
    private ChatLog nextLog;

    //General category management
    public List<String> setConvoListFromCategory(int category) {
        synchronized (categoryManagementLock) {
            if (getSelectedCategory() != category) {
                convoList.clear();
                setSelectedCategory(category);
                List<String> conversationsPathList = ChatLogManager.getCategoryConvos(categories.get(category));
                for (int i = 0; i < conversationsPathList.size(); i++) {
                    convoList.add(ChatLogManager.getInstance(conversationsPathList.get(i), false).getConvoName());
                    if (!nameToPath.containsKey(convoList.get(i))) {
                        nameToPath.put(conversationsPathList.get(i), convoList.get(i));
                    }
                }
            }
        }
        return convoList;
    }
    public List<String> getConvoList() {
        synchronized (categoryManagementLock) {
            return convoList;
        }
    }
    public void resetCategoryState() {
        setConvoListFromCategory(0);
    }
    private int getSelectedCategory() {
        return selectedCategory;
    }
    private void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    //General feeder management
    void advance() {
        synchronized (printingManagementLock) {
            while (!(hasNextEntry() || nextLog == null)) {
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
    public void resetPrintingState() {
        synchronized (printingManagementLock) {
            logSequence.clear();
            lastSpeaker = -1;
            nextEntry = 0;
            nextLog = null;
        }
    }
}