package Precalculus;

/**
 * Created by Owner on 8/27/2017.
 */
public class AnglePredicates {
    public static double getAngleFromPoint(Point modPoint) {
        return (450 - Math.toDegrees(Math.atan2(modPoint.xCoordinate.doubleValue(), modPoint.yCoordinate.doubleValue()))) % 360;
    }
}
