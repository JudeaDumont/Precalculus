package Precalculus;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/13/2017.
 */
public class CartesianCoordinateSystemPredicates {
    public static Quadrant getQuadrantFromPoint(Point point) {
        Quadrant quadrant = null;
        if(point.xCoordinate.compareTo(new BigDecimal(0)) > 0 && point.yCoordinate.compareTo(new BigDecimal(0)) > 0)
        {
            quadrant =Quadrant.Quad1;
        }
        else if(point.xCoordinate.compareTo(new BigDecimal(0)) < 0 && point.yCoordinate.compareTo(new BigDecimal(0)) > 0)
        {
            quadrant =Quadrant.Quad2;
        }
        else if(point.xCoordinate.compareTo(new BigDecimal(0)) < 0 && point.yCoordinate.compareTo(new BigDecimal(0)) < 0)
        {
            quadrant =Quadrant.Quad3;
        }
        else if(point.xCoordinate.compareTo(new BigDecimal(0)) > 0 && point.yCoordinate.compareTo(new BigDecimal(0)) < 0)
        {
            quadrant = Quadrant.Quad4;
        }
        return quadrant;
    }
}
