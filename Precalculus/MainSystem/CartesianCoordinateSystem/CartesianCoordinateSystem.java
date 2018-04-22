package MainSystem.CartesianCoordinateSystem;

import MainSystem.Line.Line;
import MainSystem.Point.Point;
import MainSystem.SystemGlobal.Shape;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("WeakerAccess")
public class CartesianCoordinateSystem extends TwoDimensionalPlane {
    @SuppressWarnings({"WeakerAccess", "CanBeFinal"})
    public Collection<Point> points = new ArrayList<Point>();
    public Collection<Line> lines = new ArrayList<Line>();
    @SuppressWarnings("CanBeFinal")
    public Collection<Shape> shapes = new ArrayList<Shape>();

    public void plotPoint(Point point) {
        //Keeping the plotted points seperate from the grid that they are being plotted on allows transferability of the points.
        //This would be called via another function that would call a repaint making use of the array of points afterward
        points.add(point);
    }

    public void plotShape(Shape shape) {
        shapes.add(shape);
    }

    public CartesianCoordinateSystem(double xAxisRange, double yAxisRange, double denominator) {
        super(new Line(new Point(-xAxisRange, 0), new Point(xAxisRange, 0), 1),
                new Line(new Point(0, -yAxisRange), new Point(0, yAxisRange), 1),
                new BigDecimal(denominator), new BigDecimal(denominator));
    }

    public CartesianCoordinateSystem() {
        super(new Line(new Point(-100, 0),
                new Point(100, 0), 1), new Line(new Point(0, -100),
                new Point(0, 100), 1), new BigDecimal(10), new BigDecimal(10));
    }

    @Override
    public String toString() {
        String pointString = "";
        for (Point point : this.points) {
            pointString += "\n" + point.toString();
        }

        String shapeString = "";
        for (Shape shape : shapes) {
            shapeString += "\n" + shape.toString();
        }
        shapeString += "\n";

        pointString += "\n";
        return "Origin X Axis = " + this.originPoint.xCoordinate.toString() + "\nOrigin Y Axis = " + this.originPoint.yCoordinate.toString() +
                "\nX Axis = " + this.xAxis.toString() + "\nY Axis = " + this.yAxis + "\nPoint = " + pointString + "\nShapes = " + shapeString;
    }
}
