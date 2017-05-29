package gui.screencomp;

import gui.GUIHelper;
import gui.OdysseyGUI;
import loghandle.ChatLogEntry;

import javax.swing.*;
import java.awt.*;

public class PrintPanel extends JPanel {

    private JTextArea messageDisplay = new JTextArea("Welcome!");
    private JScrollPane messageScrollWrap = new JScrollPane(messageDisplay);

    private OdysseyGUI parent;

    public PrintPanel(OdysseyGUI parent) {
        super(new GridBagLayout());

        this.parent = parent;

        messageScrollWrap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollWrap.setPreferredSize(new Dimension(500, 200));
        this.add(messageScrollWrap, GUIHelper.getGBC(0,0,1,1,1,1,0,0));

        messageDisplay.setMinimumSize(new Dimension(500, 500));
        messageDisplay.setEditable(false);
        messageDisplay.setLineWrap(true);
        messageDisplay.setWrapStyleWord(true);
    }

    public void append(ChatLogEntry text) {
        //TODO logic for putting this together.
        messageDisplay.append(text.toString());
    }
}
