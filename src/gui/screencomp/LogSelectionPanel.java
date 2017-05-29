package gui.screencomp;

import gui.ConversationManager;
import gui.GUIHelper;
import gui.OdysseyGUI;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LogSelectionPanel extends JPanel {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> conversationComboBox;
    private JButton loadSelectedLogs;

    private OdysseyGUI parent;
    private ConversationManager cMan;

    public LogSelectionPanel(OdysseyGUI parent, ConversationManager cman) {
        super(new GridBagLayout());
        this.parent = parent;
        this.cMan = cman;

        //Make components
        categoryComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        conversationComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        loadSelectedLogs = new JButton("Load Log(s)");

        //Add components
        this.add(categoryComboBox, GUIHelper.getGBC(2, 2, 1, 2));
        this.add(conversationComboBox, GUIHelper.getGBC(2, 3, 1, 2));
        this.add(loadSelectedLogs, GUIHelper.getGBC(4, 2, 2, 1));

        for (String category: cMan.getCategoryNames()) {
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
            newConvos.addAll(cMan.getConvoList());
        } else {
            newConvos.add(cMan.getConvoList().get(conversationComboBox.getSelectedIndex() - 1));
        }
        System.out.println("Adding logs: ");
        System.out.println("\t" + newConvos.toString());
        parent.requestHalt();
        cMan.resetPrintingState();
    }
    private void updateNames() {
        System.out.println("Updating names!");
        conversationComboBox.removeAllItems();
        conversationComboBox.addItem("All");
        cMan.resetCategoryState();
        List<String> names = cMan.setConvoListFromCategory(categoryComboBox.getSelectedIndex());
        for (String name: names) {
            conversationComboBox.addItem(name);
        }
    }
}
