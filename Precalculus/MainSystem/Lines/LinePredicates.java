package MainSystem.Lines;

import MainSystem.GlobalSystem.SystemGlobal;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static MainSystem.GlobalSystem.SystemGlobal.*;

public class LinePredicates {

    private static boolean checkForDuplicateLines(Collection<Line> lines, Line realNumberLine) {
        for (Line line : lines) {
            if (realNumberLine.equals(line)) {
                return true;
            }
        }
        return false;
    }

    public static Line[] computeAllLinesFromSortedPoints(Point[] points) {
        Point pointTemp = points[0];
        points[0] = points[1];
        points[1] = pointTemp;
        Collection<Line> computedLines = new ArrayList<Line>();
        int pointsSize = points.length;
        for (int i = 0; i < pointsSize; i++) {
            computedLines.add(new Line(new Point(points[i]), new Point(points[(i + 1) % pointsSize]), 1));
        }
        points[1] = points[0];
        points[0] = pointTemp;
        return computedLines.toArray(new Line[computedLines.size()]);
    }

    public static Line[] computeAllLinesFromPoints(Point[] points) {
        Collection<Line> computedLines = new ArrayList<Line>();
        int pointsSize = points.length;
        for (int i = 0; i < pointsSize; i++) {
            computedLines.add(new Line(new Point(points[i]), new Point(points[(i + 1) % pointsSize]), 1));
        }
        return computedLines.toArray(new Line[computedLines.size()]);
    }

    public static Line[] computeAllLinesAndSortPoints(Point[] points) {
        Collection<Line> computedLines = new ArrayList<Line>();
        points = PointPredicates.sortPoints(points);
        int pointsSize = points.length;
        for (int i = 0; i < pointsSize; i++) {
            computedLines.add(new Line(new Point(points[i]), new Point(points[(i + 1) % pointsSize]), 1));
        }
        return computedLines.toArray(new Line[computedLines.size()]);
    }

    public static void calculateAndDisplayLines(Point[] points) {
        Line[] lines = computeAllLinesAndSortPoints(points);
        for (Line numberLine : lines) {
            System.out.println(numberLine.point1.pointName + " " + numberLine.point2.pointName + ": " + numberLine.distance);
        }
    }

    public static Point[] findPointsFromDistanceAndFixedXCoordinate(Point point, BigDecimal distance, BigDecimal xCoordinate) {
        BigDecimal coordinateCalculatedByDistance = new BigDecimal(
                Math.sqrt((xCoordinate.subtract(point.xCoordinate).pow(2)).
                        subtract(distance.pow(2)).abs().doubleValue()));
        BigDecimal yCoordinate = point.yCoordinate;
        return new Point[]
                {
                        new Point(xCoordinate,
                                coordinateCalculatedByDistance.add(yCoordinate)
                        ),
                        new Point(xCoordinate,
                                coordinateCalculatedByDistance.negate().add(yCoordinate)
                        )
                };
    }

    public static Point[] findPointsFromDistanceAndFixedYCoordinate(Point point, BigDecimal distance, BigDecimal yCoordinate) {
        BigDecimal coordinateCalculatedByDistance = new BigDecimal(
                Math.sqrt((yCoordinate.subtract(point.yCoordinate).pow(2)).
                        subtract(distance.pow(2)).abs().doubleValue()));
        BigDecimal xCoordinate = point.xCoordinate;
        return new Point[]
                {
                        new Point(coordinateCalculatedByDistance.add(xCoordinate),yCoordinate),
                        new Point(coordinateCalculatedByDistance.negate().add(xCoordinate),yCoordinate)
                };
    }

    public static void displayLineLengths(Line[] lines) {
        for (Line line : lines) {
            System.out.println(line);
        }
    }

    public static BigDecimal[] getLineLengths(Line[] lines) {
        BigDecimal[] distances = new BigDecimal[lines.length];
        for (int i = 0; i < lines.length; i++) {
            distances[i] = lines[i].distance;
        }
        Arrays.sort(distances);
        return distances;
    }

    @SuppressWarnings("WeakerAccess")
    public static BigDecimal calculateDistanceBetweenTwoPointsAfterTime
            (Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2, BigDecimal time) {
        return new Line(
                new Point(
                        velocity1.xCoordinate.multiply(time).add(startingPoint1.xCoordinate),
                        velocity1.yCoordinate.multiply(time).add(startingPoint1.yCoordinate)),
                new Point(
                        velocity2.xCoordinate.multiply(time).add(startingPoint2.xCoordinate),
                        velocity2.yCoordinate.multiply(time).add(startingPoint2.yCoordinate)), 1).distance;
    }

    public static BigDecimal calculateDistanceBetweenTwoPointsAfterTime
            (Point startingPoint1, Point veloctiy1, Point startingPoint2, Point velocity2, double time) {
        return calculateDistanceBetweenTwoPointsAfterTime
                (startingPoint1, veloctiy1, startingPoint2, velocity2, new BigDecimal(time));
    }

    public static Line doubleLine(Line line) {
        Point point2 = line.point2;
        Point point1 = line.point1;
        BigDecimal xCoordinate = point1.xCoordinate;
        BigDecimal yCoordinate = point1.yCoordinate;
        BigDecimal multiplicand = new BigDecimal(2);
        return new Line(new Point(point1), new Point(
                point2.xCoordinate.subtract(xCoordinate).multiply
                        (multiplicand).add(xCoordinate),
                point2.yCoordinate.subtract(yCoordinate).multiply
                        (multiplicand).add(yCoordinate)), 1);
    }

    @SuppressWarnings("WeakerAccess")
    public static Line createLineWithPointSlopeAndMagnitude(Point point, BigDecimal slope, BigDecimal magnitude) {
        if (slope.equals(zero)) {
            System.out.println("Slope was zero");
            return null;
        }
        return new Line(point, new Point(point.xCoordinate.add(magnitude), point.yCoordinate.add(slope.multiply(magnitude))));
    }

    public static boolean checkParallelity(Line line1, Line line2) {
        return line1.lineType == LineType.Diagonal && line1.slope.equals(line2.slope)
                && (!line1.point1.equals(line2.point1) || !line1.point2.equals(line2.point2));
    }

    public static boolean checkPerpendicularity(Line line1, Line line2) {
        return (line1.lineType == LineType.Vertical && line2.lineType == LineType.Horizontal ||
                line1.lineType == LineType.Horizontal && line2.lineType == LineType.Vertical)
                || line1.slope.multiply(line2.slope).round(new MathContext(
                line1.slope.multiply(line2.slope).toBigInteger().toString().length() + EQUALITY_PRECISION))
                .equals(new BigDecimal(-1));
    }

    public static Line deriveAverageLine(Point[] points) {
        BigDecimal xTotal = new BigDecimal(0);
        BigDecimal yTotal = new BigDecimal(0);
        BigDecimal xMin = new BigDecimal(Double.MAX_VALUE);
        for (Point point : points) {
            xTotal = xTotal.add(point.xCoordinate);
            yTotal = yTotal.add(point.yCoordinate);
            if (point.xCoordinate.compareTo(xMin) < 0) {
                xMin = new BigDecimal(point.xCoordinate.toString());
            }
        }
        BigDecimal pointsLengthBigDecimal = new BigDecimal(points.length);
        xTotal = xTotal.divide(pointsLengthBigDecimal, calcPrecisionMathContext);
        yTotal = yTotal.divide(pointsLengthBigDecimal, calcPrecisionMathContext);
        BigDecimal slope = yTotal.divide(xTotal, calcPrecisionMathContext);
        return createLineWithPointSlopeAndMagnitude(new Point(new BigDecimal(xMin.toString()), xMin.multiply(slope)), slope, new BigDecimal(10));
    }

    @SuppressWarnings("WeakerAccess")
    public static Point getIntersection(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        Point intersection = null;
        boolean containsAsymptotes = containsAsymptotes(velocity1, velocity2);

        if (!containsAsymptotes) {
            IntersectionInformation intersectionInformation = checkIfIntersects(startingPoint1, velocity1, startingPoint2, velocity2);
            if (intersectionInformation.futureIntersection) {
                BigDecimal slope1 = getSlopeFromVelocity(velocity1);
                BigDecimal slope2 = getSlopeFromVelocity(velocity2);
                BigDecimal coefficientFromPointAndSlope1 = getCoefficientFromPointAndSlope(startingPoint1, slope1);
                BigDecimal coefficientFromPointAndSlope2 = getCoefficientFromPointAndSlope(startingPoint2, slope2);
                BigDecimal xcoordinateOfIntersection = getXcoordinateOfIntersection (coefficientFromPointAndSlope1, slope1, coefficientFromPointAndSlope2, slope2);
                BigDecimal yCoordinateOfIntersection = xcoordinateOfIntersection.multiply(slope1).add(coefficientFromPointAndSlope1);
                BigDecimal yCoordinateCheck = xcoordinateOfIntersection.multiply(slope2).add(coefficientFromPointAndSlope2);
                if (!yCoordinateOfIntersection.round(equalityPrecisionMathContext).equals(yCoordinateCheck.round(equalityPrecisionMathContext))) {
                    System.err.println
                            ("Upon getting the intersection the slope intercept form failed equivalent Y values");
                }
                if (checkIntersectionWithinRange(xcoordinateOfIntersection, yCoordinateOfIntersection, intersectionInformation)) {
                    intersection = new Point(xcoordinateOfIntersection, yCoordinateOfIntersection);
                } else {
                    System.err.println("Points do not form paths that intersect");
                }
            } else {
                System.err.println("Points do not form paths that intersect");
            }
        } else {
            intersection = getIntersectionWithAsymptotes(startingPoint1, velocity1, startingPoint2, velocity2);
        }
        return intersection;
    }

    private static boolean checkIntersectionWithinRange(BigDecimal xcoordinateOfIntersection, BigDecimal yCoordinateOfIntersection, IntersectionInformation intersection) {
        return !(
                (intersection.line1HighestXPoint != null && xcoordinateOfIntersection.compareTo(intersection.line1HighestXPoint) > 0)
                        || (intersection.line2HighestXPoint != null && xcoordinateOfIntersection.compareTo(intersection.line2HighestXPoint) > 0)
                        || (intersection.line1LowestXPoint != null && xcoordinateOfIntersection.compareTo(intersection.line1LowestXPoint) < 0)
                        || (intersection.line2LowestXPoint != null && xcoordinateOfIntersection.compareTo(intersection.line2LowestXPoint) < 0)
                        || (intersection.line1HighestYPoint != null && yCoordinateOfIntersection.compareTo(intersection.line1HighestYPoint) > 0)
                        || (intersection.line2HighestYPoint != null && yCoordinateOfIntersection.compareTo(intersection.line2HighestYPoint) > 0)
                        || (intersection.line1LowestYPoint != null && yCoordinateOfIntersection.compareTo(intersection.line1LowestYPoint) < 0)
                        || (intersection.line2LowestYPoint != null && yCoordinateOfIntersection.compareTo(intersection.line2LowestYPoint) < 0)
        );
    }

    private static Point getIntersectionWithAsymptotes
            (Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        IntersectionInformation intersectionInformation = checkIfIntersects(startingPoint1, velocity1, startingPoint2, velocity2);
        Point intersection = null;
        BigDecimal xCoordinateOfIntersection = null;
        BigDecimal yCoordinateOfIntersection = null;
        if (intersectionInformation.futureIntersection) {
            BigDecimal startingXCoord2 = startingPoint2.xCoordinate;
            BigDecimal startingYCoord1 = startingPoint1.yCoordinate;
            BigDecimal xCoordVel2 = velocity2.xCoordinate;
            BigDecimal yCoordVel1 = velocity1.yCoordinate;
            boolean xVel2EqualsZero = xCoordVel2.equals(zero);
            boolean yVel1EqualsZero = yCoordVel1.equals(zero);
            String startingXCoordString2 = startingXCoord2.toString();
            String startingYCoordString1 = startingYCoord1.toString();
            if (xVel2EqualsZero && yVel1EqualsZero) {
                xCoordinateOfIntersection = new BigDecimal(startingXCoordString2);
                yCoordinateOfIntersection = new BigDecimal(startingYCoordString1);
            } else {
                BigDecimal xCoordVel1 = velocity1.xCoordinate;
                BigDecimal startingXCoord1 = startingPoint1.xCoordinate;
                BigDecimal startingYCoord2 = startingPoint2.yCoordinate;
                String startingXCoordString1 = startingXCoord1.toString();
                String startingYCoordString2 = startingYCoord2.toString();
                BigDecimal yCoordVel2 = velocity2.yCoordinate;
                boolean xVel1EqualsZero = xCoordVel1.equals(zero);
                boolean yVel2EqualsZero = yCoordVel2.equals(zero);
                if (xVel1EqualsZero
                        && yVel2EqualsZero) {
                    xCoordinateOfIntersection = new BigDecimal(startingXCoordString1);
                    yCoordinateOfIntersection = new BigDecimal(startingYCoordString2);
                } else if (xVel2EqualsZero) {
                    xCoordinateOfIntersection = new BigDecimal(startingXCoordString2);
                    yCoordinateOfIntersection = startingYCoord1.add(startingXCoord2.subtract(startingXCoord1).divide
                                    (xCoordVel1, calcPrecisionMathContext).multiply(yCoordVel1));
                } else if (xVel1EqualsZero) {
                    xCoordinateOfIntersection = new BigDecimal(startingXCoordString1);
                    yCoordinateOfIntersection = startingYCoord2.add(startingXCoord1.subtract(startingXCoord2).divide
                                    (xCoordVel2, calcPrecisionMathContext).multiply(yCoordVel2));
                } else if (yVel2EqualsZero) {
                    yCoordinateOfIntersection = new BigDecimal(startingYCoordString2);
                    xCoordinateOfIntersection = startingXCoord1.add(startingYCoord2.subtract(startingYCoord1).divide
                                    (yCoordVel1, calcPrecisionMathContext).multiply(xCoordVel1));
                } else if (yVel1EqualsZero) {
                    yCoordinateOfIntersection = new BigDecimal(startingYCoordString1);
                    xCoordinateOfIntersection = startingXCoord2.add(startingYCoord1.subtract(startingYCoord2).divide
                                    (yCoordVel2, calcPrecisionMathContext).multiply(xCoordVel2));
                }
            }
            if(checkIntersectionWithinRange(xCoordinateOfIntersection, yCoordinateOfIntersection, intersectionInformation)) {
                intersection = new Point(xCoordinateOfIntersection, yCoordinateOfIntersection);
            }else {
                System.err.println("Points do not form paths that intersect");
            }
        } else {
            System.err.println("Points do not form paths that intersect");
        }
        return intersection;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean containsAsymptotes(Point velocity1, Point velocity2) {
        return velocity1.xCoordinate.equals(zero)
                || velocity1.yCoordinate.equals(zero)
                || velocity2.xCoordinate.equals(zero)
                || velocity2.yCoordinate.equals(zero);
    }

    public static boolean checkIfHeadingTowardsEachOther
            (Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        BigDecimal distance = new Line(startingPoint1, startingPoint2).distance;
        BigDecimal timeToCheckDistanceDecrease = new BigDecimal(0.001);
        BigDecimal distance1 = new Line(
                new Point(
                        startingPoint1.xCoordinate.add(velocity1.xCoordinate.multiply(timeToCheckDistanceDecrease)),
                        startingPoint1.yCoordinate.add(velocity1.yCoordinate.multiply(timeToCheckDistanceDecrease))),
                new Point(
                        startingPoint2.xCoordinate.add(velocity2.xCoordinate.multiply(timeToCheckDistanceDecrease)),
                        startingPoint2.yCoordinate.add(velocity2.yCoordinate.multiply(timeToCheckDistanceDecrease))))
                .distance;
        return distance.compareTo(distance1) >= 0;
    }

    @SuppressWarnings("WeakerAccess")
    public static IntersectionInformation checkIfIntersects(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        return new IntersectionInformation(startingPoint1, velocity1, startingPoint2, velocity2);
    }

    private static BigDecimal getXcoordinateOfIntersection(BigDecimal coefficientFromPointAndSlope1,
                                                           BigDecimal slope1,
                                                           BigDecimal coefficientFromPointAndSlope2,
                                                           BigDecimal slope2) {
        return coefficientFromPointAndSlope1.subtract(coefficientFromPointAndSlope2).divide(slope2.subtract(slope1), calcPrecisionMathContext);
    }

    private static BigDecimal getCoefficientFromPointAndSlope(Point startingPoint1, BigDecimal slope1) {
        return startingPoint1.yCoordinate.add(startingPoint1.xCoordinate.multiply((slope1.negate())));
    }

    private static BigDecimal getSlopeFromVelocity(Point velocity) {
        return new BigDecimal(velocity.yCoordinate.divide(velocity.xCoordinate, new MathContext(SystemGlobal.CALC_PRECISION)).toString());
    }

    public static BigDecimal getTimeOffsetOfCollision
            (Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        Point intersection = getIntersection(startingPoint1, velocity1, startingPoint2, velocity2);
        BigDecimal time1 = getTimeFromPointToIntersection(startingPoint1, velocity1, intersection);
        BigDecimal time2 = getTimeFromPointToIntersection(startingPoint2, velocity2, intersection);
        return time1.compareTo(time2) > 0 ? time1.subtract(time2) : time2.subtract(time1);
    }

    @SuppressWarnings("WeakerAccess")
    public static BigDecimal getTimeFromPointToIntersection(Point intersection, Point velocity1, Point startingPoint1) {
        BigDecimal velX1 = velocity1.xCoordinate;
        boolean yTrueXFalse = velX1.equals(new BigDecimal(0));
        BigDecimal line1CoordinateAgainstIntersection =
                yTrueXFalse? startingPoint1.yCoordinate : startingPoint1.xCoordinate;
        BigDecimal distanceOfCoordinateFromIntersection =
                yTrueXFalse ? line1CoordinateAgainstIntersection.subtract(intersection.yCoordinate)
                        : line1CoordinateAgainstIntersection.subtract(intersection.xCoordinate);
        BigDecimal time = yTrueXFalse ? distanceOfCoordinateFromIntersection.divide
                (velocity1.yCoordinate, new MathContext(SystemGlobal.CALC_PRECISION))
                : distanceOfCoordinateFromIntersection.divide
                (velX1, new MathContext(SystemGlobal.CALC_PRECISION));
        return time.abs();
    }

    //This is not accurate, the times have to be the same to determine the time at which they are closest,
    //Rather than at the offset of a collision
    public static BigDecimal getClosestDistanceOfTwoIntersectingTrejectorys
    (Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        //BigDecimal offsetCollisionTime = getTimeOffsetOfCollision(startingPoint1, velocity1, startingPoint2, velocity2);
        //The actual way to derive the closest distance/time is unknown at the time this funciton was written
        Point intersection = getIntersection(startingPoint1, velocity1, startingPoint2, velocity2);
        BigDecimal time1 = getTimeFromPointToIntersection(startingPoint1, velocity1, intersection);
        BigDecimal time2 = getTimeFromPointToIntersection(startingPoint2, velocity2, intersection);
        Point trajectoryPointAfterTime1 =
                getPointOfTrajectoryAfterTime(startingPoint1, velocity1, (time1.compareTo(time2) < 0) ? time1 : time2);
        Point trajectoryPointAfterTime2 =
                getPointOfTrajectoryAfterTime(startingPoint2, velocity2, (time1.compareTo(time2) < 0) ? time1 : time2);
        return new Line(trajectoryPointAfterTime1, trajectoryPointAfterTime2).distance;
    }

    private static Point getPointOfTrajectoryAfterTime(Point startingPoint, Point velocity, BigDecimal time) {
        return new Point(
                startingPoint.xCoordinate.add(velocity.xCoordinate.multiply(time, new MathContext(10))),
                startingPoint.yCoordinate.add(velocity.yCoordinate.multiply(time, new MathContext(10))));
    }
}
