package MainSystem.Triangles;

import MainSystem.Angles.AnglePredicates;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Lines.Line;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.GlobalSystem.SystemGlobal.*;

public class IsoceleceTriangle extends Triangle {

    IsoceleceTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Isoselece;
    }

    @Override
    public String getShapeType() {
        return "IsoceleceTriangle";
    }

    @Override
    public void calcShapeArea() {
        Line[] equalLines = new Line[2];
        int linesLength = lines.length;
        for (int i = 0; i < linesLength; i++) {
            for (int i1 = 0; i1 < linesLength; i1++) {
                Line line = lines[i];
                Line line1 = lines[i1];
                if (i != i1 && line.distance.equals(line1.distance)) {
                    equalLines = new Line[]{line, line1};
                    break;
                }
            }
        }
        Point commonPoint;
        Line equalLine = equalLines[0];
        Line equalLine1 = equalLines[1];
        Point point1 = equalLine.point1;
        Point point2 = equalLine1.point2;
        if (point1.equals(equalLine1.point1) || point1.equals(point2)) {
            commonPoint = point1;
        } else {
            commonPoint = equalLine.point2;
        }
        for (int i = 0; i < linesLength; i++) {
            for (Point point : points) {
                Line line = lines[i];
                if (!point.equals(line.point1) && !point.equals(line.point2) && point.equals(commonPoint)) {
                    BigDecimal divisor = new BigDecimal(2);
                    BigDecimal divide = line.distance.multiply(new Line(point, line.getMidPoint(), 1).distance).divide(divisor);
                    area = divide
                            .round(new MathContext(divide.toBigInteger().toString().length() + EQUALITY_PRECISION));
                    break;
                }
            }
        }
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {

        Point[] pointPairOfOddSide = new Point[2];
        Point points0 = points[0];
        Point points1 = points[1];
        Point points2 = points[2];
        Line side1 = new Line(points0, points1);
        Line side2 = new Line(points1, points2);
        Line side3 = new Line(points2, points0);
        //Get Odd Side of Isocelece
        BigDecimal divisor = new BigDecimal(1);
        BigDecimal side1Distance = side1.distance.divide(divisor, equalityPrecisionMathContext);
        BigDecimal side2Distance = side2.distance.divide(divisor, equalityPrecisionMathContext);
        BigDecimal side3Distance = side3.distance.divide(divisor, equalityPrecisionMathContext);
        Line oddSide = side1Distance
                .equals(side2Distance
                ) ? side3 : side2Distance
                .equals(side3Distance
                ) ? side1 : side3Distance
                .equals(side1Distance
                ) ? side2 : null;
        if (oddSide == null) {
            throw new NullPointerException();
        }

        pointPairOfOddSide = new Point[]{new Point(oddSide.point1), new Point(oddSide.point2)};
        Point[] modpoints = new Point[3];

        //Shift Triangle to one of the points of the side such that the point = 0,0
        BigDecimal xCoordinateOfOddSide = pointPairOfOddSide[0].xCoordinate;
        BigDecimal yCoordinateOfOddSide = pointPairOfOddSide[0].yCoordinate;
        for (int i = 0; i < points.length; i++) {
            Point point1 = points[i];
            modpoints[i] = new Point(point1.xCoordinate.subtract(xCoordinateOfOddSide), point1.yCoordinate.subtract(yCoordinateOfOddSide));
        }
        //Shift point along with modification
        Point point1 = pointPairOfOddSide[0];
        point = new Point(point.xCoordinate.subtract(point1.xCoordinate), point.yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));

        //Shift point of the oddside as well
        Point pointPairOfOddSide1 = pointPairOfOddSide[1];
        Point pointPairOfOddSide0 = pointPairOfOddSide[0];
        pointPairOfOddSide[1] = new Point(pointPairOfOddSide1.xCoordinate.subtract(pointPairOfOddSide0.xCoordinate),
                pointPairOfOddSide1.yCoordinate.subtract(pointPairOfOddSide0.yCoordinate));
        //Shift point of point used for modification to 0,0
        pointPairOfOddSide[0] = new Point(0, 0);

        double angle;
        int nonZeroPoint1 = -1;
        int nonZeroPoint2 = -1;

        //Get angle of the odd side
        angle = AnglePredicates.getAngleFromPoint(pointPairOfOddSide[1]);

        //find non zero points of triangle
        boolean foundNonZero = false;
        int length = modpoints.length;
        for (int i = 0; i < length; i++) {
            boolean pointIsZero = !modpoints[i].equals(zeroPoint);
            if (pointIsZero && !foundNonZero) {
                nonZeroPoint1 = i;
                foundNonZero = true;
            } else if (pointIsZero && foundNonZero) {
                nonZeroPoint2 = i;
            }
        }

        //rotate nonzeros and point and nonzero point of odd point side
        double angleOfAugmentation = 360 - (angle);
        if (angle != 0) {
            modpoints[nonZeroPoint1] = modpoints[nonZeroPoint1].rotate(zeroPoint, angleOfAugmentation);
            modpoints[nonZeroPoint2] = modpoints[nonZeroPoint2].rotate(zeroPoint, angleOfAugmentation);
            pointPairOfOddSide[1] = pointPairOfOddSide[1].rotate(zeroPoint, angleOfAugmentation);
            point = point.rotate(zeroPoint, angleOfAugmentation);
        }

        //if the oddside non zero is negative shift right along with point and odd side, were going for only quandrant one points
        pointPairOfOddSide1 = pointPairOfOddSide[1];
        BigDecimal pointPairOfOddSide1XCoordinate = pointPairOfOddSide1.xCoordinate;
        BigDecimal pointPairOfOddSide1YCoordinate = pointPairOfOddSide1.yCoordinate;
        if (pointPairOfOddSide1XCoordinate.compareTo(zero) < 0) {
            for (int i = 0; i < length; i++) {
                Point modpoint = modpoints[i];
                modpoints[i] = new Point(modpoint.xCoordinate.subtract(pointPairOfOddSide1XCoordinate),
                        modpoint.yCoordinate.subtract(pointPairOfOddSide1YCoordinate));
            }
            for (int i = 0; i < pointPairOfOddSide.length; i++) {
                Point pointPairOfOddSidei = pointPairOfOddSide[i];
                pointPairOfOddSide1 = pointPairOfOddSide[1];
                pointPairOfOddSide[i] = new Point(pointPairOfOddSidei.xCoordinate.subtract(pointPairOfOddSide1.xCoordinate),
                        pointPairOfOddSidei.yCoordinate.subtract(pointPairOfOddSide1.yCoordinate));
            }
            pointPairOfOddSide1 = pointPairOfOddSide[1];
            point = new Point(point.xCoordinate.subtract(pointPairOfOddSide1.xCoordinate),
                    point.yCoordinate.subtract(pointPairOfOddSide1.yCoordinate));
        }

        boolean inShape;
        //get the top point of the triangle
        Point topPoint = new Point(0, new BigDecimal(-Double.MAX_VALUE));
        int indexOfTopPoint = -1;
        for (int i = 0; i < length; i++) {
            Point modpoint = modpoints[i];
            if (modpoint.yCoordinate.compareTo(topPoint.yCoordinate) > 0) {
                topPoint = modpoint;
                indexOfTopPoint = i;
            }
        }

        //if the top point is 0 then the triangle is upside down and we need to rotate 180 and shift right
        if (modpoints[indexOfTopPoint].yCoordinate.divide(divisor, equalityPrecisionMathContext).compareTo(zero) <= 0) {
            for (int i = 0; i < length; i++) {
                modpoints[i] = modpoints[i].rotate(zeroPoint, 180);
            }
            point = point.rotate(zeroPoint, 180);

            for (int i = 0; i < pointPairOfOddSide.length; i++) {
                pointPairOfOddSide[i] = new Point(pointPairOfOddSide[i].rotate(zeroPoint, 180));
            }

            boolean leftPoint = pointPairOfOddSide[1].xCoordinate.compareTo(zero) < 0;
            Point pointPairOfOddSideLeftPoint = pointPairOfOddSide[leftPoint ? 1 : 0];
            BigDecimal pointPairOfOddSideXCoordinate = pointPairOfOddSideLeftPoint.xCoordinate;
            BigDecimal pointPairOfOddSideYCoordinate = pointPairOfOddSideLeftPoint.yCoordinate;
            for (int i = 0; i < length; i++) {
                Point modpoint = modpoints[i];
                modpoints[i] = new Point(modpoint.xCoordinate.subtract(pointPairOfOddSideXCoordinate),
                        modpoint.yCoordinate.subtract(pointPairOfOddSideYCoordinate));
            }
            point = new Point(point.xCoordinate.subtract(pointPairOfOddSideXCoordinate),
                    point.yCoordinate.subtract(pointPairOfOddSideYCoordinate ));

            for (int i = 0; i < 2; i++) {
                Point pointPairOfOddSidei = pointPairOfOddSide[i];
                pointPairOfOddSideLeftPoint = pointPairOfOddSide[leftPoint ? 1 : 0];
                pointPairOfOddSide[i] = new Point(pointPairOfOddSidei.xCoordinate.subtract(pointPairOfOddSideLeftPoint.xCoordinate),
                        pointPairOfOddSidei.yCoordinate.subtract(pointPairOfOddSideLeftPoint.yCoordinate));
            }
        }
        //now that the triangle is definitely in the first quadrant, we can split it into two right angles and send them off including the point to check if the point is in either
        //Get right point
        Point rightPoint = new Point(0, new BigDecimal(-Double.MAX_VALUE));
        int indexOfRightPoint = -1;
        for (int i = 0; i < length; i++) {
            Point modpoint = modpoints[i];
            if (modpoint.xCoordinate.compareTo(rightPoint.xCoordinate) > 0) {
                rightPoint = modpoint;
                indexOfRightPoint = i;
            }
        }

        for (int i = 0; i < length; i++) {
            Point modpoint = modpoints[i];
            if (modpoint.yCoordinate.compareTo(topPoint.yCoordinate) > 0) {
                topPoint = modpoint;
                indexOfTopPoint = i;
            }
        }

        Point midPoint = new Point(modpoints[indexOfRightPoint].xCoordinate.doubleValue() / 2, new BigDecimal(0));
        boolean inTriangle1 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(modpoints[(indexOfTopPoint + 1) % 3]), midPoint, topPoint
                }
        );
        boolean inTriangle2 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(modpoints[(indexOfTopPoint + 2) % 3]), midPoint, topPoint
                }
        );
        inShape = inTriangle1 || inTriangle2;
        return inShape;
    }
}
