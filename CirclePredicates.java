package com.company;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/18/2017.
 */
public class CirclePredicates {

    public static Circle deriveCircleFromLine(Line line) {
        return new Circle(
                line.point1.xCoordinate.abs().add(line.point1.yCoordinate.abs()).compareTo
                        (line.point2.xCoordinate.abs().add(line.point2.yCoordinate.abs())) > 0 ?
                        line.point2 : line.point1
                , line.distance.doubleValue(), SystemGlobal.DEFAULT_CIRCLE_PRECISION);
    }

    public static Line getTangent(int pointIndex, Circle circle) {
        Line tangentLine;
        if (!circle.points[pointIndex].xCoordinate.equals(circle.center.xCoordinate) && !circle.points[pointIndex].yCoordinate.equals(circle.center.yCoordinate)) {
            Line innerLine = new Line(circle.center, circle.points[pointIndex]);
            BigDecimal perpendicularSlope = new BigDecimal(innerLine.slope.toString()).negate();
            tangentLine = new Line(
                    new Point(
                            circle.radius + circle.points[pointIndex].xCoordinate.doubleValue(),
                            (circle.radius * perpendicularSlope.doubleValue()) + circle.points[pointIndex].yCoordinate.doubleValue()),
                    new Point(-circle.radius + circle.points[pointIndex].xCoordinate.doubleValue(),
                            (-circle.radius * perpendicularSlope.doubleValue()) + circle.points[pointIndex].yCoordinate.doubleValue()));
        } else if (circle.points[pointIndex].yCoordinate.equals(circle.center.yCoordinate)) {
            tangentLine = new Line(new Point(circle.points[pointIndex].xCoordinate, circle.radius),
                    new Point(circle.points[pointIndex].xCoordinate, -circle.radius));
        } else {
            tangentLine = new Line(new Point(circle.radius, circle.points[pointIndex].yCoordinate),
                    new Point(-circle.radius, circle.points[pointIndex].yCoordinate));
        }

        return tangentLine;
    }

    public static Square deriveSquareFromCircle(Circle circle) {
        return new Square(new Point[]{
                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
        });
    }

    public static Rectangle deriveRectangleFromCircle(Circle circle, FourPropositions fourPropositions) {
        Rectangle derivedRectangle = null;
        switch (fourPropositions) {
            case BOTTOM:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(circle.center.yCoordinate.toString())),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(circle.center.yCoordinate.toString())),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate))
                        }
                );
                break;
            case TOP:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(circle.center.yCoordinate.toString())),
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(circle.center.yCoordinate.toString()))
                        }
                );
                break;
            case LEFT:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(new BigDecimal(circle.center.xCoordinate.toString()), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(circle.center.xCoordinate.toString()), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate))
                        }
                );
                break;
            case RIGHT:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(circle.center.xCoordinate.toString()), new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(circle.center.xCoordinate.toString()), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate)),
                                new Point(new BigDecimal(Math.sqrt(circle.radius)).add(circle.center.xCoordinate), new BigDecimal(-Math.sqrt(circle.radius)).add(circle.center.yCoordinate))
                        }
                );
                break;
        }
        return derivedRectangle;
    }
}
