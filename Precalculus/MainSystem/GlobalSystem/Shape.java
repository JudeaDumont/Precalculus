package MainSystem.GlobalSystem;

import MainSystem.Lines.Line;
import MainSystem.Lines.LinePredicates;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static MainSystem.GlobalSystem.SystemGlobal.zero;

/**
 * Created by Owner on 7/10/2017.
 */
public abstract class Shape {
    public Point[] points;
    public Line[] lines;
    public BigDecimal area;
    public BigDecimal perimeter;
    public String name = "";
    public abstract String getShapeType();

    public abstract void calcShapeArea();

    public abstract Point[] centerShapePointsUpright();

    public abstract boolean pointInShape(Point point);

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

    public void initializeShape(Point[] points) {
        this.points = PointPredicates.correctPointSize(points, this.points.length);
        this.lines = LinePredicates.computeAllLinesFromPoints(points);
        calcPerimeter();
        calcShapeArea();
    }


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

    public void rotate(Point centerOfRotation, double rotationInDegrees) {
        //This is the only method so far that alters the existing object rather than returning a copied object with the rotation
        for (int i = 0; i < points.length; i++) {
            Point rotate = points[i].rotate(centerOfRotation, rotationInDegrees);
            points[i] = rotate;
        }
        this.lines = LinePredicates.computeAllLinesFromSortedPoints(points);
        calcPerimeter();
    }
}

