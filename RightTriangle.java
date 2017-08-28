package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class RightTriangle extends Triangle {

    protected RightTriangle(Point[] points) {
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
        area = lengths[0].multiply(lengths[1]).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION))
                .round(new MathContext(lengths[0].multiply(lengths[1]).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION))
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

        for (int i = 0; i < points.length; i++) {
            double differenceInOtherAngles =
                    (AnglePredicates.getAngleFromPoint(new Point(points[(i + 1) % 3].xCoordinate.subtract(points[i].xCoordinate).doubleValue(),
                            points[(i + 1) % 3].yCoordinate.subtract(points[i].yCoordinate).doubleValue()))) -
                            (AnglePredicates.getAngleFromPoint(new Point(points[(i + 2) % 3].xCoordinate.subtract(points[i].xCoordinate).doubleValue(),
                                    points[(i + 2) % 3].yCoordinate.subtract(points[i].yCoordinate).doubleValue())));
            if (Math.round(differenceInOtherAngles) == 90 || Math.round(differenceInOtherAngles) == -90) {
                indexOfRightAnglePoint = i;
                break;
            }
        }

        double angle1 =
                (AnglePredicates.getAngleFromPoint(new Point(points[(indexOfRightAnglePoint + 1) % 3].xCoordinate.subtract(points[indexOfRightAnglePoint].xCoordinate).doubleValue(),
                        points[(indexOfRightAnglePoint + 1) % 3].yCoordinate.subtract(points[indexOfRightAnglePoint].yCoordinate).doubleValue())));

        double angle2 =
                (AnglePredicates.getAngleFromPoint(new Point(points[(indexOfRightAnglePoint + 2) % 3].xCoordinate.subtract(points[indexOfRightAnglePoint].xCoordinate).doubleValue(),
                        points[(indexOfRightAnglePoint + 2) % 3].yCoordinate.subtract(points[indexOfRightAnglePoint].yCoordinate).doubleValue())));
        boolean angle2Trueangle1false = angle1 < angle2;
        double angleOfAugmentation = angle2Trueangle1false ? angle2 : angle1;
        double angleOfRotation = 450 - angleOfAugmentation;

        Point[] modPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            modPoints[i] = new Point(points[i].xCoordinate.subtract(points[indexOfRightAnglePoint].xCoordinate), points[i].yCoordinate.subtract(points[indexOfRightAnglePoint].yCoordinate));
        }

        for (int i = 0; i < modPoints.length; i++) {
            modPoints[i] = modPoints[i].rotate(new Point(0, 0), angleOfRotation);
        }

        point = new Point(point.xCoordinate.subtract(points[indexOfRightAnglePoint].xCoordinate), point.yCoordinate.subtract(points[indexOfRightAnglePoint].yCoordinate));
        point = point.rotate(new Point(0, 0), angleOfRotation);

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
            BigDecimal yMagnitudePoint = angle2Trueangle1false ? modPoints[(indexOfRightAnglePoint + 2) % 3].yCoordinate : modPoints[(indexOfRightAnglePoint + 1) % 3].yCoordinate;
            BigDecimal xMagnitudePoint = !angle2Trueangle1false ? modPoints[(indexOfRightAnglePoint + 2) % 3].xCoordinate : modPoints[(indexOfRightAnglePoint + 1) % 3].xCoordinate;
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
            if (((point.xCoordinate.multiply(xRatio).add(point.yCoordinate.multiply(yRatio))).divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))).compareTo(bound) <= 0) {
                inShape = true;
            }
        }
        return inShape;
    }
}
