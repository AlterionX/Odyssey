package gui;

import java.awt.*;

public final class GUIHelper {

    /**
     * Given a set of parameters, construct a GridBagConstraint.
     * @param x The x location in the grid.
     * @param y The y location in the grid.
     * @param height The y-span of the component in the grid.
     * @param length The x-span of the component in the grid.
     * @param xweight The horizontal weight of the component.
     * @param yweight The vertical weight of the component.
     * @param xpad The padding in the x direction.
     * @param ypad the padding in the y direction.
     * @return A GridBagConstraints object with the provided parameters.
     */
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
    /**
     * Given a set of parameters, construct a GridBagConstraint.
     * Weight is set to 1 and padding is set to 0.
     * @param x The x location in the grid.
     * @param y The y location in the grid.
     * @param height The y-span of the component in the grid.
     * @param width The x-span of the component in the grid.
     * @return A GridBagConstraints object with the provided parameters.
     */
    public static GridBagConstraints getGBC(int x, int y,
                                            int height, int width) {
        return getGBC(x, y, height, width, 1, 1, 0, 0);
    }
    /**
     * Given a set of parameters, construct a GridBagConstraint.
     * Weight is set to 1, padding is set to 0, and dimensions are set to 1.
     * @param x The x location in the grid.
     * @param y The y location in the grid.
     * @return A GridBagConstraints object with the provided parameters.
     */
    public static GridBagConstraints getGBC(int x, int y) {
        return getGBC(x, y, 1, 1);
    }

    /**
     * A private constructor to prevent constructing.
     */
    private GUIHelper() {
        System.out.println("How did it even get here...");
        throw new UnsupportedOperationException("I'm not supposed to be here. This in a non-instantiable class.");
    }
}
