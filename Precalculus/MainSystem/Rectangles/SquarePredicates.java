package MainSystem.Rectangles;

import MainSystem.Circle.Circle;
import MainSystem.Point.Point;

/**
 * Created by Owner on 7/18/2017.
 */
@SuppressWarnings("WeakerAccess")
public class SquarePredicates {

    public static Rectangle deriveContainingDiamondFromCircle(Circle derivedCircle) {
        double derivedCircleXCoord = derivedCircle.center.xCoordinate.doubleValue();
        double derivedCircleYCoord = derivedCircle.center.yCoordinate.doubleValue();
        double derivedCircleRadius = derivedCircle.radius;
        return Rectangle.createInstance(
                new Point[]
                        {
                                new Point(
                                        derivedCircleXCoord + derivedCircleRadius,
                                        derivedCircleYCoord + derivedCircleRadius
                                ),
                                new Point(
                                        derivedCircleXCoord + derivedCircleRadius,
                                        derivedCircleYCoord - derivedCircleRadius
                                ),
                                new Point(

                                        derivedCircleXCoord - derivedCircleRadius,
                                        derivedCircleYCoord + derivedCircleRadius
                                ),
                                new Point(

                                        derivedCircleXCoord - derivedCircleRadius,
                                        derivedCircleYCoord - derivedCircleRadius
                                )
                        }
        );
    }

    public static Rectangle deriveContainedSquareFromCircle(Circle derivedCircle) {
        Point point = derivedCircle.calculatePointOfCircle(derivedCircle.radius / 2);
        return Rectangle.createInstance(
                new Point[]
                        {
                                new Point(
                                        point.xCoordinate,
                                        point.yCoordinate
                                ),
                                new Point(
                                        point.xCoordinate.negate(),
                                        point.yCoordinate
                                ),
                                new Point(
                                        point.xCoordinate,
                                        point.yCoordinate.negate()
                                ),
                                new Point(
                                        point.xCoordinate.negate(),
                                        point.yCoordinate.negate()
                                )
                        }
        );
    }
}
