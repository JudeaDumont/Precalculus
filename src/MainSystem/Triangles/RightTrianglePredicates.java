package MainSystem.Triangles;

import MainSystem.Angles.AnglePredicates;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.math.MathContext;

public class RightTrianglePredicates {
    public static boolean pointInRightTriangle(Point point, Point[] pointsOfRightTriangle) {
        boolean inShape = false;
        int indexOfRightAnglePoint = 0;
        boolean angleWasNegative = false;
        for (int i = 0; i < pointsOfRightTriangle.length; i++) {
            double differenceInOtherAngles =
                    (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangle[(i + 1) % 3].xCoordinate.subtract(pointsOfRightTriangle[i].xCoordinate).doubleValue(),
                            pointsOfRightTriangle[(i + 1) % 3].yCoordinate.subtract(pointsOfRightTriangle[i].yCoordinate).doubleValue()))) -
                            (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangle[(i + 2) % 3].xCoordinate.subtract(pointsOfRightTriangle[i].xCoordinate).doubleValue(),
                                    pointsOfRightTriangle[(i + 2) % 3].yCoordinate.subtract(pointsOfRightTriangle[i].yCoordinate).doubleValue())));
            angleWasNegative = Math.round(differenceInOtherAngles) == -90;
            if (Math.round(differenceInOtherAngles) == 90 || angleWasNegative) {
                indexOfRightAnglePoint = i;
                break;
            }
        }


        double angle1 =
                (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangle[(indexOfRightAnglePoint + 1) % 3].xCoordinate
                        .subtract(pointsOfRightTriangle[indexOfRightAnglePoint].xCoordinate).doubleValue(),
                        pointsOfRightTriangle[(indexOfRightAnglePoint + 1) % 3].yCoordinate
                                .subtract(pointsOfRightTriangle[indexOfRightAnglePoint].yCoordinate).doubleValue())));


        double angle2 =
                (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangle[(indexOfRightAnglePoint + 2) % 3].xCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].xCoordinate).doubleValue(),
                        pointsOfRightTriangle[(indexOfRightAnglePoint + 2) % 3].yCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].yCoordinate).doubleValue())));
        boolean angle2Trueangle1false = angle1 < angle2;
        double angleOfAugmentation = angle2Trueangle1false ? angle2 : angle1;
        double angleOfRotation = 450 - angleOfAugmentation;

        Point[] modPoints = new Point[pointsOfRightTriangle.length];

        for (int i = 0; i < pointsOfRightTriangle.length; i++) {
            modPoints[i] = new Point(pointsOfRightTriangle[i].xCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].xCoordinate),
                    pointsOfRightTriangle[i].yCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].yCoordinate));
        }
        angleOfRotation *= 10;
        angleOfRotation = Math.round(angleOfRotation);
        angleOfRotation /= 10;
        for (int i = 0; i < modPoints.length; i++) {
            modPoints[i] = modPoints[i].rotate(new Point(0, 0), angleOfRotation);
        }

        point = new Point(point.xCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].xCoordinate),
                point.yCoordinate.subtract(pointsOfRightTriangle[indexOfRightAnglePoint].yCoordinate));

        point = point.rotate(new Point(0, 0), angleWasNegative ? angleOfRotation : angleOfRotation);


        BigDecimal maxX = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal maxY = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal minX = new BigDecimal(Double.MAX_VALUE);
        BigDecimal minY = new BigDecimal(Double.MAX_VALUE);
        for (Point point1 : modPoints) {
            if (point1.xCoordinate.compareTo(maxX) > 0) {
                maxX = new BigDecimal(point1.xCoordinate.toString());
            }
            if (point1.yCoordinate.compareTo(maxY) > 0) {
                maxY = new BigDecimal(point1.yCoordinate.toString());
            }
            if (point1.xCoordinate.compareTo(minX) < 0) {
                minX = new BigDecimal(point1.xCoordinate.toString());
            }
            if (point1.yCoordinate.compareTo(minY) < 0) {
                minY = new BigDecimal(point1.yCoordinate.toString());
            }
        }

        if (point.xCoordinate.compareTo(maxX) <= 0 && point.xCoordinate.compareTo(minX) >= 0 &&
                point.yCoordinate.compareTo(maxY) <= 0 && point.yCoordinate.compareTo(minY) >= 0) {
            BigDecimal yMagnitudePoint = angle2Trueangle1false ? modPoints[(indexOfRightAnglePoint + 2) % 3].yCoordinate :
                    modPoints[(indexOfRightAnglePoint + 1) % 3].yCoordinate;
            BigDecimal xMagnitudePoint = !angle2Trueangle1false ? modPoints[(indexOfRightAnglePoint + 2) % 3].xCoordinate :
                    modPoints[(indexOfRightAnglePoint + 1) % 3].xCoordinate;
            boolean yTruexFalse = yMagnitudePoint.compareTo(xMagnitudePoint) > 0;
            BigDecimal xRatio = null;
            BigDecimal yRatio = null;
            BigDecimal bound = null;
            if (yTruexFalse) {
                yRatio = xMagnitudePoint.divide(yMagnitudePoint, new MathContext(SystemGlobal.CALC_PRECISION));
                xRatio = new BigDecimal(1);
                bound = xMagnitudePoint;
            } else {
                yRatio = new BigDecimal(1);
                xRatio = yMagnitudePoint.divide(xMagnitudePoint, new MathContext(SystemGlobal.CALC_PRECISION));
                bound = yMagnitudePoint;
            }
            if ((point.xCoordinate.multiply(xRatio).add(point.yCoordinate.multiply(yRatio)).divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION)))
                    .compareTo(bound) <= 0) {
                inShape = true;
            }
        }
        return inShape;
    }
}
