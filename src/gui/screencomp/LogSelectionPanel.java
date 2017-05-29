package gui.screencomp;

import gui.ConversationManager;
import gui.GUIHelper;
import gui.OdysseyGUI;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.util.List;

@SuppressWarnings("serial")
public class LogSelectionPanel extends JPanel {
    private final JComboBox<String> categoryComboBox;
    private final JComboBox<String> conversationComboBox;

    private final OdysseyGUI parent;
    private final ConversationManager cMan;

    public LogSelectionPanel(OdysseyGUI parent, ConversationManager cman) {
        super(new GridBagLayout());
        this.parent = parent;
        this.cMan = cman;

        //Make components
        categoryComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        conversationComboBox = new JComboBox<>(new DefaultComboBoxModel<String>());
        JButton loadSelectedLogs = new JButton("Load Log(s)");

        //Stop collision with my spacebar
        loadSelectedLogs.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");

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
        parent.requestHalt();
        cMan.resetPrintingState();
        //No change to category
        cMan.primeLogs(conversationComboBox.getSelectedIndex());
    }
    private void updateNames() {
        System.out.println("Updating names!");
        conversationComboBox.removeAllItems();
        conversationComboBox.addItem("All");
        List<String> names = cMan.setConvoListFromCategory(categoryComboBox.getSelectedIndex());
        for (String name: names) {
            conversationComboBox.addItem(name);
        }
    }
}
