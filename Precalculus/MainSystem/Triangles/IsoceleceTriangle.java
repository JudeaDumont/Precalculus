package MainSystem.Triangles;

import MainSystem.Angles.AnglePredicates;
import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Lines.Line;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.GlobalSystem.SystemGlobal.EQUALITY_PRECISION;

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
        for (int i = 0; i < lines.length; i++) {
            for (int i1 = 0; i1 < lines.length; i1++) {
                if (i != i1 && lines[i].distance.toString().equals(lines[i1].distance.toString())) {
                    equalLines = new Line[]{lines[i], lines[i1]};
                    break;
                }
            }
        }
        Point commonPoint = null;
        if (equalLines[0].point1.equals(equalLines[1].point1) || equalLines[0].point1.equals(equalLines[1].point2)) {
            commonPoint = equalLines[0].point1;
        } else {
            commonPoint = equalLines[0].point2;
        }
        for (int i = 0; i < lines.length; i++) {
            for (Point point : points) {
                if (!point.equals(lines[i].point1) && !point.equals(lines[i].point2) && point.equals(commonPoint)) {
                    area = lines[i].distance.multiply(new Line(point, lines[i].getMidPoint(), 0).distance).divide(new BigDecimal(2))
                            .round(new MathContext(lines[i].distance.multiply(new Line(point, lines[i].getMidPoint(), 0).distance).divide(new BigDecimal(2)).toBigInteger().toString().length() + EQUALITY_PRECISION));
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
        Line side1 = new Line(points[0], points[1]);
        Line side2 = new Line(points[1], points[2]);
        Line side3 = new Line(points[2], points[0]);
        Line equalSide1 = null;
        Line equalSide2 = null;
        //Get Odd Side of Isocelece
        Line oddSide = side1.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side2.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                ) ? side3 : side2.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side3.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                ) ? side1 : side3.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side1.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                ) ? side2 : null;
        if (oddSide == null) {
            throw new NullPointerException();
        }

        pointPairOfOddSide = new Point[]{new Point(oddSide.point1), new Point(oddSide.point2)};
        Point[] modpoints = new Point[3];

        //Shift Triangle to one of the points of the side such that the point = 0,0
        for (int i = 0; i < points.length; i++) {
            modpoints[i] = new Point(points[i].xCoordinate.subtract(pointPairOfOddSide[0].xCoordinate), points[i].yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));
        }
        //Shift point along with modification
        point = new Point(point.xCoordinate.subtract(pointPairOfOddSide[0].xCoordinate), point.yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));

        //Shift point of the oddside as well
        pointPairOfOddSide[1] = new Point(pointPairOfOddSide[1].xCoordinate.subtract(pointPairOfOddSide[0].xCoordinate),
                pointPairOfOddSide[1].yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));
        //Shift point of point used for modification to 0,0
        pointPairOfOddSide[0] = new Point(0, 0);

        double angle = -1;
        int nonZeroPoint1 = -1;
        int nonZeroPoint2 = -1;

        //Get angle of the odd side
        angle = AnglePredicates.getAngleFromPoint(pointPairOfOddSide[1]);

        //find non zero points of triangle
        boolean foundNonZero = false;
        for (int i = 0; i < modpoints.length; i++) {
            if (!modpoints[i].equals(new Point(0, 0)) && !foundNonZero) {
                nonZeroPoint1 = i;
                foundNonZero = true;
            } else if (!modpoints[i].equals(new Point(0, 0)) && foundNonZero) {
                nonZeroPoint2 = i;
            }
        }

        //rotate nonzeros and point and nonzero point of odd point side
        double angleOfAugmentation = 360 - (angle);
        if (angle != 0) {
            modpoints[nonZeroPoint1] = modpoints[nonZeroPoint1].rotate(new Point(0, 0), angleOfAugmentation);
            modpoints[nonZeroPoint2] = modpoints[nonZeroPoint2].rotate(new Point(0, 0), angleOfAugmentation);
            pointPairOfOddSide[1] = pointPairOfOddSide[1].rotate(new Point(0, 0), angleOfAugmentation);
            point = point.rotate(new Point(0, 0), angleOfAugmentation);
        }
        //if the oddside non zero is negative shift right along with point and odd side, were going for only quandrant one points
        if (pointPairOfOddSide[1].xCoordinate.compareTo(new BigDecimal(0)) < 0) {
            for (int i = 0; i < modpoints.length; i++) {
                modpoints[i] = new Point(modpoints[i].xCoordinate.subtract(pointPairOfOddSide[1].xCoordinate),
                        modpoints[i].yCoordinate.subtract(pointPairOfOddSide[1].yCoordinate));
            }
            for (int i = 0; i < pointPairOfOddSide.length; i++) {
                pointPairOfOddSide[i] = new Point(pointPairOfOddSide[i].xCoordinate.subtract(pointPairOfOddSide[1].xCoordinate),
                        pointPairOfOddSide[i].yCoordinate.subtract(pointPairOfOddSide[1].yCoordinate));
            }
            point = new Point(point.xCoordinate.subtract(pointPairOfOddSide[1].xCoordinate),
                    point.yCoordinate.subtract(pointPairOfOddSide[1].yCoordinate));
        }

        boolean inShape = false;
        //get the top point of the triangle
        Point topPoint = new Point(0, new BigDecimal(-Double.MAX_VALUE));
        int indexOfTopPoint = -1;
        for (int i = 0; i < modpoints.length; i++) {
            if (modpoints[i].yCoordinate.compareTo(topPoint.yCoordinate) > 0) {
                topPoint = modpoints[i];
                indexOfTopPoint = i;
            }
        }

        //if the top point is 0 then the triangle is upside down and we need to rotate 180 and shift right
        if (modpoints[indexOfTopPoint].yCoordinate.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION)).compareTo(new BigDecimal(0)) <= 0) {
            for (int i = 0; i < modpoints.length; i++) {
                modpoints[i] = modpoints[i].rotate(new Point(0, 0), 180);
            }
            point = point.rotate(new Point(0, 0), 180);

            for (int i = 0; i < pointPairOfOddSide.length; i++) {
                pointPairOfOddSide[i] = new Point(pointPairOfOddSide[i].rotate(new Point(0, 0), 180));
            }

            boolean leftPoint = pointPairOfOddSide[1].xCoordinate.compareTo(new BigDecimal(0)) < 0;
            for (int i = 0; i < modpoints.length; i++) {
                modpoints[i] = new Point(modpoints[i].xCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].xCoordinate),
                        modpoints[i].yCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].yCoordinate));
            }
            point = new Point(point.xCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].xCoordinate),
                    point.yCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].yCoordinate));

            for (int i = 0; i < pointPairOfOddSide.length; i++) {
                pointPairOfOddSide[i] = new Point(pointPairOfOddSide[i].xCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].xCoordinate),
                        pointPairOfOddSide[i].yCoordinate.subtract(pointPairOfOddSide[leftPoint ? 1 : 0].yCoordinate));
            }
        }
        //now that the triangle is definitely in the first quadrant, we can split it into two right angles and send them off including the point to check if the point is in either
        //Get right point
        Point rightPoint = new Point(0, new BigDecimal(-Double.MAX_VALUE));
        int indexOfRightPoint = -1;
        for (int i = 0; i < modpoints.length; i++) {
            if (modpoints[i].xCoordinate.compareTo(rightPoint.xCoordinate) > 0) {
                rightPoint = modpoints[i];
                indexOfRightPoint = i;
            }
        }

        for (int i = 0; i < modpoints.length; i++) {
            if (modpoints[i].yCoordinate.compareTo(topPoint.yCoordinate) > 0) {
                topPoint = modpoints[i];
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
