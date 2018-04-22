package MainSystem.AnglePredicates;

import MainSystem.Point.Point;

public class AnglePredicates {
    public static double getAngleFromPoint(Point modPoint) {
        return (450 - Math.toDegrees(Math.atan2(modPoint.xCoordinate.doubleValue(), modPoint.yCoordinate.doubleValue()))) % 360;
    }
}
