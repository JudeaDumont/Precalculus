package Precalculus;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static Precalculus.SystemGlobal.EQUALITY_PRECISION;

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
        for (int i = 0; i < points.length; i++) {
            computedLines.add(new Line(new Point(points[i]), new Point(points[(i + 1) % points.length]), 1));
        }
        Point pointTemp2 = points[1];
        points[1] = points[0];
        points[0] = pointTemp;
        return computedLines.toArray(new Line[computedLines.size()]);
    }

    public static Line[] computeAllLinesFromPoints(Point[] points) {
        Collection<Line> computedLines = new ArrayList<Line>();
        points = PointPredicates.sortPoints(points);
        for (int i = 0; i < points.length; i++) {
            computedLines.add(new Line(new Point(points[i]), new Point(points[(i + 1) % points.length]), 1));
        }
        return computedLines.toArray(new Line[computedLines.size()]);
    }

    public static void calculateAndDisplayLines(Point[] trianglePoints) {
        Line[] lines = computeAllLinesFromPoints(trianglePoints);

        for (Line numberLine : lines) {
            System.out.println(numberLine.point1.pointName + " " + numberLine.point2.pointName + ": " + numberLine.distance);
        }
    }

    public static Point[] findPointsFromDistanceAndFixedXCoordinate(Point point, BigDecimal distance, BigDecimal xCoordinate) {
        return new Point[]
                {
                        new Point(xCoordinate,
                                new BigDecimal(Math.sqrt((xCoordinate.subtract(point.xCoordinate).pow(2)).subtract(distance.pow(2)).abs().doubleValue())).add(point.yCoordinate)
                        ),
                        new Point(xCoordinate,
                                new BigDecimal(Math.sqrt((xCoordinate.subtract(point.xCoordinate).pow(2)).subtract(distance.pow(2)).abs().doubleValue())).negate().add(point.yCoordinate)
                        )
                };
    }

    public static Point[] findPointsFromDistanceAndFixedYCoordinate(Point point, BigDecimal distance, BigDecimal yCoordinate) {
        return new Point[]
                {
                        new Point(new BigDecimal(Math.sqrt((yCoordinate.subtract(point.yCoordinate).pow(2)).subtract(distance.pow(2)).abs().doubleValue())).add(point.xCoordinate),
                                yCoordinate

                        ),
                        new Point(new BigDecimal(Math.sqrt((yCoordinate.subtract(point.yCoordinate).pow(2)).subtract(distance.pow(2)).abs().doubleValue())).negate().add(point.xCoordinate),
                                yCoordinate

                        )
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

    public static BigDecimal calculateDistanceBetweenTwoPointsAfterTime
            (Point startingPoint1, Point startingPoint2, Point point1Direction, Point point2Direction,
             BigDecimal point1Velocity, BigDecimal point2Velocity, BigDecimal time) {
        Point point1 = new Point(point1Direction);
        point1.xCoordinate = new BigDecimal(point1Direction.xCoordinate.multiply(time).add(startingPoint1.xCoordinate).toString());
        point1.yCoordinate = new BigDecimal(point1Direction.yCoordinate.multiply(time).add(startingPoint1.yCoordinate).toString());

        Point point2 = new Point(point2Direction);
        point2.xCoordinate = new BigDecimal(point2.xCoordinate.multiply(time).add(startingPoint2.xCoordinate).toString());
        point2.yCoordinate = new BigDecimal(point2.yCoordinate.multiply(time).add(startingPoint2.yCoordinate).toString());

        Line line = new Line(new Point(point1), new Point(point2), 1);
        return new BigDecimal(line.distance.toString());
    }

    public static BigDecimal calculateDistanceBetweenTwoPointsAfterTime
            (Point startingPoint1, Point startingPoint2, Point point1Direction, Point point2Direction,
             double point1Velocity, double point2Velocity, double time) {
        return calculateDistanceBetweenTwoPointsAfterTime(startingPoint1, startingPoint2, point1Direction, point2Direction,
                new BigDecimal(point1Velocity), new BigDecimal(point2Velocity), new BigDecimal(time));
    }

    public static Line doubleLine(Line line) {
        return new Line(new Point(line.point1), new Point(
                line.point2.xCoordinate.subtract(line.point1.xCoordinate).multiply(new BigDecimal(2)).add(line.point1.xCoordinate),
                line.point2.yCoordinate.subtract(line.point1.yCoordinate).multiply(new BigDecimal(2)).add(line.point1.yCoordinate)), 1);
    }

    public static Line createLineWithPointSlopeAndMagnitude(Point point, BigDecimal slope, BigDecimal magnitude) {
        if (slope.equals(new BigDecimal(0))) {
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
        return (line1.lineType == LineType.Vertical && line2.lineType == LineType.Horizontal || line1.lineType == LineType.Horizontal && line2.lineType == LineType.Vertical)
                || line1.slope.multiply(line2.slope).round(new MathContext(line1.slope.multiply(line2.slope).toBigInteger().toString().length() + EQUALITY_PRECISION))
                .equals(new BigDecimal(-1));
    }

    public static Line deriveAverageLine(Point[] points, Point startingPoint) {
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
        xTotal = xTotal.divide(new BigDecimal(points.length), new MathContext(SystemGlobal.CALC_PRECISION));
        yTotal = yTotal.divide(new BigDecimal(points.length), new MathContext(SystemGlobal.CALC_PRECISION));
        BigDecimal slope = yTotal.divide(xTotal, new MathContext(SystemGlobal.CALC_PRECISION));
        Line midLine = createLineWithPointSlopeAndMagnitude(new Point(new BigDecimal(xMin.toString()), xMin.multiply(slope)), slope, new BigDecimal(10));
        return midLine;
    }


    public static Point getIntersection(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        Point intersection = new Point(0, 0);
        boolean containsAsymptotes = containsAsymptotes(velocity1, velocity2);

        if (!containsAsymptotes) {
            if (checkIfIntersects(startingPoint1, velocity1, startingPoint2, velocity2)) {
                BigDecimal slope1 = getSlopeFromVelocity(velocity1);
                BigDecimal slope2 = getSlopeFromVelocity(velocity2);
                BigDecimal coefficientFromPointAndSlope1 = getCoefficientFromPointAndSlope(startingPoint1, slope1);
                BigDecimal coefficientFromPointAndSlope2 = getCoefficientFromPointAndSlope(startingPoint2, slope2);
                BigDecimal xcoordinateOfIntersection = getXcoordinateOfIntersection(coefficientFromPointAndSlope1, slope1, coefficientFromPointAndSlope2, slope2);
                BigDecimal yCoordinateOfIntersection = xcoordinateOfIntersection.multiply(slope1).add(coefficientFromPointAndSlope1);
                BigDecimal yCoordinateCheck = xcoordinateOfIntersection.multiply(slope2).add(coefficientFromPointAndSlope2);
                if (!yCoordinateOfIntersection.round(new MathContext(EQUALITY_PRECISION)).equals(yCoordinateCheck.round(new MathContext(EQUALITY_PRECISION)))) {
                    System.out.println("Upon getting the intersection the slope intercept form failed equivalent Y values");
                }
                intersection = new Point(xcoordinateOfIntersection, yCoordinateOfIntersection);
            } else {
                System.out.println("Points do not form paths that intersect");
            }
        } else {
            intersection = getIntersectionWithAsymptotes(startingPoint1, velocity1, startingPoint2, velocity2);
        }
        return intersection;
    }

    private static Point getIntersectionWithAsymptotes(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        boolean doesIntersect = checkIfIntersects(startingPoint1, velocity1, startingPoint2, velocity2);
        Point intersection = new Point(0, 0);
        BigDecimal xCoordinateOfIntersection = new BigDecimal(0);
        BigDecimal yCoordinateOfIntersection = new BigDecimal(0);
        if (doesIntersect) {
            if (velocity2.xCoordinate.equals(new BigDecimal(0)) && velocity1.yCoordinate.equals(new BigDecimal(0))) {
                xCoordinateOfIntersection = new BigDecimal(startingPoint2.xCoordinate.toString());
                yCoordinateOfIntersection = new BigDecimal(startingPoint1.yCoordinate.toString());
            } else if (velocity1.xCoordinate.equals(new BigDecimal(0)) && velocity2.yCoordinate.equals(new BigDecimal(0))) {
                xCoordinateOfIntersection = new BigDecimal(startingPoint1.xCoordinate.toString());
                yCoordinateOfIntersection = new BigDecimal(startingPoint2.yCoordinate.toString());
            } else if (velocity2.xCoordinate.equals(new BigDecimal(0))) {
                xCoordinateOfIntersection = new BigDecimal(startingPoint2.xCoordinate.toString());
                yCoordinateOfIntersection = startingPoint1.xCoordinate.subtract
                        (startingPoint2.xCoordinate).divide
                        (velocity1.xCoordinate, new MathContext(SystemGlobal.CALC_PRECISION)).multiply
                        (velocity1.yCoordinate);
            }
            intersection = new Point(xCoordinateOfIntersection, yCoordinateOfIntersection);
        } else {
            System.out.println("Points do not form paths that intersect");
        }
        return intersection;
    }

    public static boolean containsAsymptotes(Point velocity1, Point velocity2) {
        return velocity1.xCoordinate.equals(new BigDecimal(0))
                || velocity1.yCoordinate.equals(new BigDecimal(0))
                || velocity2.xCoordinate.equals(new BigDecimal(0))
                || velocity2.yCoordinate.equals(new BigDecimal(0));
    }

    private static boolean checkIfHeadingTowardsEachOther(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        BigDecimal distance = new Line(startingPoint1, startingPoint2).distance;
        BigDecimal distance1 = new Line(new Point(
                startingPoint1.xCoordinate.add(velocity1.xCoordinate.multiply(new BigDecimal(0.001))),
                startingPoint1.yCoordinate.add(velocity1.yCoordinate.multiply(new BigDecimal(0.001)))),
                new Point(
                        startingPoint2.xCoordinate.add(velocity2.xCoordinate.multiply(new BigDecimal(0.001))),
                        startingPoint2.yCoordinate.add(velocity2.yCoordinate.multiply(new BigDecimal(0.001))))).distance;
        boolean closer = distance.compareTo(distance1) > 0;
        return closer;
    }

    public static boolean checkIfIntersects(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        boolean futureIntersection = true;
        boolean headingTowardsEachOther = checkIfHeadingTowardsEachOther(startingPoint1, velocity1, startingPoint2, velocity2);
        if (headingTowardsEachOther) {
            BigDecimal line1HighestXPoint = null;
            BigDecimal line2HighestXPoint = null;
            BigDecimal line1HighestYPoint = null;
            BigDecimal line2HighestYPoint = null;
            BigDecimal line1LowestXPoint = null;
            BigDecimal line2LowestXPoint = null;
            BigDecimal line1LowestYPoint = null;
            BigDecimal line2LowestYPoint = null;

            if (velocity2.xCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line2HighestXPoint = startingPoint2.xCoordinate;
            }
            if (velocity1.xCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line1HighestXPoint = startingPoint1.xCoordinate;
            }
            if (velocity1.yCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line1HighestYPoint = startingPoint1.yCoordinate;
            }
            if (velocity2.yCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line2HighestYPoint = startingPoint2.yCoordinate;
            }
            if (velocity2.xCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line2LowestXPoint = startingPoint2.xCoordinate;
            }
            if (velocity1.xCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line1LowestXPoint = startingPoint1.xCoordinate;
            }
            if (velocity1.yCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line1LowestYPoint = startingPoint1.yCoordinate;
            }
            if (velocity2.yCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line2LowestYPoint = startingPoint2.yCoordinate;
            }
            if (line1HighestXPoint != null && line2LowestXPoint != null) {
                if (line1HighestXPoint.compareTo(line2LowestXPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1LowestXPoint != null && line2HighestXPoint != null) {
                if (line2HighestXPoint.compareTo(line1LowestXPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1HighestYPoint != null && line2LowestYPoint != null) {
                if (line1HighestYPoint.compareTo(line2LowestYPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1LowestYPoint != null && line2HighestYPoint != null) {
                if (line2HighestYPoint.compareTo(line1LowestYPoint) < 1) {
                    futureIntersection = false;
                }
            }
        } else {
            futureIntersection = false;
        }
        return futureIntersection;
    }

    private static BigDecimal getXcoordinateOfIntersection(BigDecimal coefficientFromPointAndSlope1,
                                                           BigDecimal slope1,
                                                           BigDecimal coefficientFromPointAndSlope2,
                                                           BigDecimal slope2) {
        BigDecimal combinedCoefficient = coefficientFromPointAndSlope1.subtract(coefficientFromPointAndSlope2);
        BigDecimal combinedSlope = slope2.subtract(slope1);
        BigDecimal xCoordinate = combinedCoefficient.divide(combinedSlope, new MathContext(SystemGlobal.CALC_PRECISION));
        return xCoordinate;
    }

    private static BigDecimal getCoefficientFromPointAndSlope(Point startingPoint1, BigDecimal slope1) {
        BigDecimal coefficient = new BigDecimal(
                startingPoint1.yCoordinate.add(
                        new BigDecimal(
                                startingPoint1.xCoordinate.multiply(
                                        new BigDecimal(slope1.negate().toString())
                                ).toString()
                        )
                ).toString()
        );
        return coefficient;
    }

    private static BigDecimal getSlopeFromVelocity(Point velocity) {
        return new BigDecimal(
                velocity.yCoordinate.divide(velocity.xCoordinate).toString(), new MathContext(SystemGlobal.CALC_PRECISION)
        );
    }

    public static BigDecimal getTimeOffsetOfCollision(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        Point intersection = getIntersection(startingPoint1, velocity1, startingPoint2, velocity2);
        BigDecimal time1 = getTimeFromPointToIntersection(startingPoint1, velocity1, intersection);
        BigDecimal time2 = getTimeFromPointToIntersection(startingPoint2, velocity2, intersection);
        return time1.compareTo(time2) > 0 ? time1.subtract(time2) : time2.subtract(time1);
    }


    public static BigDecimal getTimeFromPointToIntersection(Point intersection, Point velocity1, Point startingPoint1) {
        boolean yTrueXFalse = velocity1.xCoordinate.equals(new BigDecimal(0));
        BigDecimal line1CoordinateAgainstIntersection =
                yTrueXFalse
                        ? startingPoint1.yCoordinate : startingPoint1.xCoordinate;
        BigDecimal distanceOfCoordinateFromIntersection =
                yTrueXFalse ? line1CoordinateAgainstIntersection.subtract(intersection.yCoordinate)
                        : line1CoordinateAgainstIntersection.subtract(intersection.xCoordinate);
        BigDecimal time = yTrueXFalse ? distanceOfCoordinateFromIntersection.divide(velocity1.yCoordinate, new MathContext(SystemGlobal.CALC_PRECISION))
                : distanceOfCoordinateFromIntersection.divide(velocity1.xCoordinate, new MathContext(SystemGlobal.CALC_PRECISION));
        return time.abs();
    }

    //This is not accurate, the times have to be the same to determine the time at which they are closest,
    //Rather than at the offset of a collision
    public static BigDecimal getClosestDistanceOfTwoIntersectingTrejectorys(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        //BigDecimal offsetCollisionTime = getTimeOffsetOfCollision(startingPoint1, velocity1, startingPoint2, velocity2);
        //The actual way to derive the closest distance/time is unknown at the time this funciton was written
        Point intersection = getIntersection(startingPoint1, velocity1, startingPoint2, velocity2);
        BigDecimal time1 = getTimeFromPointToIntersection(startingPoint1, velocity1, intersection);
        BigDecimal time2 = getTimeFromPointToIntersection(startingPoint2, velocity2, intersection);
        Point trajectoryPointAfterTime1 = getPointOfTrajectoryAfterTime(startingPoint1, velocity1, (time1.compareTo(time2) < 0) ? time1 : time2);
        Point trajectoryPointAfterTime2 = getPointOfTrajectoryAfterTime(startingPoint2, velocity2, (time1.compareTo(time2) < 0) ? time1 : time2);
        return new Line(trajectoryPointAfterTime1, trajectoryPointAfterTime2).distance;
    }

    private static Point getPointOfTrajectoryAfterTime(Point startingPoint, Point velocity, BigDecimal time) {
        return new Point(
                startingPoint.xCoordinate.add(velocity.xCoordinate.multiply(time, new MathContext(10))),
                startingPoint.yCoordinate.add(velocity.yCoordinate.multiply(time, new MathContext(10))));
    }
}
