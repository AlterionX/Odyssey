package gui.screencomp;

import gui.GUIHelper;
import gui.OdysseyChatLogGUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PrintPanel extends JPanel {

    private JTextArea messageDisplay = new JTextArea("Welcome!");


    private OdysseyChatLogGUI parent;

    public PrintPanel(OdysseyChatLogGUI parent) {
        super(new GridBagLayout());

        this.setMinimumSize(new Dimension(500, 500));

        this.parent = parent;

        this.add(messageDisplay, GUIHelper.getGBC(0,0,1,1,1,1,0,0));
    }
}
