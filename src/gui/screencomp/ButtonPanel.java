package gui.screencomp;

import gui.GUIHelper;
import gui.OdysseyGUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
    private JButton resetButton = new JButton("Reset");
    private JCheckBox autoCheckBox = new JCheckBox("Auto");

    private OdysseyGUI parent;
    private boolean auto = false;

    public ButtonPanel(OdysseyGUI parent) {
        super(new GridBagLayout());

        this.parent = parent;

        this.add(autoCheckBox, GUIHelper.getGBC(0, 0));
        this.add(resetButton, GUIHelper.getGBC(1, 0));

        resetButton.addActionListener(e -> stopReset());
    }

    public void toggleAuto() {
        System.out.println("Auto check box toggled.");
        setAuto(!getAuto());
    }
    public void setAuto(boolean auto) {
        System.out.println(String.format("Auto check box set to %b.", auto));
        this.auto = auto;
        autoCheckBox.setSelected(getAuto());
    }
    public boolean getAuto() {
        return auto;
    }
    private void stopReset() {
        System.out.println("Stop pressed.");
        auto = false;
        autoCheckBox.setSelected(false);
    }
}
