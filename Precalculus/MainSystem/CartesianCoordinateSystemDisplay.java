package MainSystem;

import MainSystem.CartesianCoordinateSystem.CartesianCoordinateSystem;
import MainSystem.Line.Line;
import MainSystem.Point.Point;
import MainSystem.SystemGlobal.Shape;

import java.awt.*;

public class CartesianCoordinateSystemDisplay {
    CartesianCoordinateSystem backingSystem;
    DrawingArea drawingArea;
    boolean drawAxis = true;
    //initially there is one drawing area that is locked to the whole window
    boolean lockDrawingAreaToWholeWindow = true;
    boolean lockPartiallyToWindow = false;
    int slotToLockTo = -1;
    private boolean lockViewBoxToBoundingCoordinates = true; //shirnk/stretch to fit
    private boolean denotePointsOfLines = true;
    private Point boundingRight;
    private Point boundingLeft;
    private Point boundingTop;
    private Point boundingBottom;
    private Point scaling = new Point(1, 1);
    //the drawing area does translate points,
    //the viewbox potentially cuts off and zooms in on drawn objects

    public CartesianCoordinateSystemDisplay(Graphics graphics, int height, int width, double xAxisRange, double yAxisRange, double denominator) {
        backingSystem = new CartesianCoordinateSystem(xAxisRange, yAxisRange, denominator);
        initialize(height, width);
    }

    public CartesianCoordinateSystemDisplay(Graphics graphics, int height, int width) {
        backingSystem = new CartesianCoordinateSystem();
        initialize(height, width);
    }

    private void initialize(double height, double width) {
        drawingArea = new DrawingArea(new Point(0, 0), height, width);
        drawAxis = true;
        boundingRight = new Point(-Double.MAX_VALUE, 0);
        boundingLeft = new Point(Double.MAX_VALUE, 0);
        boundingTop = new Point(0, -Double.MAX_VALUE);
        boundingBottom = new Point(0, Double.MAX_VALUE);
    }

    public void draw(Point topLeftMain, double height, double width, Point topLeftViewBox, double heightViewBox, double widthViewBox) {

    }


    public Point translatePointRelativeToGUI(double x, double y) {
        //This function being contained here implies that a coord system has one drawing area and a drawing area has one viewbox
        //this has to be per line.

        return new Point(
                x +
                        drawingArea.width.doubleValue() / 2 +
                        backingSystem.originPoint.xCoordinate.doubleValue() +
                        drawingArea.topLeft.xCoordinate.doubleValue(),
                y +
                        drawingArea.height.doubleValue() / 2 +
                        backingSystem.originPoint.yCoordinate.doubleValue() +
                        drawingArea.topLeft.yCoordinate.doubleValue()
        );
    }

    public Point translatePointRelativeToOriginAndViewBox(Point pointToTranslate) {
        return translatePointRelativeToOriginAndViewBox(pointToTranslate.xCoordinate.doubleValue(), pointToTranslate.yCoordinate.doubleValue());
    }

    public Point translatePointRelativeToOriginAndViewBox(double x, double y) {
        Point returnPoint = null;
        if (lockPartiallyToWindow || lockViewBoxToBoundingCoordinates) {
            double xCoordRelativeToOrigin =
                    (x * scaling.xCoordinate.doubleValue()) +
                            drawingArea.width.doubleValue() / 2 +
                            backingSystem.originPoint.xCoordinate.doubleValue() +
                            drawingArea.topLeft.xCoordinate.doubleValue();
            //System.out.println(scaling.xCoordinate.doubleValue());
            double yCoordRelativeToOrigin =
                    (y * scaling.yCoordinate.doubleValue()) +
                            drawingArea.height.doubleValue() / 2 +
                            backingSystem.originPoint.yCoordinate.doubleValue() +
                            drawingArea.topLeft.yCoordinate.doubleValue();
            returnPoint = new Point(xCoordRelativeToOrigin, yCoordRelativeToOrigin);
        } else {
            returnPoint = new Point(
                    (x +
                            drawingArea.width.doubleValue() / 2 +
                            backingSystem.originPoint.xCoordinate.doubleValue() +
                            drawingArea.topLeft.xCoordinate.doubleValue()),
                    (y +
                            drawingArea.height.doubleValue() / 2 +
                            backingSystem.originPoint.yCoordinate.doubleValue() +
                            drawingArea.topLeft.yCoordinate.doubleValue())
            );
        }
//        System.out.print("DrawnPoint: " + returnPoint);
        return returnPoint;
    }

    public void setBoundingDirection(Point potentiallyBounding) {
        //set the dimensions of a view box instead of setting four different points
        //if the binding point changes, we want to react to that, no new
        if (potentiallyBounding.xCoordinate.compareTo(boundingRight.xCoordinate) > 0) {
            boundingRight = potentiallyBounding;
        }
        if (potentiallyBounding.xCoordinate.compareTo(boundingLeft.xCoordinate) < 0) {
            boundingLeft = potentiallyBounding;
        }
        if (potentiallyBounding.yCoordinate.compareTo(boundingTop.yCoordinate) > 0) {
            System.out.println("+yCoordinate" + potentiallyBounding.yCoordinate);
            boundingTop = potentiallyBounding;
        }
        if (potentiallyBounding.yCoordinate.compareTo(boundingBottom.yCoordinate) < 0) {
            System.out.println("-yCoordinate" + potentiallyBounding.yCoordinate);
            boundingBottom = potentiallyBounding;
        }
    }

    private void setNewBoundingBox() {
        //there is no offset for the origin point of a viewbox yet.
        //there is also no offset for a drawing area either.
        drawingArea.viewBox.height = boundingTop.yCoordinate.subtract(boundingBottom.yCoordinate);
        System.out.println(drawingArea.viewBox.height);
//        System.out.println(boundingBottom.yCoordinate);
        drawingArea.viewBox.width = boundingRight.xCoordinate.subtract(boundingLeft.xCoordinate);
        System.out.println(drawingArea.viewBox.width);
//        System.out.println(drawingArea.viewBox.width);
        scaling = new Point(
                drawingArea.width.doubleValue() * 0.91 / drawingArea.viewBox.width.doubleValue(),
                drawingArea.height.doubleValue() * 0.91 / drawingArea.viewBox.height.doubleValue()
        );
//        System.out.println("scaling1: " + scaling);
    }

    public void draw(Graphics graphics) {//drawing everything each time is not acceptable.
        //only what changes should be redrawn, there should be a redrawn queue,
        //each shape/line/point is kept in a hash map by ID, when a shape, line, or point is added, it is added to the draw queue,
        //when a shape/line/point is changed, it is added to the redraw queue.
        //This function should be moved to something called "initial draw" where everything must be drawn
        Point prevRightBinding = null;
        Point prevLeftBinding = null;
        Point prevTopBinding = null;
        Point prevBottomBinding = null;
        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
            prevRightBinding = new Point(boundingRight);
            prevLeftBinding = new Point(boundingLeft);
            prevTopBinding = new Point(boundingTop);
            prevBottomBinding = new Point(boundingBottom);
        }

        for (Line line : backingSystem.lines) {
            Point point1RelativeOrigin = translatePointRelativeToOriginAndViewBox(line.point1);
            Point point2RelativeOrigin = translatePointRelativeToOriginAndViewBox(line.point2);
            if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                setBoundingDirection(line.point1);
                setBoundingDirection(line.point2);
            }

            graphics.drawLine(
                    point1RelativeOrigin.xCoordinate.intValue(),
                    point1RelativeOrigin.yCoordinate.intValue(),
                    point2RelativeOrigin.xCoordinate.intValue(),
                    point2RelativeOrigin.yCoordinate.intValue());
            if (denotePointsOfLines) {
                graphics.setColor(Color.BLUE);
                int xCoord = point1RelativeOrigin.xCoordinate.intValue();
                int yCoord = point1RelativeOrigin.yCoordinate.intValue();

                graphics.fillOval(
                        xCoord - 2,
                        yCoord - 2,
                        4,
                        4);
                xCoord = point2RelativeOrigin.xCoordinate.intValue();
                yCoord = point2RelativeOrigin.yCoordinate.intValue();
                graphics.fillOval(
                        xCoord - 2,
                        yCoord - 2,
                        4,
                        4);
            }
//            System.out.println(line);
        }
        graphics.setColor(Color.RED);
        for (Point point : backingSystem.points) {
            Point pointRelativeOrigin = translatePointRelativeToOriginAndViewBox(point);
            int xCoord = pointRelativeOrigin.xCoordinate.intValue();
            int yCoord = pointRelativeOrigin.yCoordinate.intValue();
            graphics.fillOval(
                    xCoord - 2,
                    yCoord - 2,
                    4,
                    4);
//            System.out.println(point);
            if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                setBoundingDirection(point);
            }
        }
        for (Shape shape : backingSystem.shapes) {
            graphics.setColor(Color.BLUE);
//            System.out.println(shape);
            if (shape.lines != null) {
                for (Line line : shape.lines) {
                    Point point1RelativeOrigin = translatePointRelativeToOriginAndViewBox(line.point1);
                    Point point2RelativeOrigin = translatePointRelativeToOriginAndViewBox(line.point2);
                    if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                        setBoundingDirection(line.point1);
                        setBoundingDirection(line.point2);
                    }
                    graphics.drawLine(
                            point1RelativeOrigin.xCoordinate.intValue(),
                            point1RelativeOrigin.yCoordinate.intValue(),
                            point2RelativeOrigin.xCoordinate.intValue(),
                            point2RelativeOrigin.yCoordinate.intValue());

                    if (denotePointsOfLines) {
                        int xCoord = point1RelativeOrigin.xCoordinate.intValue();
                        int yCoord = point1RelativeOrigin.yCoordinate.intValue();
                        graphics.fillOval(
                                xCoord - 2,
                                yCoord - 2,
                                4,
                                4);
                        xCoord = point2RelativeOrigin.xCoordinate.intValue();
                        yCoord = point2RelativeOrigin.yCoordinate.intValue();
                        graphics.fillOval(
                                xCoord - 2,
                                yCoord - 2,
                                4,
                                4);
                    }
//                    System.out.println("Shape: " + shape);
                }
            }
            if (shape.points != null) {
                if (denotePointsOfLines) {
                    graphics.setColor(Color.BLACK);
                    for (Point point : shape.points) {
                        Point translatePointRelativeToOrigin = translatePointRelativeToOriginAndViewBox(point.xCoordinate.doubleValue(), point.yCoordinate.doubleValue());
                        int xCoord = translatePointRelativeToOrigin.xCoordinate.intValue();
                        int yCoord = translatePointRelativeToOrigin.yCoordinate.intValue();
                        graphics.fillOval(
                                xCoord - 2,
                                yCoord - 2,
                                4,
                                4);
                        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                            setBoundingDirection(point);
                        }
//                        System.out.println("Shape: " + "Point: " + point);
                    }
                }
            }
        }
        graphics.setColor(Color.RED);
        for (Point point : backingSystem.points) {
            Point pointRelativeOrigin = translatePointRelativeToOriginAndViewBox(point);
            if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                setBoundingDirection(point);
            }
            int xCoord = pointRelativeOrigin.xCoordinate.intValue();
            int yCoord = pointRelativeOrigin.yCoordinate.intValue();
            graphics.fillOval(
                    xCoord - 2,
                    yCoord - 2,
                    4,
                    4);
//            System.out.println("Shape: " + point);
        }
        graphics.setColor(Color.BLACK);
        if (drawAxis) {
            Point translatedPoint1X = translatePointRelativeToOriginAndViewBox(backingSystem.xAxis.point1.xCoordinate.doubleValue(),
                    backingSystem.xAxis.point1.yCoordinate.doubleValue());
            Point translatedPoint2X = translatePointRelativeToOriginAndViewBox(backingSystem.xAxis.point2.xCoordinate.doubleValue(),
                    backingSystem.xAxis.point2.yCoordinate.doubleValue());
            Point translatedPoint1Y = translatePointRelativeToOriginAndViewBox(backingSystem.yAxis.point1.xCoordinate.doubleValue(),
                    backingSystem.yAxis.point1.yCoordinate.doubleValue());
            Point translatedPoint2Y = translatePointRelativeToOriginAndViewBox(backingSystem.yAxis.point2.xCoordinate.doubleValue(),
                    backingSystem.yAxis.point2.yCoordinate.doubleValue());
            graphics.drawLine(
                    translatedPoint1X.xCoordinate.intValue(),
                    translatedPoint1X.yCoordinate.intValue(),
                    translatedPoint2X.xCoordinate.intValue(),
                    translatedPoint2X.yCoordinate.intValue()
            );
            graphics.drawLine(
                    translatedPoint1Y.xCoordinate.intValue(),
                    translatedPoint1Y.yCoordinate.intValue(),
                    translatedPoint2Y.xCoordinate.intValue(),
                    translatedPoint2Y.yCoordinate.intValue()
            );
            if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                setBoundingDirection(backingSystem.xAxis.point1);
                setBoundingDirection(backingSystem.xAxis.point2);
                setBoundingDirection(backingSystem.yAxis.point1);
                setBoundingDirection(backingSystem.yAxis.point2);
//                System.out.println(backingSystem.xAxis.point1);
//                System.out.println(backingSystem.xAxis.point2);
//                System.out.println(backingSystem.yAxis.point1);
//                System.out.println(backingSystem.yAxis.point2);
            }
        }
        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
            if (
                    !prevRightBinding.equals(boundingRight) ||
                            !prevLeftBinding.equals(boundingLeft) ||
                            !prevTopBinding.equals(boundingTop) ||
                            !prevBottomBinding.equals(boundingBottom)
                    ) {
//                System.out.println("BOUNDINGBOX");
                setNewBoundingBox();
                draw(graphics);
                DisplayHelper.getDisplay().repaint();
            }
        }
    }


    public void setDrawingArea(Point topLeftMain, double height, double width, Point topLeftViewBox, double heightViewBox, double widthViewBox) {
        drawingArea = new DrawingArea(topLeftMain, height, width, topLeftViewBox, heightViewBox, widthViewBox);
    }

    public void setDrawingArea(Point topLeftMain, double height, double width) {
        drawingArea = new DrawingArea(topLeftMain, height, width);
    }

    public void resize(Dimension size) {
        if (lockDrawingAreaToWholeWindow) {
            drawingArea.setDrawingArea(new Point(0, 0), size.height, size.width);
//            System.out.println(size);
        }
        if (lockViewBoxToBoundingCoordinates) {
            //todo: make GUIrelativeDimension scoped to the class?
            //there is some ratio to translate backingsystem numbers to GUI numbers.
            scaling = new Point(
                    (drawingArea.width.doubleValue()) / (drawingArea.viewBox.width.doubleValue()),
                    (drawingArea.height.doubleValue()) / (drawingArea.viewBox.height.doubleValue())
            );
//            System.out.println("scaling2: " + scaling);
            draw(DisplayHelper.getDisplay().getGraphics());
            DisplayHelper.getDisplay().repaint();

//            System.out.println(guiRelativeDimension);
//            System.out.println(guiRelativeDimension);

//            System.out.println(scaling);
        }
    }
}
