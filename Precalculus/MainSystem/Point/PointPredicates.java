package MainSystem.Point;

import MainSystem.Line.Line;
import MainSystem.Line.LinePredicates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class PointPredicates {
    public static String getPointString(Point[] points) {
        String pointString = "";
        for (Point point : points) {
            pointString = pointString.concat((point.pointName.equals("") ? "Unnamed" : point.pointName) +
                    " (" + point.xCoordinate.toString() +
                    "," + point.yCoordinate.toString() + ")\n");
        }
        return pointString;
    }

    public static Point[] sortPoints(Point[] points) {
        ArrayList<Point> swappingPoints = new ArrayList<Point>(Arrays.asList(points));
        boolean allEqual = areAllPointsEqual(points);
        int size = swappingPoints.size();
        if (!allEqual) {
            BigDecimal max = new BigDecimal(Double.MAX_VALUE).negate();
            for (int i = 0; i < size; i++) {
                for (int i1 = 0; i1 < size; i1++) {
                    Point swaper = swappingPoints.get(i);
                    Point swapy = swappingPoints.get(i1);
                    BigDecimal swaperYCoordinate = swaper.yCoordinate;
                    BigDecimal swapyY = swapy.yCoordinate;
                    int swaperCompareToSwapy = swaperYCoordinate.compareTo(swapyY);
                    if (i > i1 && swaperCompareToSwapy <= 0) {
                        if (swaperCompareToSwapy == 0) {
                            if (swaper.xCoordinate.compareTo(swapy.xCoordinate) < 0) {
                                swappingPoints.set(i, swapy);
                                swappingPoints.set(i1, swaper);
                            }
                        } else {
                            swappingPoints.set(i, swapy);
                            swappingPoints.set(i1, swaper);
                        }
                    }
                }
                BigDecimal yCoordinate = swappingPoints.get(i).yCoordinate;
                if (yCoordinate.compareTo(max) > 0) {
                    max = new BigDecimal(yCoordinate.toString());
                }
            }
            for (int i = 0; i < size; i++) {
                if (swappingPoints.get(i).yCoordinate.equals(max)) {
                    List<Point> flip = swappingPoints.subList(i, size - 1);
                    List<Point> flipadd = new ArrayList<>(flip);
                    swappingPoints.removeAll(flip);
                    Collections.reverse(flipadd);
                    swappingPoints.addAll(flipadd);
                    break;
                }
            }
        }
        return (swappingPoints.toArray(new Point[size]));
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean areAllPointsEqual(Point[] points) {
        boolean allEqual = true;
        int length = points.length;
        for (int i = 0; i < length; i++) {
            if (!points[i].equals(points[(i + 1) % length])) {
                allEqual = false;
                break;
            }
        }
        return allEqual;
    }

    public static boolean symmetricAboutXAxis(Point[] points) {
        int matches = 0;
        int length = points.length;
        for (Point point : points) {
            for (Point point1 : points) {
                if (point.xCoordinate.equals(point1.xCoordinate) &&
                        point.yCoordinate.equals(point1.yCoordinate.negate())) {
                    ++matches;
                    if (matches == length) {
                        break;
                    }
                }
            }
        }
        return matches == length;
    }

    public static Point[] getXAxisReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        int size = reflectedPoints.size();
        for (int i1 = 0; i1 < size; i1++) {
            Point point = reflectedPoints.get(i1);
            BigDecimal xCoord = point.xCoordinate;
            BigDecimal yCoordNegate = point.yCoordinate.negate();
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(xCoord,
                                    yCoordNegate));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(xCoord,
                                yCoordNegate));
            }
        }
        return reflectedPoints.toArray(new Point[size]);
    }

    public static Point[] getYAxisReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        for (int i1 = 0; i1 < reflectedPoints.size(); i1++) {
            BigDecimal xNegate = reflectedPoints.get(i1).xCoordinate.negate();
            BigDecimal Y = reflectedPoints.get(i1).yCoordinate;
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(xNegate,
                                    Y));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(xNegate,
                                Y));
            }
        }
        return reflectedPoints.toArray(new Point[reflectedPoints.size()]);
    }

    public static Point[] getOriginReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        for (int i1 = 0; i1 < reflectedPoints.size(); i1++) {
            Point point = reflectedPoints.get(i1);
            BigDecimal xNegate = point.xCoordinate.negate();
            BigDecimal yNegate = point.yCoordinate.negate();
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(xNegate,
                                    yNegate));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(xNegate,
                                yNegate));
            }
        }
        return reflectedPoints.toArray(new Point[reflectedPoints.size()]);
    }

    public static boolean doesDuplicateExist(ArrayList<Point> reflectedPoints, Point obj) {
        boolean reflectionExists = false;
        for (int i = 0; i < reflectedPoints.size(); i++) {
            if (reflectedPoints.get(i).equals(obj)) {
                reflectionExists = true;
                break;
            }
        }
        return reflectionExists;
    }

    public static boolean symmetricAboutYAxis(Point[] points) {
        int matches = 0;
        for (Point point : points) {
            for (Point point1 : points) {
                if (point.yCoordinate.equals(point1.yCoordinate) &&
                        point.xCoordinate.equals(point1.xCoordinate.negate())) {
                    ++matches;
                    if (matches == points.length) {
                        break;
                    }
                }
            }
        }
        return matches == points.length;
    }

    public static boolean symmetricAboutOrigin(Point[] points) {
        int matches = 0;
        for (Point point : points) {
            for (Point point1 : points) {
                if (point.yCoordinate.equals(point1.yCoordinate.negate()) &&
                        point.xCoordinate.equals(point1.xCoordinate.negate())) {
                    ++matches;
                    if (matches == points.length) {
                        break;
                    }
                }
            }
        }
        return matches == points.length;
    }

    public static boolean checkIfPointsRepresentFunction(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int i1 = 0; i1 < points.length; i1++) {
                if (i != i1 && points[i].xCoordinate.equals(points[i1].xCoordinate) &&
                        !points[i].yCoordinate.equals(points[i1].yCoordinate)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] sortPointsByXThenY(Point[] points) {
        ArrayList<Point> swappingPoints = new ArrayList<Point>(Arrays.asList(points));
        int size = swappingPoints.size();
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size; i1++) {
                Point point = swappingPoints.get(i);
                BigDecimal xCoordinate = point.xCoordinate;
                Point point1 = swappingPoints.get(i1);
                BigDecimal xCoordinate1 = point1.xCoordinate;
                int xCoordComparison = xCoordinate.compareTo(xCoordinate1);
                if (i > i1 && xCoordComparison <= 0) {
                    if (xCoordComparison == 0) {
                        if (point.yCoordinate.compareTo(point1.yCoordinate) < 0) {
                            swappingPoints.set(i, point1);
                            swappingPoints.set(i1, point);
                        }
                    } else {
                        swappingPoints.set(i, point1);
                        swappingPoints.set(i1, point);
                    }
                }
            }
        }
        return swappingPoints.toArray(new Point[size]);
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] sortPointsByX(Point[] points) {
        ArrayList<Point> swappingPoints = new ArrayList<Point>(Arrays.asList(points));
        int size = swappingPoints.size();
        for (int i = 0; i < size; i++) {
            for (int i1 = 0; i1 < size; i1++) {
                Point point = swappingPoints.get(i);
                Point point1 = swappingPoints.get(i1);
                if (i > i1 && point.xCoordinate.compareTo(point1.xCoordinate) < 0) {
                    swappingPoints.set(i, point1);
                    swappingPoints.set(i1, point);
                }
            }
        }
        return swappingPoints.toArray(new Point[size]);
    }

    public static Line[] getLinesOfAllPointsSortedByXCoordinatesThenYCoordinates(Point[] points) {
        Point[] sortedPoints = sortPointsByXThenY(points);
        return LinePredicates.computeAllLinesAndSortPoints(sortedPoints);
    }

    public static Point getMaximumPoint(Point[] points) {
        BigDecimal max = new BigDecimal(-Double.MAX_VALUE);
        Point maxPoint = null;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            BigDecimal coordSum = point.xCoordinate.add(point.yCoordinate);
            if (coordSum.compareTo(max) > 0) {
                max = coordSum;
                maxPoint = point;
            }
        }
        return maxPoint;
    }

    public static Point getMinimumPoint(Point[] points) {
        BigDecimal min = new BigDecimal(Double.MAX_VALUE);
        Point minPoint = null;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            BigDecimal coordSum = point.xCoordinate.add(point.yCoordinate);
            if (coordSum.compareTo(min) < 0) {
                min = coordSum;
                minPoint = point;
            }
        }
        return minPoint;
    }

    public static Point[] getMaximas(Point[] points) {
        Point[] pointsSortedByX = sortPointsByX(points);
        ArrayList<Point> maximas = new ArrayList<Point>();
        for (int i = 1; i < pointsSortedByX.length - 1; i++) {
            Point point = pointsSortedByX[i];
            BigDecimal yCoordinate = point.yCoordinate;
            if (yCoordinate.compareTo(pointsSortedByX[i - 1].yCoordinate) > 0 &&
                    yCoordinate.compareTo(pointsSortedByX[i + 1].yCoordinate) > 0) {
                maximas.add(point);
            }
        }
        return maximas.toArray(new Point[maximas.size()]);
    }

    public static Point[] getMinimas(Point[] points) {
        Point[] pointsSortedByX = sortPointsByX(points);
        ArrayList<Point> minimas = new ArrayList<Point>();
        for (int i = 1; i < pointsSortedByX.length - 1; i++) {
            Point point = pointsSortedByX[i];
            BigDecimal yCoordinate = point.yCoordinate;
            if (yCoordinate.compareTo(pointsSortedByX[i - 1].yCoordinate) < 0 &&
                    yCoordinate.compareTo(pointsSortedByX[i + 1].yCoordinate) < 0) {
                minimas.add(point);
            }
        }
        return minimas.toArray(new Point[minimas.size()]);
    }

    public static Point[] correctPointSize(Point[] points, int correctSizeOfPoints) {
        Point[] fullPoints = new Point[correctSizeOfPoints];
        int length = points.length;
        int length1 = fullPoints.length;
        if (length > 0) {
            if (length >= length1) {
                //If the points passed in are equal to or greater than
                // the number of points that are supposed to be in the shape, subset them
                fullPoints = (Arrays.asList(points).subList(0, length1)).toArray(new Point[length1]);
            } else {
                //If the number of points passed in is less than the number that are supposed to be there for a
                // particular shape, we add the ones that are there and fill the rest with (0,0)
                for (int pointIndex = 0; pointIndex < length; pointIndex++) {
                    fullPoints[pointIndex] = points[pointIndex];
                }
                for (int pointIndex = length; pointIndex < length1; pointIndex++) {
                    fullPoints[pointIndex] = new Point(0, 0);
                }
            }
        } else {
            //There are no points, we fill the shape with (0,0)
            for (int i = 0; i < length1; i++) {
                fullPoints[i] = new Point(0, 0);
            }
        }
        return fullPoints;
    }

    public static Point[] transformPoints(Point[] points, Function<Point, Point> pointBasedMethod) {
        int length = points.length;
        Point[] transformedPoints = new Point[length];
        for (int i = 0; i < length; i++) {
            transformedPoints[i] = pointBasedMethod.apply(points[i]);
        }
        return transformedPoints;
    }
}
