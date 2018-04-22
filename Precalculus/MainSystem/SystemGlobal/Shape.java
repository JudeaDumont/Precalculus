package MainSystem.SystemGlobal;

import MainSystem.Line.Line;
import MainSystem.Line.LinePredicates;
import MainSystem.Point.Point;
import MainSystem.Point.PointPredicates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static MainSystem.SystemGlobal.SystemGlobal.zero;

/**
 * Created by Owner on 7/10/2017.
 */
public abstract class Shape {
    public Point[] points;
    @SuppressWarnings("WeakerAccess")
    public Line[] lines;
    @SuppressWarnings("WeakerAccess")
    public BigDecimal area;
    @SuppressWarnings("WeakerAccess")
    public BigDecimal perimeter;
    public String name = "";

    //Since this breaks the constructor design pattern intended for the shape usage
    //And since the Projectile Implementations Temporality requires a shape deep copy.
    //Not to mention the general benefits of having a shape deep copy.
    //The copy constructor should essentially be moved to a static method deepCopyShape
    public Shape(Shape shape) {
        this.points = new Point[shape.points.length];
        for (int i = 0; i < shape.points.length; i++) {
            this.points[i] = new Point(shape.points[i]);
        }

        this.lines = new Line[shape.lines.length];
        for (int i = 0; i < shape.lines.length; i++) {
            this.lines[i] = new Line(shape.lines[i]);
        }

        this.area = new BigDecimal(shape.area.doubleValue());
        this.perimeter = new BigDecimal(shape.perimeter.doubleValue());
        this.name = shape.name;
    }

    public abstract String getShapeType();

    public abstract void calcShapeArea();

    public abstract Point[] centerShapePointsUpright();

    public abstract boolean pointInShape(Point point);

    @SuppressWarnings("WeakerAccess")
    public void calcPerimeter() {
        perimeter = new BigDecimal(0);
        for (Line line : lines) {
            perimeter = perimeter.add(line.distance);
        }
    }

    //Takes in points just for the sake of determining the number of points that should be in the shape.
    protected Shape(Point[] points) {
        this.points = points;
    }

    @SuppressWarnings("WeakerAccess")
    public void initializeShape(Point[] points) {
        this.points = PointPredicates.correctPointSize(points, this.points.length);
        this.lines = LinePredicates.computeAllLinesAndSortPoints(points);
        calcPerimeter();
        calcShapeArea();
    }


    @SuppressWarnings("WeakerAccess")
    public Point[] getYIntercepts() {
        Collection<Point> yIntercepts = new ArrayList<Point>();
        for (Point point : points) {
            if (point.xCoordinate.equals(zero)) {
                yIntercepts.add(point);
            }
        }
        for (Line line : lines) {
            line.getXIntercept();
        }
        return yIntercepts.toArray(new Point[yIntercepts.size()]);
    }

    @SuppressWarnings("WeakerAccess")
    public Point[] getXIntercepts() {
        Collection<Point> xIntercepts = new ArrayList<Point>();
        for (Point point : points) {
            if (point.yCoordinate.equals(zero)) {
                xIntercepts.add(point);
            }
        }
        for (Line line : lines) {
            line.getYIntercept();
        }
        return xIntercepts.toArray(new Point[xIntercepts.size()]);
    }

    public Point[][] getIntercepts() {
        Point[] xIntercepts = getXIntercepts();
        Point[] yIntercepts = getYIntercepts();
        return new Point[][]{xIntercepts, yIntercepts};
    }

    @Override
    public String toString() {
        String shapeString = "";
        shapeString += "\n\n" +
                "" + getShapeType() + "\n";
        for (Line line : lines) {
            shapeString += line.toString() + "\n";
        }
        shapeString += "Area: " + area.toString();
        return shapeString;
    }

    public Shape rotate(Point centerOfRotation, double rotationInDegrees) {
        //This is the only method so far that alters the existing object rather than returning a copied object with the rotation
        for (int i = 0; i < points.length; i++) {
            Point rotate = points[i].rotate(centerOfRotation, rotationInDegrees);
            points[i] = rotate;
        }
        this.lines = LinePredicates.computeAllLinesFromPoints(points);
        calcPerimeter();
        return this;
    }
}

