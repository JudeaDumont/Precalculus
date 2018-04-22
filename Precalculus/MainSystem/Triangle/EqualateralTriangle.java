package MainSystem.Triangle;

import MainSystem.AnglePredicates.AnglePredicates;
import MainSystem.Point.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.SystemGlobal.SystemGlobal.EQUALITY_PRECISION;
import static MainSystem.SystemGlobal.SystemGlobal.zeroPoint;

public class EqualateralTriangle extends Triangle {
    @Override
    public String getShapeType() {
        return "EqualateralTriangle";
    }

    EqualateralTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Equalateral;
    }

    @Override
    public void calcShapeArea() {
        BigDecimal divisor = new BigDecimal(2);
        BigDecimal meridianDistance = getMeridians()[0].distance;
        BigDecimal lineDistance = lines[0].distance;
        BigDecimal lineMultipliedByMeridian = lineDistance.multiply(meridianDistance);
        area = lineMultipliedByMeridian.divide(divisor
                , new MathContext(lineMultipliedByMeridian
                        .divide(divisor).toBigInteger().toString().length() + EQUALITY_PRECISION));
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        boolean inShape = false;
        //rotate equalateral to one edge = (0,0), (x,0)
        Point[] modPoints = new Point[points.length];
        Point point1 = points[0];
        BigDecimal xCoordinate = point1.xCoordinate;
        BigDecimal yCoordinate = point1.yCoordinate;
        for (int i = 0; i < points.length; i++) {
            Point pointsI = points[i];
            modPoints[i] = new Point(pointsI.xCoordinate.subtract(xCoordinate), pointsI.yCoordinate.subtract(yCoordinate));
        }
        point = new Point(point.xCoordinate.subtract(points[0].xCoordinate), point.yCoordinate.subtract(points[0].yCoordinate));
        double angle = AnglePredicates.getAngleFromPoint(modPoints[1]);
        double angleDifferenceBetweenRight = 360 - angle;
        for (int i = 0; i < points.length; i++) {
            modPoints[i] = modPoints[i].rotate(zeroPoint, angleDifferenceBetweenRight);
        }
        Point topPoint = new Point(0, new BigDecimal(-Double.MAX_VALUE));
        int indexOfTopPoint = -1;
        for (int i = 0; i < modPoints.length; i++) {
            if (modPoints[i].yCoordinate.compareTo(topPoint.yCoordinate) > 0) {
                topPoint = modPoints[i];
                indexOfTopPoint = i;
            }
        }
        Point rightOrLeftPoint = modPoints[(indexOfTopPoint + 1) % 3];
        Point leftOrRightPoint = modPoints[(indexOfTopPoint + 2) % 3];
        double rightOrLeftX = rightOrLeftPoint.xCoordinate.doubleValue();
        double leftOrRightX = leftOrRightPoint.xCoordinate.doubleValue();
        Point midPoint = new Point(rightOrLeftX + leftOrRightX / 2, 0);
        boolean inTriangle1 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(rightOrLeftPoint), midPoint, topPoint
                }
        );
        boolean inTriangle2 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(leftOrRightPoint), midPoint, topPoint
                }
        );

        inShape = inTriangle1 || inTriangle2;

        //rotate point appropriately
        //get midpoint of previously described edge
        //triangle 1 = left point, top point, midpoint
        //triangle 2 = right point, top point, midpoint
        //send both triangles to righttrianglepredicates point in right triangle
        //if either come back true then point is in the equalateral.

        return inShape;
    }
}
