package MainSystem;

//A simple Swing application

import MainSystem.Circle.Circle;
import MainSystem.Line.Line;
import MainSystem.Point.Point;
import MainSystem.SystemGlobal.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collection;

public class Display extends JFrame {
    public JTextField textFieldCmdInputField;
    public String resizeParameterDebounceVariable = "COMPONENT_RESIZED (0,0 800x500)";
    public ComponentListener resizeListener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
            resizeIfLocked(e);
        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    };

    public WindowListener maximizeMinimizeListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {
            resizeIfLocked(e);
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            resizeIfLocked(e);
        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };
    public Collection<CartesianCoordinateSystemDisplay> cartesianCoordinateSystemsDisplays =
            new ArrayList<CartesianCoordinateSystemDisplay>();

    Display(Boolean defaultCartesian, int width, int height) {
        super("Basic Display");
        setLayout(new FlowLayout());

        //give the frame an intial size
        setSize(width, height);
        //Terminates the program when the user closes the application.

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create a text-basedlabel
        textFieldCmdInputField = new JTextField("Command");
        textFieldCmdInputField.setVisible(true);
        textFieldCmdInputField.addActionListener(new CommandInterpreter());

        System.out.println("CONSTRUCT");
        add(textFieldCmdInputField);

        if (defaultCartesian) {
            System.out.println("OKAY");
            CartesianCoordinateSystemDisplay coorSysDisp = new CartesianCoordinateSystemDisplay(getGraphics(), height, width);
            cartesianCoordinateSystemsDisplays.add(coorSysDisp);
            //Whatever the defaults for the cartesian coordinate system should be fine for now.
        }
        addComponentListener(resizeListener);

        setVisible(true);
    }

    private void resizeIfLocked(ComponentEvent e) {
        //todo: The check for whether or not boundaries need to be reset on resizing of the window should be done at a top level,
        //This will require redesign along the flow control starting here for each function.
        if (!e.paramString().equals(resizeParameterDebounceVariable)) {
            resizeParameterDebounceVariable = e.paramString();
            System.out.println(e.paramString());
            for (CartesianCoordinateSystemDisplay cartesianCoordinateSystemDisplay : cartesianCoordinateSystemsDisplays) {
                cartesianCoordinateSystemDisplay.resize(getSize());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (CartesianCoordinateSystemDisplay cartesianCoordinateSystemDisplay : cartesianCoordinateSystemsDisplays) {
            cartesianCoordinateSystemDisplay.draw(getGraphics());
        }
    }

    public static void main(String args[]) {
        //Creates the frame on the even dispatching thread
        SwingUtilities.invokeLater(() -> DisplayHelper.setDisplay(new Display(true, 800, 500)));
    }

    public void createCenterPoint() {
        //get the Selected Cartesian Coordinate System Display
        CartesianCoordinateSystemDisplay selectedCartesianCoordinateSystemDisplay = cartesianCoordinateSystemsDisplays.iterator().next();
        selectedCartesianCoordinateSystemDisplay.addPoint(new Point(0, 0));
        System.out.println("createCenterPoint");
    }

    public void createCircle(Point centerPoint, double radius, long precision) {
        //get the Selected Cartesian Coordinate System Display
        CartesianCoordinateSystemDisplay selectedCartesianCoordinateSystemDisplay = cartesianCoordinateSystemsDisplays.iterator().next();
        selectedCartesianCoordinateSystemDisplay.addShape(new Circle(centerPoint, radius, precision));
//        System.out.println("createCircle");
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        //get the Selected Cartesian Coordinate System Display
        CartesianCoordinateSystemDisplay selectedCartesianCoordinateSystemDisplay = cartesianCoordinateSystemsDisplays.iterator().next();
        selectedCartesianCoordinateSystemDisplay.addLine(new Line(new Point(x1, y1), new Point(x2, y2)));

//        System.out.println("createLine");
    }

    public void createGenericShape(Shape shape) {
        CartesianCoordinateSystemDisplay selectedCartesianCoordinateSystemDisplay = cartesianCoordinateSystemsDisplays.iterator().next();
        selectedCartesianCoordinateSystemDisplay.addShape(shape);
//        System.out.println("createCircle");
    }
}
