package MainSystem;

import MainSystem.CartesianCoordinateSystem.CartesianCoordinateSystem;
import MainSystem.Line.DrawingLine;
import MainSystem.Line.Line;
import MainSystem.Point.DrawingPoint;
import MainSystem.Point.Point;
import MainSystem.SystemGlobal.DrawingShape;
import MainSystem.SystemGlobal.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Semaphore;

//todo: understand the implications and advantages/disadvantatges of static imports and compensate codewise
import static MainSystem.SystemGlobal.SystemGlobal.calcPrecisionMathContext;
import static MainSystem.SystemGlobal.SystemGlobal.zero;

public class CartesianCoordinateSystemDisplay {
    CartesianCoordinateSystem backingSystem;

    DrawingLine xAxis;
    DrawingLine yAxis;

    DrawingArea drawingArea;
    boolean drawAxis = true;
    //initially there is one drawing area that is locked to the whole window
    boolean lockDrawingAreaToWholeWindow = true;
    boolean lockPartiallyToWindow = false;
    int slotToLockTo = -1;
    private boolean lockViewBoxToBoundingCoordinates = true; //shirnk/stretch to fit
    private boolean denotePointsOfLines = true;
    private boolean denotePointsOfShapes;

    private Point boundingRight;
    private Point boundingLeft;
    private Point boundingTop;
    private Point boundingBottom;
    private Point scaling = new Point(1, 1);
    //the viewbox potentially cuts off and zooms in on drawn objects
    //the drawing area does translate drawingPoints,


    public Collection<DrawingPoint> allPoints = new ArrayList<>();
    public Collection<DrawingPoint> justPoints = new ArrayList<>();
    public Collection<DrawingPoint> justLinePoints = new ArrayList<>();
    public Collection<DrawingPoint> justShapePoints = new ArrayList<>();
    public Collection<DrawingLine> allLines = new ArrayList<>();
    public Collection<DrawingLine> justlines = new ArrayList<>();
    public Collection<DrawingLine> justShapeLines = new ArrayList<>();
    public Collection<DrawingShape> allShapes = new ArrayList<>();

    boolean newBoundingBox = true;

    private static Semaphore mutex = new Semaphore(1);

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
        xAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.xAxis));
        yAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.yAxis));
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
        //rewrite the following function with bigdecimal instead of double
        return translatePointRelativeToOriginAndViewBox(pointToTranslate.xCoordinate.doubleValue(), pointToTranslate.yCoordinate.doubleValue());
    }

    private Line translateLineRelativeToOriginAndViewBox(Line line) {
        return new Line(new Point(translatePointRelativeToOriginAndViewBox(line.point1)),
                new Point(translatePointRelativeToOriginAndViewBox(line.point2)));
    }

    public Point translatePointRelativeToOriginAndViewBox(double x, double y) {
        Point returnPoint = null;
        if (lockPartiallyToWindow || lockViewBoxToBoundingCoordinates) {
            double xCoordRelativeToOrigin =
                    (x * scaling.xCoordinate.doubleValue()) +
                            drawingArea.width.doubleValue() / 2 +
                            backingSystem.originPoint.xCoordinate.doubleValue() +
                            drawingArea.topLeft.xCoordinate.doubleValue();
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
        return returnPoint;
    }

    public void setBoundingDirection(Point potentiallyBounding) {
        //set the dimensions of a view box instead of setting four different drawingPoints
        //if the binding point changes, we want to react to that, no new
        if (potentiallyBounding.xCoordinate.compareTo(boundingRight.xCoordinate) > 0) {
            boundingRight = potentiallyBounding;
            newBoundingBox = true;
        }
        if (potentiallyBounding.xCoordinate.compareTo(boundingLeft.xCoordinate) < 0) {
            boundingLeft = potentiallyBounding;
            newBoundingBox = true;

        }
        if (potentiallyBounding.yCoordinate.compareTo(boundingTop.yCoordinate) > 0) {
            boundingTop = potentiallyBounding;
            newBoundingBox = true;
        }
        if (potentiallyBounding.yCoordinate.compareTo(boundingBottom.yCoordinate) < 0) {
            boundingBottom = potentiallyBounding;
            newBoundingBox = true;
        }
    }

    private void setNewBoundingBox() {
        //there is no offset for the origin point of a viewbox yet.
        //there is also no offset for a drawing area either.
        drawingArea.viewBox.height = boundingTop.yCoordinate.subtract(boundingBottom.yCoordinate);
        drawingArea.viewBox.width = boundingRight.xCoordinate.subtract(boundingLeft.xCoordinate);
        Point prevScaling = new Point(scaling);
        scaling = new Point(
                drawingArea.width.doubleValue() * 0.80 / drawingArea.viewBox.width.doubleValue(),
                drawingArea.height.doubleValue() * 0.80 / drawingArea.viewBox.height.doubleValue()
        );
        if (scaling.xCoordinate.compareTo(zero) != 0 &&
                scaling.yCoordinate.compareTo(zero) != 0 &&
                prevScaling.xCoordinate.compareTo(zero) != 0 &&
                prevScaling.yCoordinate.compareTo(zero) != 0 &&
                scaling.compareTo(prevScaling) != 0) {
            prevScaling = new Point(
                    prevScaling.xCoordinate.divide(scaling.xCoordinate, calcPrecisionMathContext),
                    prevScaling.yCoordinate.divide(scaling.yCoordinate, calcPrecisionMathContext)
            );
            resizeAxis(prevScaling);
        }
    }

    private void resizeAxis(Point scaling) {
        backingSystem.scaleAxis(scaling);
        xAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.xAxis));
        yAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.yAxis));
    }

    public void draw(Graphics graphics) {//drawing everything each time is not acceptable.
        //only what changes should be redrawn, there should be a redrawn queue,
        //each shape/line/point is kept in a hash map by ID, when a shape, line, or point is added, it is added to the draw queue,
        //when a shape/line/point is changed, it is added to the redraw queue.
        //This function should be moved to something called "initial draw" where everything must be drawn

        //todo: allow the user to select what color the shape will be drawn in, random by default
        //todo: after randomizing the colors that shapes and points are drawn in by default the color selection of the draw function can be simplified
        if (newBoundingBox) {

            if (mutex.tryAcquire()) {
                setNewBoundingBox();
                newBoundingBox = false;
                reDraw(graphics);
                mutex.release();
                return;
            }
        }
        graphics.setColor(Color.BLACK);
        for (DrawingLine line : justlines) {
            graphics.drawLine(
                    line.drawingPoint1.drawingX,
                    line.drawingPoint1.drawingY,
                    line.drawingPoint2.drawingX,
                    line.drawingPoint2.drawingY);

        }
        if (denotePointsOfLines) {
            graphics.setColor(Color.BLUE);
            for (DrawingPoint linePoint : justLinePoints) {
                graphics.fillOval(
                        linePoint.drawingX - 2,
                        linePoint.drawingY - 2,
                        4,
                        4);
            }
        }
        graphics.setColor(Color.RED);
        for (DrawingPoint point : justPoints) {
            graphics.fillOval(
                    point.drawingX - 2,
                    point.drawingY - 2,
                    4,
                    4);
        }
        graphics.setColor(Color.GREEN);
        for (DrawingLine line : justShapeLines) {
            graphics.drawLine(
                    line.drawingPoint1.drawingX,
                    line.drawingPoint1.drawingY,
                    line.drawingPoint2.drawingX,
                    line.drawingPoint2.drawingY
            );
        }

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        graphics.setColor(new Color(r, g, b));
        for (DrawingPoint point : justShapePoints) {
            graphics.fillOval(
                    point.drawingX - 2,
                    point.drawingY - 2,
                    4,
                    4);
        }

        graphics.setColor(Color.RED);
        for (DrawingPoint point : justPoints) {
            graphics.fillOval(
                    point.drawingX - 2,
                    point.drawingY - 2,
                    4,
                    4);
        }
        graphics.setColor(Color.BLACK);
        if (drawAxis) {
            graphics.drawLine(
                    xAxis.drawingPoint1.drawingX,
                    xAxis.drawingPoint1.drawingY,
                    xAxis.drawingPoint2.drawingX,
                    xAxis.drawingPoint2.drawingY
            );
            graphics.drawLine(
                    yAxis.drawingPoint1.drawingX,
                    yAxis.drawingPoint1.drawingY,
                    yAxis.drawingPoint2.drawingX,
                    yAxis.drawingPoint2.drawingY
            );

        }
    }

    private void reDraw(Graphics graphics) {
        clear(true);
        reAdd();
        draw(graphics);
    }

    private void reAdd() {
        //There is a lot of overhead in here.
        Collection<Point> points = backingSystem.getPoints();
        for (Point point : points) {
            addDrawingPoint(point);
        }
        Collection<Line> lines = backingSystem.getLines();
        for (Line line : lines) {
            addDrawingLine(line);
        }
        Collection<Shape> shapes = backingSystem.getShapes();
        for (Shape shape : shapes) {
            addDrawingShape(shape);
        }
        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
            setBoundingDirection(backingSystem.xAxis.point1);
            setBoundingDirection(backingSystem.xAxis.point2);
            setBoundingDirection(backingSystem.yAxis.point1);
            setBoundingDirection(backingSystem.yAxis.point2);
        }
        xAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.xAxis));
        yAxis = new DrawingLine(translateLineRelativeToOriginAndViewBox(backingSystem.yAxis));

    }

    public void setDrawingArea(Point topLeftMain, double height, double width, Point topLeftViewBox, double heightViewBox, double widthViewBox) {
        drawingArea = new DrawingArea(topLeftMain, height, width, topLeftViewBox, heightViewBox, widthViewBox);
    }

    public void setDrawingArea(Point topLeftMain, double height, double width) {
        drawingArea = new DrawingArea(topLeftMain, height, width);
    }

    //todo: resize should not cause a re-add of any points
    //Same goes for adding a new bounding point
    //the "allpoints" container should just be itterated through
    //performing the transformation there.
    public void resize(Dimension size) {
        if (lockDrawingAreaToWholeWindow) {
            drawingArea.setDrawingArea(new Point(0, 0), size.height, size.width);
        }
        if (lockViewBoxToBoundingCoordinates) {
            //todo: make GUIrelativeDimension scoped to the class?
            //there is some ratio to translate backingsystem numbers to GUI numbers.
            scaling = new Point(
                    drawingArea.width.doubleValue() * 0.80 / drawingArea.viewBox.width.doubleValue(),
                    drawingArea.height.doubleValue() * 0.80 / drawingArea.viewBox.height.doubleValue()
            );
            reDraw(DisplayHelper.getDisplay().getGraphics());
        }
    }

    public void clear(boolean displayOnly) {
        this.allPoints = new ArrayList<>();
        this.justLinePoints = new ArrayList<>();
        this.justPoints = new ArrayList<>();
        this.justShapeLines = new ArrayList<>();
        this.justShapePoints = new ArrayList<>();
        this.allLines = new ArrayList<>();
        this.allShapes = new ArrayList<>();
        if (!displayOnly) {
            backingSystem.clear();
            boundingRight = new Point(-Double.MAX_VALUE, 0);
            boundingLeft = new Point(Double.MAX_VALUE, 0);
            boundingTop = new Point(0, -Double.MAX_VALUE);
            boundingBottom = new Point(0, Double.MAX_VALUE);
        }
    }
    //todo: when the viewbox changes as a result of the bounding direction being set the entire system
    //needs to be emptied and redrawn.
    //However, the system can be loaded up and then redrawn still.

    private void addDrawingPoint(Point point) {
        DrawingPoint pointRelativeOrigin = new DrawingPoint(translatePointRelativeToOriginAndViewBox(point));
        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
            setBoundingDirection(point);
        }
        allPoints.add(pointRelativeOrigin);
        justPoints.add(pointRelativeOrigin);
    }

    private void addDrawingLine(Line line) {
        if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
            setBoundingDirection(line.point1);
            setBoundingDirection(line.point2);
        }
        DrawingPoint point1RelativeOrigin = new DrawingPoint(translatePointRelativeToOriginAndViewBox(line.point1));
        DrawingPoint point2RelativeOrigin = new DrawingPoint(translatePointRelativeToOriginAndViewBox(line.point2));
        allPoints.add(point1RelativeOrigin);
        allPoints.add(point2RelativeOrigin);
        justLinePoints.add(point1RelativeOrigin);
        justLinePoints.add(point2RelativeOrigin);
    }

    private void addDrawingShape(Shape shape) {
        if (shape.lines != null) {
            for (Line line : shape.lines) {
                if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                    setBoundingDirection(line.point1);
                    setBoundingDirection(line.point2);
                }
                DrawingLine drawingLine = new DrawingLine(
                        translateLineRelativeToOriginAndViewBox(line));
                allLines.add(drawingLine);
                justShapeLines.add(drawingLine);
                justShapePoints.add(drawingLine.drawingPoint1);
                justShapePoints.add(drawingLine.drawingPoint2);
            }
        }
        if (shape.points != null) {
            for (Point point : shape.points) {
                if (lockViewBoxToBoundingCoordinates || lockPartiallyToWindow) {
                    setBoundingDirection(point);
                }
                //todo:part of making a drawing point is translating it, so that should be a function of the constructor
                justShapePoints.add(new DrawingPoint(translatePointRelativeToOriginAndViewBox(point)));
            }
        }
        allShapes.add(new DrawingShape(shape));
    }

    public void addPoint(Point point) {
        backingSystem.add(point);
        addDrawingPoint(point);
    }

    public void addShape(Shape shape) {
        backingSystem.add(shape);
        addDrawingShape(shape);
    }

    public void addLine(Line line) {
        backingSystem.add(line);
        addDrawingLine(line);
    }
}
