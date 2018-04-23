package MainSystem;

import MainSystem.Point.Point;
import MainSystem.SystemGlobal.Shape;

public class DisplayHelper {
    //There is only one instance of a display per instance of the program
    //A display may have multiple coordinate systems though
    private static Display display = null;
    private static DisplayHelper displayHelper = null;

    private DisplayHelper(Display display) {
        this.display = display;
    }

    public static void setDisplay(Display display) {
        displayHelper = new DisplayHelper(display);
    }

    public static Display getDisplay() {
        if (display == null) {
            throw new NullPointerException("Display Helper Was Null");
        }
        return display;
    }

    public static void executeCommand(String[] splitCommand) {

    }

    public static void createCenterPoint() {
        display.createCenterPoint();
        display.repaint();
    }

    public static void createCircle(double x, double y, double radius, long precision) {
        display.createCircle(new Point(x, y), radius, precision);
        display.repaint();
    }

    public static void drawLine(double x1, double y1, double x2, double y2) {
        display.drawLine(x1, y1, x2, y2);
        display.repaint();
    }

    public static void createGenericShape(Object o) {
        //todo: Here is where the shape should be added to a list of shapes the user can select from.

        try {
            display.createGenericShape((Shape) o);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        display.repaint();
    }
}
