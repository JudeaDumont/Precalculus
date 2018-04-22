package MainSystem.Triangle;

import MainSystem.AnglePredicates.AnglePredicates;
import MainSystem.Line.LinePredicates;
import MainSystem.Point.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.SystemGlobal.SystemGlobal.*;

public class RightTriangle extends Triangle {

    RightTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Right;
    }

    @Override
    public String getShapeType() {
        return "RightTriangle";
    }

    @Override
    public void calcShapeArea() {
        BigDecimal[] lengths = LinePredicates.getLineLengths(lines);
        BigDecimal length0 = lengths[0];
        BigDecimal length1 = lengths[1];
        BigDecimal divisor = new BigDecimal(2);
        BigDecimal lengthProduct = length0.multiply(length1);
        area = lengthProduct.divide(divisor, calcPrecisionMathContext)
                .round(new MathContext(lengthProduct.divide(divisor, calcPrecisionMathContext)
                        .toBigInteger().toString().length() + EQUALITY_PRECISION));
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        boolean inShape = false;
        int indexOfRightAnglePoint = 0;

        int length = points.length;
        for (int i = 0; i < length; i++) {
            Point pointDif1 = points[(i + 1) % 3];
            Point pointDif2 = points[(i + 2) % 3];
            Point pointI = points[i];
            BigDecimal pointIXCoordinate = pointI.xCoordinate;
            BigDecimal pointIYCoordinate = pointI.yCoordinate;
            double differenceInOtherAngles =
                    (AnglePredicates.getAngleFromPoint(new Point(pointDif1.xCoordinate.subtract(pointIXCoordinate).doubleValue(),
                            pointDif1.yCoordinate.subtract(pointIYCoordinate).doubleValue()))) -
                            (AnglePredicates.getAngleFromPoint(new Point(pointDif2.xCoordinate.subtract(pointIXCoordinate).doubleValue(),
                                    pointDif2.yCoordinate.subtract(pointIYCoordinate).doubleValue())));
            if (Math.round(differenceInOtherAngles) == 90 || Math.round(differenceInOtherAngles) == -90) {
                indexOfRightAnglePoint = i;
                break;
            }
        }

        Point notRightAnglePoint1 = points[(indexOfRightAnglePoint + 1) % 3];
        Point notRightAnglePoint2 = points[(indexOfRightAnglePoint + 2) % 3];
        Point rightAnglePoint = points[indexOfRightAnglePoint];
        BigDecimal rightAnglePointX = rightAnglePoint.xCoordinate;
        BigDecimal rightAnglePointY = rightAnglePoint.yCoordinate;
        double angle1 =
                (AnglePredicates.getAngleFromPoint(new Point(notRightAnglePoint1.xCoordinate.subtract(rightAnglePointX).doubleValue(),
                        notRightAnglePoint1.yCoordinate.subtract(rightAnglePoint.yCoordinate).doubleValue())));

        double angle2 =
                (AnglePredicates.getAngleFromPoint(new Point(notRightAnglePoint2.xCoordinate.subtract(rightAnglePointX).doubleValue(),
                        notRightAnglePoint2.yCoordinate.subtract(rightAnglePointY).doubleValue())));
        boolean angle2Trueangle1false = angle1 < angle2;
        double angleOfAugmentation = angle2Trueangle1false ? angle2 : angle1;
        double angleOfRotation = 450 - angleOfAugmentation;

        Point[] modPoints = new Point[length];

        for (int i = 0; i < length; i++) {
            Point pointI = points[i];
            modPoints[i] = new Point(pointI.xCoordinate.subtract(rightAnglePointX), pointI.yCoordinate.subtract(rightAnglePointY));
        }

        for (int i = 0; i < modPoints.length; i++) {
            modPoints[i] = modPoints[i].rotate(zeroPoint, angleOfRotation);
        }

        point = new Point(point.xCoordinate.subtract(rightAnglePointX), point.yCoordinate.subtract(rightAnglePointY));
        point = point.rotate(zeroPoint, angleOfRotation);
        BigDecimal yCoordinate1 = point.yCoordinate;
        BigDecimal pointXCoordinate1 = point.xCoordinate;

        BigDecimal maxX = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal maxY = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal minX = new BigDecimal(Double.MAX_VALUE);
        BigDecimal minY = new BigDecimal(Double.MAX_VALUE);
        for (Point point1 : modPoints) {
            BigDecimal xCoordinate = point1.xCoordinate;
            BigDecimal yCoordinate = point1.yCoordinate;
            String xCoordString = xCoordinate.toString();
            String yCoordString = yCoordinate.toString();
            if (xCoordinate.compareTo(maxX) > 0) {
                maxX = new BigDecimal(xCoordString);
            }
            if (yCoordinate.compareTo(maxY) > 0) {
                maxY = new BigDecimal(yCoordString);
            }
            if (xCoordinate.compareTo(minX) < 0) {
                minX = new BigDecimal(xCoordString);
            }
            if (yCoordinate.compareTo(minY) < 0) {
                minY = new BigDecimal(yCoordString);
            }
        }

        if (pointXCoordinate1.compareTo(maxX) <= 0 && pointXCoordinate1.compareTo(minX) >= 0 &&
                yCoordinate1.compareTo(maxY) <= 0 && yCoordinate1.compareTo(minY) >= 0) {
            notRightAnglePoint2 = modPoints[(indexOfRightAnglePoint + 2) % 3];
            notRightAnglePoint1 = modPoints[(indexOfRightAnglePoint + 1) % 3];
            BigDecimal yMagnitudePoint = angle2Trueangle1false ? notRightAnglePoint2.yCoordinate : notRightAnglePoint1.yCoordinate;
            BigDecimal xMagnitudePoint = !angle2Trueangle1false ? notRightAnglePoint2.xCoordinate : notRightAnglePoint1.xCoordinate;
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
            if (((pointXCoordinate1.multiply(xRatio).add(yCoordinate1.multiply(yRatio))).divide(new BigDecimal(1), equalityPrecisionMathContext)).compareTo(bound) <= 0) {
                inShape = true;
            }
        }
        return inShape;
    }
}
