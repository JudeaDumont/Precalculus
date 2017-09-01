package Precalculus;

import java.util.ArrayList;

/**
 * Created by Owner on 7/12/2017.
 */
public abstract class Rectangle extends Shape {

    public static Rectangle createInstance(Point[] points) {
        Rectangle rectangle = null;
        RectangleShapeType type = RectanglePredicates.determineRectangleType(LinePredicates.computeAllLinesFromPoints(points));
        switch (type) {
            case Square:
                rectangle = new Square(points);
                break;
            case Rectangle:
                rectangle = new RectangleConcrete(points);
                break;
        }
        return rectangle;
    }

    protected Rectangle(Point[] points) {
        super(new Point[4]);
        initializeShape(points);
        calcShapeArea();
    }

    public Line[] getMeridians() {
        Line[] meridians = new Line[4];

        for (int i = 0; i < lines.length; i++) {
            for (Point point : points) {
                if (!point.equals(lines[i].point1) && !point.equals(lines[i].point2)) {
                    meridians[i] = new Line(point, lines[i].getMidPoint(), 0);
                }
            }
        }
        return meridians;
    }

    public Line[] getDiagnalLines() {
        ArrayList<Line> diags = new ArrayList<Line>();
        for (int i = 0; i < points.length; i++) {
            for (int i1 = 0; i1 < points.length; i1++) {
                for (int i2 = 0; i2 < lines.length; i2++) {
                    if (!points[i].equals(points[i1]) && (lines[i2].point1.equals(points[i]) && !lines[i2].point2.equals(points[i1]))) {
                        boolean twoPointsShareCommonLine = false;
                        for (int i3 = 0; i3 < lines.length; i3++) {
                            if ((lines[i3].point1.equals(points[i]) && lines[i3].point2.equals(points[i1])) || (lines[i3].point1.equals(points[i1]) && lines[i3].point2.equals(points[i]))) {
                                twoPointsShareCommonLine = true;
                            }
                        }
                        if (!twoPointsShareCommonLine) {
                            diags.add(new Line(new Point(points[i]), new Point(points[i1]), 0));
                        }
                    }
                }
            }
        }
        return diags.toArray(new Line[diags.size()]);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
