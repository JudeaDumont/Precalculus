package MainSystem.Triangle;

import MainSystem.Line.Line;
import MainSystem.Line.LinePredicates;
import MainSystem.Point.Point;
import MainSystem.Point.PointPredicates;

public abstract class Triangle extends MainSystem.SystemGlobal.Shape {
    //Factory Method
    @SuppressWarnings("WeakerAccess")
    public TriangleShapeType type;

    public static Triangle createInstance(Point[] threePoints) {
        threePoints = PointPredicates.correctPointSize(threePoints, 3);
        TriangleShapeType type = TrianglePredicates.determineTriangleType(LinePredicates.computeAllLinesAndSortPoints(threePoints));
        Triangle specificTriangle = null;
        switch (type) {
            case Equalateral:
                specificTriangle = new EqualateralTriangle(threePoints);
                break;
            case Right:
                specificTriangle = new RightTriangle(threePoints);
                break;
            case Isoselece:
                specificTriangle = new IsoceleceTriangle(threePoints);
                break;
            case Scalene:
                specificTriangle = new ScaleneTriangle(threePoints);
                break;
            case RightIsoselece:
                specificTriangle = new RightIsoseleceTriangle(threePoints);

        }
        return specificTriangle;
    }

    Triangle(Point[] threePoints) {
        super(new Point[3]);
        initializeShape(threePoints);
    }

    @Override
    public String toString() {
        return PointPredicates.getPointString(this.points) + "\nArea: " + this.area.toString() + "\nShapeType: " + this.getShapeType();
    }

    @SuppressWarnings("WeakerAccess")
    public Line[] getMeridians() {
        Line[] meridians = new Line[3];

        for (int i = 0; i < lines.length; i++) {
            for (Point point : points) {
                Line linesI = lines[i];
                if (!point.equals(linesI.point1) && !point.equals(linesI.point2)) {
                    meridians[i] = new Line(point, linesI.getMidPoint());
                }
            }
        }
        return meridians;
    }
}
