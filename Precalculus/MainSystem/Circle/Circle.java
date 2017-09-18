package MainSystem.Circle;

import MainSystem.Angles.AnglePredicates;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static MainSystem.GlobalSystem.SystemGlobal.PI;

public class Circle extends MainSystem.GlobalSystem.Shape {
    public Point center = new Point(0, 0);
    public double radius = 0;

    @Override
    public void calcPerimeter() {
        perimeter = new BigDecimal(2).multiply
                (new BigDecimal(PI)).multiply(new BigDecimal(radius));
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

    @SuppressWarnings("WeakerAccess")
    public Point[] getPointsOfACircle(long precision) {
        ArrayList<Point> circlePoints = new ArrayList<>();
        for (long i = 0; i < precision; i++) {
            Point point = (calculatePointOfCircle(radius / (i + 1)));

            BigDecimal pointXCoord = point.xCoordinate;
            BigDecimal centerPointXCoord = center.xCoordinate;
            BigDecimal pointYCoord = point.yCoordinate;
            BigDecimal centerPointYCoordinate = center.yCoordinate;
            BigDecimal pointAndCenterPointXSummation = pointXCoord.add(centerPointXCoord);
            BigDecimal pointAndCenterPointYSummation = pointYCoord.add(centerPointYCoordinate);
            BigDecimal pointAndCenterPointXDifference = pointXCoord.negate().add(centerPointXCoord);
            BigDecimal pointAndCenterPointYDifference = pointYCoord.negate().add(centerPointYCoordinate);
            //The following variables have to do with proportionality between X and Y
            //See the output of circle2 in MajorRefactoringRegressionTesting for elaboration
            BigDecimal yPointAndXCenterSummation = pointYCoord.add(centerPointXCoord);
            BigDecimal xPointAndYCenterSummation = pointXCoord.add(centerPointYCoordinate);
            BigDecimal yPointAndXCenterDifference = pointYCoord.negate().add(centerPointXCoord);
            BigDecimal xPointAndYCenterDifference = pointXCoord.negate().add(centerPointYCoordinate);

            if (!PointPredicates.doesDuplicateExist
                    (circlePoints, new Point
                            (pointAndCenterPointXSummation,
                                    pointAndCenterPointYSummation))) {
                circlePoints.add(new Point
                        (pointAndCenterPointXSummation,
                                pointAndCenterPointYSummation));
            }
            if (!PointPredicates.doesDuplicateExist
                    (circlePoints, new Point
                            (pointAndCenterPointXDifference,
                                    pointAndCenterPointYDifference))) {
                circlePoints.add(new Point
                        (pointAndCenterPointXDifference,
                                pointAndCenterPointYDifference));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (pointAndCenterPointXDifference,
                            pointAndCenterPointYSummation))) {
                circlePoints.add(new Point
                        (pointAndCenterPointXDifference,
                                pointAndCenterPointYSummation));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (pointAndCenterPointXSummation,
                            pointAndCenterPointYDifference))) {
                circlePoints.add(new Point
                        (pointAndCenterPointXSummation,
                                pointAndCenterPointYDifference));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (yPointAndXCenterSummation,
                            xPointAndYCenterSummation))) {
                circlePoints.add(new Point
                        (yPointAndXCenterSummation,
                                xPointAndYCenterSummation));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (yPointAndXCenterDifference,
                            xPointAndYCenterDifference))) {
                circlePoints.add(new Point
                        (yPointAndXCenterDifference,
                                xPointAndYCenterDifference));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (yPointAndXCenterDifference,
                            xPointAndYCenterSummation))) {
                circlePoints.add(new Point
                        (yPointAndXCenterDifference,
                                xPointAndYCenterSummation));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point
                    (yPointAndXCenterSummation,
                            xPointAndYCenterDifference))) {
                circlePoints.add(new Point
                        (yPointAndXCenterSummation,
                                xPointAndYCenterDifference));
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
        area = new BigDecimal(Math.pow(radius, 2) * PI);
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

        BigDecimal xCoordinate = modPoint.xCoordinate;
        BigDecimal pointOnCircleAtAngleXCoordinate = pointOnCircleAtAngle.xCoordinate;
        BigDecimal yCoordinate = modPoint.yCoordinate;
        BigDecimal pointOnCircleAtAngleYCoordinate = pointOnCircleAtAngle.yCoordinate;
        int xCoordinateComparedToPointAtAngle = xCoordinate.compareTo(pointOnCircleAtAngleXCoordinate);
        int yCoordinateComparedToPointAtAngle = yCoordinate.compareTo(pointOnCircleAtAngleYCoordinate);
        boolean yCoordComparedToPointAtAngleNonPositive = yCoordinateComparedToPointAtAngle <= 0;
        boolean xCoordComparedToPointAtAngleNonPositive = xCoordinateComparedToPointAtAngle <= 0;
        if (angle > 0 && angle < 90) {
            pointInCircle = xCoordComparedToPointAtAngleNonPositive &&
                    yCoordComparedToPointAtAngleNonPositive;
        } else {
            int xPositiveOrNegative = xCoordinate.compareTo
                    (SystemGlobal.zero);
            boolean xIsZero = xPositiveOrNegative == 0;
            if (angle == 90) {
                pointInCircle = xIsZero &&
                        yCoordComparedToPointAtAngleNonPositive;
            } else {
                int yPositiveOrNegative = yCoordinate.compareTo(SystemGlobal.zero);
                boolean yIsZero = yPositiveOrNegative == 0;
                if (angle == 0) {
                    pointInCircle = xCoordComparedToPointAtAngleNonPositive &&
                            yIsZero;
                } else {
                    boolean xCoordComparedToAnglePointNonNegative = xCoordinateComparedToPointAtAngle >= 0;
                    if (angle > 90 && angle < 180) {
                        pointInCircle = xCoordComparedToAnglePointNonNegative &&
                                yCoordComparedToPointAtAngleNonPositive;
                    } else if (angle == 180) {
                        pointInCircle = xCoordComparedToAnglePointNonNegative &&
                                yIsZero;
                    } else {
                        boolean yCoordComparedToAnglePointNonNegative = yCoordinateComparedToPointAtAngle >= 0;
                        if (angle > 180 && angle < 270) {
                            pointInCircle = xCoordComparedToAnglePointNonNegative &&
                                    yCoordComparedToAnglePointNonNegative;
                        } else if (angle == 270) {
                            pointInCircle = xIsZero &&
                                    yCoordComparedToAnglePointNonNegative;
                        } else if (angle > 270 && angle < 360) {
                            pointInCircle = xCoordComparedToPointAtAngleNonPositive &&
                                    yCoordComparedToAnglePointNonNegative;
                        }
                    }
                }
            }
        }
        return pointInCircle;
    }
}
