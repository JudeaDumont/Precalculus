package MainSystem.Circle;

import MainSystem.Angles.AnglePredicates;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Circle extends MainSystem.GlobalSystem.Shape {
    public Point center = new Point(0, 0);
    public double radius = 0;

    @Override
    public void calcPerimeter() {
        perimeter = new BigDecimal(2).multiply
                (new BigDecimal(MainSystem.GlobalSystem.SystemGlobal.PI)).multiply(new BigDecimal(radius));
    }

    public Circle(Point center, double radius, long precision) {
        super(new Point[]{});
        //bypass point validation with intializeShape
        this.center = center;
        this.radius = radius;
        this.points = getPointsOfACircle(precision);
        calcShapeArea();
        calcPerimeter();
    }

    @Override
    public String toString() {
        return "\nRadius:" +
                radius +
                "\nPerimeter:" +
                perimeter +
                "\nCenter:" +
                center.toString() +
                "\nPoints:" +
                PointPredicates.getPointString(points);
    }

    public Point[] getPointsOfACircle(long precision) {
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collection<Double> divisionsOfRadius = new ArrayList<>();
        for (long i = 0; i < precision; i++) {
            Point point = (calculatePointOfCircle(radius / (i + 1)));

            if (!PointPredicates.doesDuplicateExist
                    (circlePoints, new Point
                            (point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                    point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist
                    (circlePoints, new Point
                            (-point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                    -point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (-point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                -point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (-point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (-point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            -point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (point.xCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                -point.yCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (-point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            -point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (-point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                -point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (-point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (-point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                            -point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point
                        (point.yCoordinate.doubleValue() + center.xCoordinate.doubleValue(),
                                -point.xCoordinate.doubleValue() + center.yCoordinate.doubleValue()));
            }
        }
        return circlePoints.toArray(new Point[circlePoints.size()]);
    }

    public Point calculatePointOfCircle(double radiusPortion) {
        double remainingRadius = radius - radiusPortion;
        return new Point(
                radiusPortion != 0 ? Math.sqrt(radiusPortion) : 0
                ,
                remainingRadius != 0 ? Math.sqrt(remainingRadius) : 0
        );
    }

    @Override
    public String getShapeType() {
        return null;
    }

    @Override
    public void calcShapeArea() {
        area = new BigDecimal(Math.pow(radius, 2) * MainSystem.GlobalSystem.SystemGlobal.PI);
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        Point modPoint = new Point
                (point.xCoordinate.subtract(center.xCoordinate),
                        point.yCoordinate.subtract(center.yCoordinate));
        double angle = AnglePredicates.getAngleFromPoint(modPoint);
        Point pointOnCircleAtAngle = new Point
                (Math.cos(Math.toRadians(angle)) * radius,
                        Math.sin(Math.toRadians(angle)) * radius);

        boolean pointInCircle = false;

        if (angle > 0 && angle < 90) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) <= 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) <= 0;
        } else if (angle == 90) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (new BigDecimal(0)) == 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) <= 0;
        } else if (angle == 0) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) <= 0 &&
                    modPoint.yCoordinate.compareTo(new BigDecimal(0)) == 0;
        } else if (angle > 90 && angle < 180) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) >= 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) <= 0;
        } else if (angle == 180) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) >= 0 &&
                    modPoint.yCoordinate.compareTo(new BigDecimal(0)) == 0;
        } else if (angle > 180 && angle < 270) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) >= 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) >= 0;
        } else if (angle == 270) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (new BigDecimal(0)) == 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) >= 0;
        } else if (angle > 270 && angle < 360) {
            pointInCircle = modPoint.xCoordinate.compareTo
                    (pointOnCircleAtAngle.xCoordinate) <= 0 &&
                    modPoint.yCoordinate.compareTo(pointOnCircleAtAngle.yCoordinate) >= 0;
        }
        return pointInCircle;
    }
}
