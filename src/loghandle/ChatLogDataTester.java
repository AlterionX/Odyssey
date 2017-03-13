package loghandle;

import java.util.List;

/**
 * Created by benbe on 3/9/2017.
 */
public class ChatLogDataTester {
    public static void main(String[] args) {
        ChatLog randomThing = ChatLog.getInstance("aaron1.oclog", true);
        randomThing.printWithDelays();

        List<String> categoryNames = ChatLog.getCategoryNames();
        categoryNames.forEach(System.out::println);

        List<String> category = ChatLog.getCategoryConvos(categoryNames.get(0));
        category.forEach(System.out::println);

        ChatLog otherRandomThing = ChatLog.getInstance(category.get(2), false);
        System.out.println(randomThing == otherRandomThing);

        for (String item : category) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randomThing = ChatLog.getInstance(item, false);
            randomThing.printWithDelays();
        }

    }
}
