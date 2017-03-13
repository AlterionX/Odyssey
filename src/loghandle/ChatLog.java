package loghandle;

import javafx.util.Pair;

import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles file input for the chatlog being displayed.
 * @author Benjamin Xu
 */
public class ChatLog {

    /**
     * List of initialised conversations.
     */
    private static HashMap<String, ChatLog> initializedLogs = new HashMap<>();
    /**
     * The list of all set directories.
     */
    private final static String HOME_DIR = "./logs";
    private final static String RECORDS_FILE = "history.llst";
    private final static String CATEGORIES_FILE = "categories.oplist";

    private static List<String> RECORDS_LIST = fetchRecordList();
    private static List<String> fetchRecordList() {
        List<String> paths = readAllLinesFromFile(HOME_DIR, RECORDS_FILE);
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).length() == 0) {
                paths.remove(i);
                i--;
            }
            try {
                paths.set(i, Paths.get(HOME_DIR).resolve(paths.get(i)).toRealPath().toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        ArrayList<String> prunedPaths = new ArrayList<>();
        for (String path : paths) {
            if (!prunedPaths.contains(path)) {
                prunedPaths.add(path);
            }
        }
        return prunedPaths;
    }
    private static Map<String, Pair<String, List<String>>> CATEGORY_DATA = fetchCategoryList();
    private static Map<String, Pair<String, List<String>>> fetchCategoryList() {
        List<String> categories = readAllLinesFromFile(HOME_DIR, CATEGORIES_FILE);
        LinkedHashMap<String, Pair<String, List<String>>> prunedCategories = new LinkedHashMap<>();
        for (String category : categories) {
            String[] categoryPair = category.split(":");
            if (categoryPair[0].length() != 0 && categoryPair[1].length() != 0
                    && !prunedCategories.containsKey(category)) {
                List<String> categoryConvos = readAllLinesFromFile(HOME_DIR, categoryPair[1]);
                ArrayList<String> prunedCategoryConvos = new ArrayList<>();
                for (String convo: categoryConvos) {
                    if (convo.length() != 0 && !prunedCategoryConvos.contains(convo)) {
                        try {
                            prunedCategoryConvos.add(Paths.get(HOME_DIR).resolve(convo).toRealPath().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                }
                prunedCategories.put(categoryPair[0], new Pair<>(categoryPair[1], prunedCategoryConvos));
            }
        }
        return prunedCategories;
    }

    public static ChatLog getInstance(int logNum) {
        if (logNum < 0) {
            throw new IllegalStateException("Not allowed to access conversation logs of negative numbers.");
        }
        if (logNum >= RECORDS_LIST.size()) {
            throw new IllegalStateException("Not allowed to access conversation logs that do not exist. Obviously.");
        }
        return internalInstanceFetch(RECORDS_LIST.get(logNum));
    }

    public static List<String> getCategoryNames() {
        ArrayList<String> names = new ArrayList<>();
        names.addAll(CATEGORY_DATA.keySet());
        return names;
    }
    public static List<String> getCategoryConvos(String categoryName) {
        return CATEGORY_DATA.get(categoryName).getValue();
    }

    public static int getPathIndex(String path) {
        String realPath = null;
        try {
            realPath = Paths.get(HOME_DIR).resolve(path).toRealPath().toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return RECORDS_LIST.indexOf(realPath);
    }
    public static ChatLog getInstance(String pathPart, boolean includeHome) {
        if (pathPart.length() == 0) {
            throw new IllegalStateException("Not allowed to access conversation logs without a name or path.");
        }
        String path = null;
        try {
            if (includeHome) {
                path = Paths.get(HOME_DIR).resolve(pathPart).toRealPath().toString();
            } else {
                path = Paths.get(pathPart).toRealPath().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if (!RECORDS_LIST.contains(path)) {
            System.out.println("Warning: Attempting to access a strange path: " + path);
            RECORDS_LIST.add(path);
        }
        return internalInstanceFetch(path);
    }
    private static ChatLog internalInstanceFetch(String path) {
        String[] pieces = path.split("\\.");
        if (!pieces[pieces.length - 1].equalsIgnoreCase("oclog")) {
            throw new IllegalStateException("Cannot access logs that do not have the correct file extension.");
        }
        if (!initializedLogs.containsKey(path)) {
            try {
                initializedLogs.put(path, new ChatLog(path));
            } catch (UnexpectedException e) {
                System.out.println("Exception encountered in file read.");
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return initializedLogs.get(path);
    }

    private String convoName;
    private String[] speakerList;
    private ArrayList<ChatLogEntry> convo = new ArrayList<>();

    /**
     * Initialized a new log from a path to a .oclog file.
     * @param convoPath Path to the .oclog file containing
     */
    private ChatLog(String convoPath) throws UnexpectedException {
        System.out.println("Processing files: " + convoPath);
        List<String> data = readAllLinesFromFile(HOME_DIR, convoPath);

        //Parse header
        convoName = data.get(0);
        speakerList = (data.get(1).split(":")[1] + "\tSystem").split("\t");
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
        //Parse non-empty lines
        for (int i = 4; i < data.size(); i++) {
            if (data.get(i).length() != 0) {
                convo.add(new ChatLogEntry(data.get(i), normalTime, speakerList.length - 1));
            }
        }
    }

    public String getConvoName() {
        return convoName;
    }
    public int getLineCount() {
        return convo.size();
    }

    private void printSpeaker(int lastSpeaker) {
        System.out.println(speakerList[lastSpeaker]);
    }
    public void printWithDelays() {
        System.out.println("This is a conversation named: " + getConvoName());
        int lastSpeaker = -1;
        for (ChatLogEntry entry: convo) {
            try {
                Thread.sleep(entry.delay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (lastSpeaker != entry.speaker()) {
                lastSpeaker = entry.speaker();
                printSpeaker(lastSpeaker);
            }
            System.out.println(entry.text());
        }
    }

    public ChatLogEntry getLine(int i) {
        return convo.get(i);
    }
    public String getSpeaker(int i) {
        return speakerList[i];
    }

    /**
     * Helper function to get all the lines from a provided file.
     * @param pathPieces Segments, in order, of the path to the file.
     * @return A list of strings for each of the lines in the file.
     */
    private static List<String> readAllLinesFromFile(String... pathPieces) {
        List<String> lines = null;
        Path p = Paths.get(pathPieces[0]);
        for (int i = 1; i < pathPieces.length; i++) {
            p = p.resolve(pathPieces[i]);
        }
        try {
            p = p.toRealPath();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            lines = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("Unexpected read error occured. Shutting down.");
            e.printStackTrace();
            System.exit(-1);
        }
        return lines;
    }
}
