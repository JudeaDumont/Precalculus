package MainSystem.Triangles;

import MainSystem.Angles.AnglePredicates;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.GlobalSystem.SystemGlobal.calcPrecisionMathContext;
import static MainSystem.GlobalSystem.SystemGlobal.equalityPrecisionMathContext;
import static MainSystem.GlobalSystem.SystemGlobal.zeroPoint;

@SuppressWarnings("WeakerAccess")
public class RightTrianglePredicates {
    public static boolean pointInRightTriangle(Point point, Point[] pointsOfRightTriangle) {
        boolean inShape = false;
        int indexOfRightAnglePoint = 0;
        boolean angleWasNegative = false;
        int length = pointsOfRightTriangle.length;
        for (int i = 0; i < length; i++) {
            Point pointsOfRightriangleI = pointsOfRightTriangle[i];
            BigDecimal pointsOfRightTriangle1XCoordinate = pointsOfRightriangleI.xCoordinate;
            BigDecimal pointsOfRightTriangle1YCoordinate = pointsOfRightriangleI.yCoordinate;
            Point pointsOfRightTriangleOffset1 = pointsOfRightTriangle[(i + 1) % 3];
            Point pointsOfRightTriangleOffset2 = pointsOfRightTriangle[(i + 2) % 3];
            double differenceInOtherAngles =
                    (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangleOffset1.xCoordinate.subtract(pointsOfRightTriangle1XCoordinate),
                            pointsOfRightTriangleOffset1.yCoordinate.subtract(pointsOfRightTriangle1YCoordinate)))) -
                            (AnglePredicates.getAngleFromPoint(new Point(pointsOfRightTriangleOffset2.xCoordinate.subtract(pointsOfRightTriangle1XCoordinate),
                                    pointsOfRightTriangleOffset2.yCoordinate.subtract(pointsOfRightTriangle1YCoordinate))));
            angleWasNegative = Math.round(differenceInOtherAngles) == -90;
            if (Math.round(differenceInOtherAngles) == 90 || angleWasNegative) {
                indexOfRightAnglePoint = i;
                break;
            }
        }

        Point rightAnglePointOffset1 = pointsOfRightTriangle[(indexOfRightAnglePoint + 1) % 3];
        Point rightAnglePointOffset2 = pointsOfRightTriangle[(indexOfRightAnglePoint + 2) % 3];
        Point rightAnglePoint = pointsOfRightTriangle[indexOfRightAnglePoint];
        BigDecimal rightAnglePointX = rightAnglePoint.xCoordinate;
        BigDecimal rightAnglePointY = rightAnglePoint.yCoordinate;
        double angle1 =
                (AnglePredicates.getAngleFromPoint(new Point(rightAnglePointOffset1.xCoordinate
                        .subtract(rightAnglePointX),
                        rightAnglePointOffset1.yCoordinate
                                .subtract(rightAnglePointY))));

        double angle2 =
                (AnglePredicates.getAngleFromPoint(new Point(rightAnglePointOffset2.xCoordinate.subtract(rightAnglePointX),
                        rightAnglePointOffset2.yCoordinate.subtract(rightAnglePointY))));
        boolean angle2Trueangle1false = angle1 < angle2;
        double angleOfAugmentation = angle2Trueangle1false ? angle2 : angle1;
        double angleOfRotation = 450 - angleOfAugmentation;

        Point[] modPoints = new Point[length];

        for (int i = 0; i < length; i++) {
            Point pointsI = pointsOfRightTriangle[i];
            modPoints[i] = new Point(pointsI.xCoordinate.subtract(rightAnglePointX),
                    pointsI.yCoordinate.subtract(rightAnglePointY));
        }
        angleOfRotation *= 10;
        angleOfRotation = Math.round(angleOfRotation);
        angleOfRotation /= 10;
        for (int i = 0; i < modPoints.length; i++) {
            modPoints[i] = modPoints[i].rotate(zeroPoint, angleOfRotation);
        }

        point = new Point(point.xCoordinate.subtract(rightAnglePointX), point.yCoordinate.subtract(rightAnglePointY));
        point = point.rotate(zeroPoint, angleWasNegative ? angleOfRotation : angleOfRotation);
        BigDecimal pointXCoord = point.xCoordinate;
        BigDecimal pointYCoord = point.yCoordinate;

        BigDecimal maxX = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal maxY = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal minX = new BigDecimal(Double.MAX_VALUE);
        BigDecimal minY = new BigDecimal(Double.MAX_VALUE);
        for (Point point1 : modPoints) {
            BigDecimal xCoordinate = point1.xCoordinate;
            BigDecimal yCoordinate = point1.yCoordinate;
            if (xCoordinate.compareTo(maxX) > 0) {
                maxX = new BigDecimal(xCoordinate.toString());
            }
            if (yCoordinate.compareTo(maxY) > 0) {
                maxY = new BigDecimal(yCoordinate.toString());
            }
            if (xCoordinate.compareTo(minX) < 0) {
                minX = new BigDecimal(xCoordinate.toString());
            }
            if (yCoordinate.compareTo(minY) < 0) {
                minY = new BigDecimal(yCoordinate.toString());
            }
        }

        if (pointXCoord.compareTo(maxX) <= 0 && pointXCoord.compareTo(minX) >= 0 &&
                pointYCoord.compareTo(maxY) <= 0 && pointYCoord.compareTo(minY) >= 0) {
            rightAnglePointOffset1 = modPoints[(indexOfRightAnglePoint + 1) % 3];
            rightAnglePointOffset2 = modPoints[(indexOfRightAnglePoint + 2) % 3];
            BigDecimal yMagnitudePoint = angle2Trueangle1false ? rightAnglePointOffset2.yCoordinate : rightAnglePointOffset1.yCoordinate;
            BigDecimal xMagnitudePoint = !angle2Trueangle1false ? rightAnglePointOffset2.xCoordinate : rightAnglePointOffset1.xCoordinate;
            boolean yTruexFalse = yMagnitudePoint.compareTo(xMagnitudePoint) > 0;
            BigDecimal xRatio;
            BigDecimal yRatio;
            BigDecimal bound;
            if (yTruexFalse) {
                yRatio = xMagnitudePoint.divide(yMagnitudePoint, calcPrecisionMathContext);
                xRatio = new BigDecimal(1);
                bound = xMagnitudePoint;
            } else {
                yRatio = new BigDecimal(1);
                xRatio = yMagnitudePoint.divide(xMagnitudePoint, calcPrecisionMathContext);
                bound = yMagnitudePoint;
            }
            if ((pointXCoord.multiply(xRatio).add(pointYCoord.multiply(yRatio)).divide(new BigDecimal(1), equalityPrecisionMathContext))
                    .compareTo(bound) <= 0) {
                inShape = true;
            }
        }
        return inShape;
    }
}
