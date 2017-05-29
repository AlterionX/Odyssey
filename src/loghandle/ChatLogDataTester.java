package loghandle;

import java.util.List;

public class ChatLogDataTester {
    private static boolean[] test = {
            false,
            false,
            false,
            false,
            false
    };

    public static void main(String[] args) {
        List<String> categoryNames = ChatLogManager.getCategoryNames();
        if (test[0]) {
            System.out.println("Test 0: Print all categories read from the file system.\n\n\n\n");
            categoryNames.forEach(System.out::println);
        }

        List<String> category = ChatLogManager.getCategoryConvos(categoryNames.get(0));
        if (test[1]) {
            System.out.println("Test 1: Print all conversations (category 0 is always all conversations)\n\n\n\n");
            category.forEach(System.out::println);
        }

        ChatLog randomThing = ChatLogManager.getInstance("aaron1.oclog", true);
        if (test[2]) {
            System.out.println("Test 2: Simulating Aaron and Terence's conversation.\n\n\n\n");
            randomThing.printWithDelays();
        }

        ChatLog otherRandomThing = ChatLogManager.getInstance(category.get(2), false);
        if (test[3]) {
            System.out.println("Test 3: Checking equivalence of multiple log objects through equality operator.\n\n\n\n");
            System.out.println(randomThing == otherRandomThing);
        }

        if (test[4]) {
            System.out.println("Test 4: Testing print of all the files.\n\n\n\n");
            for (String item : category) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                randomThing = ChatLogManager.getInstance(item, false);
                randomThing.printWithDelays();
            }
        }
    }
}
