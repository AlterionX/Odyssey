package gui;

import javax.swing.*;
import java.awt.*;

public class GUIHelper {

    //DOES NOTHING< DO NOT USE
    private GUIHelper() {
        System.out.println("How did it even get here...");
    }

    private GridBagConstraints d = new GridBagConstraints(0,0,0,0,
            0,0,0, 0, null,0,0);

    public static GridBagConstraints getGBC(int x, int y,
                                            int height, int length,
                                            int xweight, int yweight,
                                            int xpad, int ypad) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = height;
        constraints.gridwidth = length;
        constraints.weightx = xweight;
        constraints.weighty = yweight;
        constraints.ipadx = xpad;
        constraints.ipady = ypad;

        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }
    public static GridBagConstraints getGBC(int x, int y,
                                            int height, int width) {
        return getGBC(x, y, height, width, 1, 1, 0, 0);
    }
    public static GridBagConstraints getGBC(Point p,
                                            int height, int width) {
        return getGBC(p.x, p.y, height, width);
    }
    public static GridBagConstraints getGBC(int x, int y) {
        return getGBC(x, y, 1, 1);
    }

}
