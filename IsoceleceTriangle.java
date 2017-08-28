package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class IsoceleceTriangle extends Triangle {

    protected IsoceleceTriangle(Point[] points) {
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

        //THIS IS UNFINISHED

        Point[] pointPairOfOddSide = new Point[2];
        Line side1 = new Line(points[0], points[1]);
        Line side2 = new Line(points[1], points[2]);
        Line side3 = new Line(points[3], points[0]);
        Line equalSide1 = null;
        Line equalSide2 = null;
        Line oddSide = side1.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side2.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                )?side3:side2.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side3.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                )?side1:side3.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(side1.distance.divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                )?side2:null;

        pointPairOfOddSide = new Point[]{new Point(oddSide.point1), new Point(oddSide.point2)};
        Point[] modpoints = new Point[3];

        for (int i = 0; i < points.length; i++) {
            modpoints[i] = new Point(points[i].xCoordinate.subtract(pointPairOfOddSide[0].xCoordinate), points[i].yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));
        }
        pointPairOfOddSide[0] = new Point(0,0);

        pointPairOfOddSide[1] = new Point(pointPairOfOddSide[1].xCoordinate.subtract(pointPairOfOddSide[0].xCoordinate),
                pointPairOfOddSide[1].yCoordinate.subtract(pointPairOfOddSide[0].yCoordinate));

        double angle1 = 0;
        double angle2 = 0;
        boolean foundOne = false;
        for (int i = 0; i < modpoints.length; i++) {
            if (!modpoints[i].equals(new Point(0, 0)) && !foundOne) {
                angle1 = AnglePredicates.getAngleFromPoint(modpoints[i]);
                foundOne=true;
            }
            else if (!modpoints[i].equals(new Point(0, 0)) && foundOne) {
                angle2 = AnglePredicates.getAngleFromPoint(modpoints[i]);
            }
        }
        return false;
    }
}
