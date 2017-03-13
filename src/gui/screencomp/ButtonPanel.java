package gui.screencomp;

import gui.GUIHelper;
import gui.OdysseyChatLogGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
    private JButton spButton = new JButton("Start");
    private JButton stopButton = new JButton("Halt");
    private JCheckBox autoCheckBox = new JCheckBox("Auto");

    private OdysseyChatLogGUI parent;

    public ButtonPanel(OdysseyChatLogGUI parent) {
        super(new GridBagLayout());

        this.parent = parent;

        this.add(autoCheckBox, GUIHelper.getGBC(2, 0));
        this.add(spButton, GUIHelper.getGBC(0, 0));
        this.add(stopButton, GUIHelper.getGBC(1, 0));

        autoCheckBox.addItemListener(e -> toggleAuto());
        spButton.addActionListener(e -> togglePause());
        stopButton.addActionListener(e -> stopReset());
    }

    private void toggleAuto() {
        System.out.println("Auto check box toggled.");
        parent.setAuto(autoCheckBox.isSelected());
    }
    private void togglePause() {
        System.out.println("Start/Pause button pressed.");
        spButton.setText(parent.isPause()? "Start":"Pause");
        parent.setPause(!parent.isPause());
    }
    private void stopReset() {
        System.out.println("Stop pressed.");
        spButton.setText("Start");
        autoCheckBox.setSelected(false);
        parent.setPause(true);
        parent.setAuto(false);
    }
}
