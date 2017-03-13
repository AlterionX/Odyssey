package gui;

import loghandle.ChatLog;
import loghandle.ChatLogEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversationManager {

    private static Map<String, String> nameToPath = new HashMap<>();
    private static List<String> categories = ConversationManager.fetchCategories();
    private static List<String> fetchCategories() {
        return ChatLog.getCategoryNames();
    }
    private static int selectedCategory = 0;
    private static List<String> conversations = new ArrayList<>();
    static {
        setCategory(0);
    }

    private static ChatLog currentLog = null;
    private static int lastSpeaker = -1;
    private static ChatLogEntry currentEntry = null;
    private static int nextEntry = 0;

    private ConversationManager() {}

    public static void reset() {
        resetCurrent();
        setCategory(0);
    }
    public static void resetCurrent() {
        currentLog = null;
        lastSpeaker = -1;
        currentEntry = null;
        nextEntry = 0;
    }

    public static List<String> setCategory(int category) {
        if (getSelectedCategory() != category) {
            conversations.clear();
            setSelectedCategory(category);
            List<String> conversationsPathList = ChatLog.getCategoryConvos(categories.get(category));
            for (int i = 0; i < conversationsPathList.size(); i++) {
                conversations.add(ChatLog.getInstance(conversationsPathList.get(i), false).getConvoName());
                if (!nameToPath.containsKey(conversations.get(i))) {
                    nameToPath.put(conversationsPathList.get(i), conversations.get(i));
                }
            }
        }
        return conversations;
    }
    public static int getSelectedCategory() {
        return selectedCategory;
    }
    public static void setSelectedCategory(int selectedCategory) {
        ConversationManager.selectedCategory = selectedCategory;
    }

    public static List<String> getCategoryNames() {
        return categories;
    }
    public static List<String> getCurrentCategoryConversationNames() {
        return conversations;
    }
    public static void getConversationFromName(String name) {
        if (!nameToPath.containsKey(name)) {
            System.out.println("Not loaded.");
            return;
        }
        currentLog = ChatLog.getInstance(nameToPath.get(name), true);
    }

    public static ChatLog getCurrentConversation() {
        return currentLog;
    }

    //Printing and fetching things phase.
    public static void primeLog() {
        if (currentLog == null || currentLog.getLineCount() == 0) {
            throw new IllegalStateException("No conversation set for iteration.");
        }
        fetchNextEntry();
    }
    public void printCurrentAndFindNextWithDelay() {
        //TODO? Might put this into the ChatArea/PrintPanel
    }
    public int getCurrentDelay() {
        return currentEntry.delay();
    }
    public static String getCurrentOuput() {
        StringBuilder sb = new StringBuilder();
        if (lastSpeaker != currentEntry.speaker()) {
            sb.append(currentLog.getSpeaker(currentEntry.speaker()));
        }
        sb.append(currentEntry.text());
        return sb.toString();
    }
    private static void fetchNextEntry() {
        if (currentEntry != null) {
            lastSpeaker = currentEntry.speaker();
        }
        if (nextEntry != currentLog.getLineCount()) {
            currentEntry = currentLog.getLine(nextEntry);
            nextEntry++;
        }
    }
    public static String getCurrentOuputAndFetchNextEntry() {
        String output = getCurrentOuput();
        fetchNextEntry();
        return output;
    }

    //Do something here
} 