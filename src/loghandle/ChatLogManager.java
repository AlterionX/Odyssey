package loghandle;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.*;

public class ChatLogManager {

    /**
     * The list of all set directories.
     *
     * TODO create a configuration file for these values.
     */
    private final static String HOME_DIR = "./logs";
    private final static String RECORDS_FILE = "history.llst";
    private final static String CATEGORIES_FILE = "categories.oplist";
    //Lists that are placed in file/populated on startup (mostly. things are only added)
    private static final Object recordsLock = new Object();
    /**
     * A list of the paths to records.
     */
    private static final List<String> recordsList = fetchRecordList();
    /**
     * A map from a category's name to the path to the category file as well as the list of conversation files
     * within the category.
     */
    private static final Map<String, Pair<String, List<String>>> CATEGORY_DATA = fetchCategoryList(); //Try for immutable later
    //These two are only called here, on launch, and are private. Synchronization unecessary
    /**
     * Get the list of list of records
     * @return A list of all available records
     */
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
    /**
     * Gets a map of the name of a category to the path of the category and the list of
     * paths to the conversations in the category.
     * @return The above map.
     */
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


    private static final Object logsLock = new Object();
    //Object pool/cache/multiton/whateveryouwanttocallit
    // Synchronization needed when looking at/modifying this object
    /**
     * List of initialised conversations.
     */
    private static final HashMap<String, ChatLog> initializedLogs = new HashMap<>();


    //General access
    /**
     * Get the names of all of the categories.
     * @return The list of all the categories.
     */
    public static List<String> getCategoryNames() {
        ArrayList<String> names = new ArrayList<>();
        names.addAll(CATEGORY_DATA.keySet());
        return names;
    }
    /**
     * Gets conversations from a single category.
     * @param categoryName The name fo the category.
     * @return A list of conversations in that category.
     */
    public static List<String> getCategoryConvos(String categoryName) {
        return CATEGORY_DATA.get(categoryName).getValue();
    }

    //Chat log access
    /**
     * Get a conversation based on the path to that conversation.
     * @param pathPart The path to the conversation
     * @param includeHome If the HOME path should be prepended to the first path.
     * @return A ChatLog based on the file found at the path, or HOME_DIR + path.
     */
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
        synchronized (recordsLock) {
            if (!recordsList.contains(path)) {
                System.out.println("Warning: Attempting to access a strange path: " + path);
                recordsList.add(path);
            }
        }
        return internalInstanceFetch(path);
    }
    /**
     * Fetches the files, processing the data. This is placed into the initalized logs, essentially cached.
     * @param path The path to the file to be provided.
     * @return The path that is matched.
     */
    private static ChatLog internalInstanceFetch(String path) {
        String[] pieces = path.split("\\.");
        if (!pieces[pieces.length - 1].equalsIgnoreCase("oclog")) {
            throw new IllegalStateException("Cannot access logs that do not have the correct file extension.");
        }
        //Make sure that only one checks at a time, unnecessary if another thread jumped ahead to initialize the same thing
        synchronized (logsLock) {
            if (!initializedLogs.containsKey(path)) {
                try {
                    initializedLogs.put(path, new ChatLog(path));
                } catch (UnexpectedException e) {
                    System.out.println("Exception encountered in file read.");
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        return initializedLogs.get(path);
    }

    //Additional methods. Fully thread-safe.
    /**
     * Helper function to get all the lines from a provided file. Attempts to resolve path and other issues.
     * @param pathPieces Segments, in order, of the path to the file.
     * @return A list of strings for each of the lines in the file.
     */
    static List<String> readAllLinesFromFile(String... pathPieces) {
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
    /**
     * Get the home directory.
     * @return The String of the home directory.
     */
    static String getHomeDir() {
        return HOME_DIR;
    }
}
