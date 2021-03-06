package MainSystem.CartesianCoordinateSystem;

import MainSystem.Point.Point;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/13/2017.
 */
@SuppressWarnings("WeakerAccess")
public class CartesianCoordinateSystemPredicates {
    public static Quadrant getQuadrantFromPoint(Point point) {
        //This would also need the coordinate system passed into it, as the quadrant would depend on the origin
        Quadrant quadrant = null;
        BigDecimal xCoordinate = point.xCoordinate;
        BigDecimal yCoordinate = point.yCoordinate;
        BigDecimal zero = new BigDecimal(0);
        int xPosOrNeg = xCoordinate.compareTo(zero);
        int yPosOrNeg = yCoordinate.compareTo(zero);
        boolean xPN = xPosOrNeg > 0;
        boolean yPN = yPosOrNeg > 0;
        if (xPN && yPN) {
            quadrant = Quadrant.Quad1;
        } else if (!xPN && yPN) {
            quadrant = Quadrant.Quad2;
        } else if (!xPN && !yPN) {
            quadrant = Quadrant.Quad3;
        } else if (xPN && !yPN) {
            quadrant = Quadrant.Quad4;
        }
        return quadrant;
    }
}
