package gui.screencomp;

import gui.ConversationManager;
import gui.GUIHelper;
import gui.OdysseyChatLogGUI;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LogSelectionPanel extends JPanel {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> conversationComboBox;
    private JButton loadSelectedLogs;

    private OdysseyChatLogGUI parent;

    public LogSelectionPanel(OdysseyChatLogGUI parent) {
        super(new GridBagLayout());
        this.parent = parent;

        //Make components
        categoryComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        conversationComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        loadSelectedLogs = new JButton("Load Log(s)");

        //Add components
        this.add(categoryComboBox, GUIHelper.getGBC(2, 2, 1, 2));
        this.add(conversationComboBox, GUIHelper.getGBC(2, 3, 1, 2));
        this.add(loadSelectedLogs, GUIHelper.getGBC(4, 2, 2, 1));

        for (String category: ConversationManager.getCategoryNames()) {
            categoryComboBox.addItem(category);
        }
        updateNames();

        //Set listeners
        categoryComboBox.addActionListener(e -> updateNames());
        loadSelectedLogs.addActionListener(e -> loadLogs());
    }

    private void loadLogs() {
        System.out.println("Loading logs!");
        ArrayList<String> newConvos = new ArrayList<>();
        if (conversationComboBox.getSelectedIndex() == 0) {
            for (String x: ConversationManager.getCurrentCategoryConversationNames()) {
                newConvos.add(x);
            }
        } else {
            newConvos.add(ConversationManager.getCurrentCategoryConversationNames().get(conversationComboBox.getSelectedIndex() - 1));
        }
        System.out.println("Adding logs: ");
        System.out.println("\t" + newConvos.toString());
        parent.halt();
        ConversationManager.resetCurrent();

    }
    private void updateNames() {
        System.out.println("Updating names!");
        conversationComboBox.removeAllItems();
        conversationComboBox.addItem("All");
        ConversationManager.reset();
        List<String> names = ConversationManager.setCategory(categoryComboBox.getSelectedIndex());
        for (String name: names) {
            conversationComboBox.addItem(name);
        }
    }
}
