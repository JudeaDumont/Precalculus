package MainSystem.Circle;

import MainSystem.CartesianCoordinateSystem.FourPropositions;
import MainSystem.Line.Line;
import MainSystem.Point.Point;
import MainSystem.Rectangles.Rectangle;

import java.math.BigDecimal;

import static MainSystem.SystemGlobal.SystemGlobal.DEFAULT_CIRCLE_PRECISION;

/**
 * Created by Owner on 7/18/2017.
 */
public class CirclePredicates {

    private CirclePredicates() {
    }

    public static Circle deriveCircleFromLine(Line line) {
        Point point1 = line.point1;
        Point point2 = line.point2;
        return new Circle(
                point1.xCoordinate.abs().add(point1.yCoordinate.abs()).compareTo
                        (point2.xCoordinate.abs().add(point2.yCoordinate.abs())) > 0 ?
                        point2 : point1
                , line.distance.doubleValue(), DEFAULT_CIRCLE_PRECISION);
    }

    public static Line getTangent(@SuppressWarnings("SameParameterValue") int pointIndex, Circle circle) {
        Line tangentLine = null;
        if(pointIndex >= 0 && pointIndex < circle.points.length) {
            Point pointAtIndex = circle.points[pointIndex];
            BigDecimal xCoordinate = pointAtIndex.xCoordinate;
            BigDecimal centerPointXCoord = circle.center.xCoordinate;
            BigDecimal yCoordinate = pointAtIndex.yCoordinate;
            BigDecimal centerPointYCoord = circle.center.yCoordinate;
            double radiusOfCircle = circle.radius;
            if (!xCoordinate.equals(centerPointXCoord) &&
                    !yCoordinate.equals(centerPointYCoord)) {
                Line innerLine = new Line(circle.center, pointAtIndex);
                BigDecimal perpendicularSlope = new BigDecimal(innerLine.slope.toString()).negate();
                double xCoordDouble = xCoordinate.doubleValue();
                double yCoordDouble = yCoordinate.doubleValue();
                double perpendicularSlopeDouble = perpendicularSlope.doubleValue();
                tangentLine = new Line(
                        new Point(
                                radiusOfCircle + xCoordDouble,
                                (radiusOfCircle * perpendicularSlopeDouble) + yCoordDouble),
                        new Point(-radiusOfCircle + xCoordDouble,
                                (-radiusOfCircle * perpendicularSlopeDouble) + yCoordDouble));
            } else if (yCoordinate.equals(centerPointYCoord)) {
                tangentLine = new Line(new Point(xCoordinate, radiusOfCircle),
                        new Point(xCoordinate, -radiusOfCircle));
            } else {
                tangentLine = new Line(new Point(radiusOfCircle, yCoordinate),
                        new Point(-radiusOfCircle, yCoordinate));
            }
        }
        return tangentLine;
    }

    public static Rectangle deriveContainingSquareFromCircle(Circle circle) {
        double sqrtRadius = Math.sqrt(circle.radius);
        BigDecimal centerXCoordinate = circle.center.xCoordinate;
        BigDecimal centerYCoordinate = circle.center.yCoordinate;
        BigDecimal positiveSqrtRadius = new BigDecimal(sqrtRadius);
        BigDecimal negativeSqrtRadius = new BigDecimal(-sqrtRadius);
        BigDecimal sqrtRadiusXCoordinateSum = positiveSqrtRadius.add(centerXCoordinate);
        BigDecimal sqrtRadiusYCenterSum = positiveSqrtRadius.add(centerYCoordinate);
        BigDecimal radiusAndXCoordDifference = negativeSqrtRadius.add(centerXCoordinate);
        BigDecimal radiusAndYCoordDifference = negativeSqrtRadius.add(centerYCoordinate);
        return Rectangle.createInstance(new Point[]{
                new Point(sqrtRadiusXCoordinateSum,
                        sqrtRadiusYCenterSum),
                new Point(radiusAndXCoordDifference,
                        sqrtRadiusYCenterSum),
                new Point(radiusAndXCoordDifference,
                        radiusAndYCoordDifference),
                new Point(sqrtRadiusXCoordinateSum,
                        radiusAndYCoordDifference),
        });
    }

    public static Rectangle deriveRectangleFromCircle(Circle circle, FourPropositions fourPropositions) {
        Rectangle derivedRectangle = null;
        double radiusSqrt = Math.sqrt(circle.radius);
        BigDecimal centerXCoordinate = circle.center.xCoordinate;
        BigDecimal centerYCoordinate = circle.center.yCoordinate;
        BigDecimal positiveSqrtRadius = new BigDecimal(radiusSqrt);
        BigDecimal negativeSqrtRadius = new BigDecimal(-radiusSqrt);
        BigDecimal sqrtRadiusCenterDifference = negativeSqrtRadius.add(centerYCoordinate);
        BigDecimal sqrtRadiusCenterSum = positiveSqrtRadius.add(centerYCoordinate);
        BigDecimal sqrtRadiusXCoordSum = positiveSqrtRadius.add(centerXCoordinate);
        BigDecimal sqrtRadiusXCoordDifference = negativeSqrtRadius.add(centerXCoordinate);
        switch (fourPropositions) {
            case BOTTOM:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(sqrtRadiusXCoordSum, centerYCoordinate),
                                new Point(sqrtRadiusXCoordDifference, centerYCoordinate),
                                new Point(sqrtRadiusXCoordDifference, sqrtRadiusCenterDifference),
                                new Point(sqrtRadiusXCoordSum, sqrtRadiusCenterDifference)
                        }
                );
                break;
            case TOP:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(sqrtRadiusXCoordSum, sqrtRadiusCenterSum),
                                new Point(sqrtRadiusXCoordDifference, sqrtRadiusCenterSum),
                                new Point(sqrtRadiusXCoordDifference, centerYCoordinate),
                                new Point(sqrtRadiusXCoordSum, centerYCoordinate)
                        }
                );
                break;
            case LEFT:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(centerXCoordinate, sqrtRadiusCenterSum),
                                new Point(sqrtRadiusXCoordDifference, sqrtRadiusCenterSum),
                                new Point(sqrtRadiusXCoordDifference, sqrtRadiusCenterDifference),
                                new Point(centerXCoordinate, sqrtRadiusCenterDifference)
                        }
                );
                break;
            case RIGHT:
                derivedRectangle = Rectangle.createInstance(
                        new Point[]{
                                new Point(sqrtRadiusXCoordSum, sqrtRadiusCenterSum),
                                new Point(centerXCoordinate, sqrtRadiusCenterSum),
                                new Point(centerXCoordinate, sqrtRadiusCenterDifference),
                                new Point(sqrtRadiusXCoordSum, sqrtRadiusCenterDifference)
                        }
                );
                break;
        }
        return derivedRectangle;
    }
}
