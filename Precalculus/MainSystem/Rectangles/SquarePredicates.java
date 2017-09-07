package MainSystem.Rectangles;

import MainSystem.Circle.Circle;
import MainSystem.Points.Point;

/**
 * Created by Owner on 7/18/2017.
 */
public class SquarePredicates {

    public static Rectangle deriveContainingSquareFromCircle(Circle derivedCircle) {
        return Rectangle.createInstance(
                new Point[]
                        {
                                new Point(
                                        derivedCircle.center.xCoordinate.doubleValue() + derivedCircle.radius,
                                        derivedCircle.center.yCoordinate.doubleValue() + derivedCircle.radius
                                ),
                                new Point(
                                        derivedCircle.center.xCoordinate.doubleValue() + derivedCircle.radius,
                                        derivedCircle.center.yCoordinate.doubleValue() - derivedCircle.radius
                                ),
                                new Point(

                                        derivedCircle.center.xCoordinate.doubleValue() - derivedCircle.radius,
                                        derivedCircle.center.yCoordinate.doubleValue() + derivedCircle.radius
                                ),
                                new Point(

                                        derivedCircle.center.xCoordinate.doubleValue() - derivedCircle.radius,
                                        derivedCircle.center.yCoordinate.doubleValue() - derivedCircle.radius
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
