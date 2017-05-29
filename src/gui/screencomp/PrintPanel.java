package gui.screencomp;

import gui.GUIHelper;
import loghandle.ChatLogEntry;

import javax.swing.*;
import java.awt.*;

public class PrintPanel extends JPanel {

    private final JTextArea messageDisplay = new JTextArea("Welcome!");
    private final JScrollPane messageScrollWrap = new JScrollPane(messageDisplay);

    public PrintPanel() {
        super(new GridBagLayout());

        messageScrollWrap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollWrap.setPreferredSize(new Dimension(500, 200));
        this.add(messageScrollWrap, GUIHelper.getGBC(0,0,1,1,1,1,0,0));
        messageScrollWrap.getVerticalScrollBar().setValue(messageScrollWrap.getVerticalScrollBar().getMaximum());

        messageDisplay.setMinimumSize(new Dimension(500, 500));
        messageDisplay.setEditable(false);
        messageDisplay.setLineWrap(true);
        messageDisplay.setWrapStyleWord(true);
    }

    public void append(ChatLogEntry text) {
        SwingUtilities.invokeLater(()-> {
            boolean isAtMax = messageScrollWrap.getVerticalScrollBar().getValue()
                    + messageScrollWrap.getVerticalScrollBar().getVisibleAmount() == messageScrollWrap.getVerticalScrollBar().getMaximum();
            System.out.println(isAtMax);
            //TODO logic for putting this together.
            messageDisplay.append(text.toString());
            messageDisplay.append("\n");
            //TODO force scrollpane to scroll to bottom only if already at bottom. Code doesn't scroll all the way
            if (isAtMax) {
                messageScrollWrap.getVerticalScrollBar().setValue(messageScrollWrap.getVerticalScrollBar().getMaximum() - messageScrollWrap.getVerticalScrollBar().getVisibleAmount());
            }
        });
    }
}
