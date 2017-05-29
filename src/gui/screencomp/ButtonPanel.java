package gui.screencomp;

import gui.GUIHelper;
import gui.OdysseyGUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
    private final JCheckBox autoCheckBox = new JCheckBox("Auto");

    private final OdysseyGUI parent;
    private boolean auto = false;

    public ButtonPanel(OdysseyGUI parent) {
        super(new GridBagLayout());

        this.parent = parent;

        this.add(autoCheckBox, GUIHelper.getGBC(0, 0));
        JButton resetButton = new JButton("Reset");
        this.add(resetButton, GUIHelper.getGBC(1, 0));

        resetButton.addActionListener(e -> stopReset());
        autoCheckBox.addActionListener(e -> {
            boolean hi = autoCheckBox.isSelected();
            setAuto(hi, true);
        });
    }

    public void toggleAuto() {
        System.out.println("Auto check box toggled.");
        setAuto(!getAuto());
    }
    public void setAuto(boolean auto) {
        setAuto(auto, false);
    }
    private void setAuto(boolean auto, boolean external) {
        System.out.println(String.format("Auto check box set to %b.", auto));
        this.auto = auto;
        if (!external) autoCheckBox.setSelected(getAuto());
        if (getAuto()) {
            parent.createAndStartAutoThread();
        } else {
            parent.interruptAutoThread();
        }
    }

    private boolean getAuto() {
        return auto;
    }
    private void stopReset() {
        System.out.println("Stop pressed.");
        auto = false;
        setAuto(false);
    }
}
