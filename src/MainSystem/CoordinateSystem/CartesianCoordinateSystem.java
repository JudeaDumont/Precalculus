package MainSystem.CoordinateSystem;

import MainSystem.Lines.Line;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class CartesianCoordinateSystem extends TwoDimensionalPlane {
    public Collection<Point> points = new ArrayList<Point>();

    public void plotPoint(Point point) {
        //Keeping the plotted points seperate from the grid that they are being plotted on allows transferability of the points.
        //This would be called via another function that would call a repaint making use of the array of points afterward
        points.add(point);
    }

    public CartesianCoordinateSystem(double xAxisLength, double yAxisLength, double denominator) {
        super(new Line(new Point(-xAxisLength, 0), new Point(xAxisLength, 0), 1),
                new Line(new Point(0, -yAxisLength), new Point(0, yAxisLength), 1), new BigDecimal(denominator), new BigDecimal(denominator));
    }

    public CartesianCoordinateSystem() {
        super(new Line(new Point(-100, 0), new Point(100, 0), 1), new Line(new Point(0, -100), new Point(0, 100), 1), new BigDecimal(10), new BigDecimal(10));
    }
}
