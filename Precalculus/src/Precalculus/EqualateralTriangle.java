package Precalculus;

import java.math.BigDecimal;
import java.math.MathContext;

import static Precalculus.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class EqualateralTriangle extends Triangle {
    @Override
    public String getShapeType() {
        return "EqualateralTriangle";
    }

    protected EqualateralTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Equalateral;
    }

    @Override
    public void calcShapeArea() {
        area = lines[0].distance.multiply(getMeridians()[0].distance).divide(new BigDecimal(2)
        , new MathContext(lines[0].distance.multiply(getMeridians()[0].distance)
                        .divide(new BigDecimal(2)).toBigInteger().toString().length() + EQUALITY_PRECISION));
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
        for (int i = 0; i < points.length; i++) {
            modPoints[i] = new Point(points[i].xCoordinate.subtract(points[0].xCoordinate), points[i].yCoordinate.subtract(points[0].yCoordinate));
        }
        point = new Point(point.xCoordinate.subtract(points[0].xCoordinate), point.yCoordinate.subtract(points[0].yCoordinate));
        double angle = AnglePredicates.getAngleFromPoint(modPoints[1]);
        double angleDifferenceBetweenRight = 360 - angle;
        for (int i = 0; i < points.length; i++) {
            modPoints[i] = modPoints[i].rotate(new Point(0, 0), angleDifferenceBetweenRight);
        }
        Point topPoint = new Point(0,new BigDecimal(-Double.MAX_VALUE));
        int indexOfTopPoint = -1;
        for (int i = 0; i < modPoints.length; i++) {
            if(modPoints[i].yCoordinate.compareTo(topPoint.yCoordinate)>0)
            {
                topPoint = modPoints[i];
                indexOfTopPoint = i;
            }
        }
        Point midPoint = new Point(modPoints[(indexOfTopPoint+1)%3].xCoordinate.doubleValue()+modPoints[(indexOfTopPoint+2)%3].xCoordinate.doubleValue()/2,0);
        boolean inTriangle1 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(modPoints[(indexOfTopPoint+1)%3]), midPoint, topPoint
                }
        );
        boolean inTriangle2 = RightTrianglePredicates.pointInRightTriangle(point, new Point[]
                {
                        new Point(modPoints[(indexOfTopPoint+2)%3]), midPoint, topPoint
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
