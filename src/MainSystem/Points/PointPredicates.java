package MainSystem.Points;

import MainSystem.Lines.Line;
import MainSystem.Lines.LinePredicates;

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
        if (!allEqual) {
            BigDecimal max = new BigDecimal(Double.MAX_VALUE).negate();
            for (int i = 0; i < swappingPoints.size(); i++) {
                for (int i1 = 0; i1 < swappingPoints.size(); i1++) {
                    if (i > i1 && swappingPoints.get(i).yCoordinate.compareTo(swappingPoints.get(i1).yCoordinate) <= 0) {
                        if (swappingPoints.get(i).yCoordinate.compareTo(swappingPoints.get(i1).yCoordinate) == 0) {
                            if (swappingPoints.get(i).xCoordinate.compareTo(swappingPoints.get(i1).xCoordinate) < 0) {
                                Point temp = swappingPoints.get(i);
                                swappingPoints.set(i, swappingPoints.get(i1));
                                swappingPoints.set(i1, temp);
                            }
                        } else {
                            Point temp = swappingPoints.get(i);
                            swappingPoints.set(i, swappingPoints.get(i1));
                            swappingPoints.set(i1, temp);
                        }
                    }
                }
                if (swappingPoints.get(i).yCoordinate.compareTo(max) > 0) {
                    max = new BigDecimal(swappingPoints.get(i).yCoordinate.toString());
                }
            }
            for (int i = 0; i < swappingPoints.size(); i++) {
                if (swappingPoints.get(i).yCoordinate.equals(max)) {
                    List<Point> flip = swappingPoints.subList(i, swappingPoints.size() - 1);
                    List<Point> flipadd = new ArrayList<>(flip);
                    swappingPoints.removeAll(flip);
                    Collections.reverse(flipadd);
                    swappingPoints.addAll(flipadd);
                    break;
                }
            }
        }
        return (swappingPoints.toArray(new Point[swappingPoints.size()]));
    }

    public static boolean areAllPointsEqual(Point[] points) {
        boolean allEqual = true;
        for (int i = 0; i < points.length; i++) {
            if (!points[i].equals(points[(i + 1) % points.length])) {
                allEqual = false;
                break;
            }
        }
        return allEqual;
    }

    public static boolean symmetricAboutXAxis(Point[] points) {
        int matches = 0;
        for (Point point : points) {
            for (Point point1 : points) {
                if (point.xCoordinate.equals(point1.xCoordinate) &&
                        point.yCoordinate.equals(point1.yCoordinate.negate())) {
                    ++matches;
                    if (matches == points.length) {
                        break;
                    }
                }
            }
        }
        return matches == points.length;
    }

    public static Point[] getXAxisReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        for (int i1 = 0; i1 < reflectedPoints.size(); i1++) {
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(reflectedPoints.get(i1).xCoordinate,
                                    reflectedPoints.get(i1).yCoordinate.negate()));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(reflectedPoints.get(i1).xCoordinate,
                                reflectedPoints.get(i1).yCoordinate.negate()));
            }
        }
        return reflectedPoints.toArray(new Point[reflectedPoints.size()]);
    }

    public static Point[] getYAxisReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        for (int i1 = 0; i1 < reflectedPoints.size(); i1++) {
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(reflectedPoints.get(i1).xCoordinate.negate(),
                                    reflectedPoints.get(i1).yCoordinate));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(reflectedPoints.get(i1).xCoordinate.negate(),
                                reflectedPoints.get(i1).yCoordinate));
            }
        }
        return reflectedPoints.toArray(new Point[reflectedPoints.size()]);
    }

    public static Point[] getOriginReflected(Point[] points) {
        Point[] pointsCopy = points.clone();
        ArrayList<Point> reflectedPoints = new ArrayList<Point>(Arrays.asList(pointsCopy));
        for (int i1 = 0; i1 < reflectedPoints.size(); i1++) {
            boolean reflectionExists = doesDuplicateExist
                    (reflectedPoints,
                            new Point(reflectedPoints.get(i1).xCoordinate.negate(),
                                    reflectedPoints.get(i1).yCoordinate.negate()));
            if (!reflectionExists) {
                reflectedPoints.add
                        (new Point(reflectedPoints.get(i1).xCoordinate.negate(),
                                reflectedPoints.get(i1).yCoordinate.negate()));
            }
        }
        return reflectedPoints.toArray(new Point[reflectedPoints.size()]);
    }

    public static boolean doesDuplicateExist(ArrayList<Point> reflectedPoints, Point obj) {
        boolean reflectionExists = false;
        for (int i = 0; i < reflectedPoints.size(); i++) {
            if (reflectedPoints.get(i).equals(obj)) {
                reflectionExists = true;
                i = reflectedPoints.size();
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

    public static Point[] sortPointsByXThenY(Point[] points) {
        ArrayList<Point> swappingPoints = new ArrayList<Point>(Arrays.asList(points));
        for (int i = 0; i < swappingPoints.size(); i++) {
            for (int i1 = 0; i1 < swappingPoints.size(); i1++) {
                if (i > i1 && swappingPoints.get(i).xCoordinate.compareTo(swappingPoints.get(i1).xCoordinate) <= 0) {
                    if (swappingPoints.get(i).xCoordinate.compareTo(swappingPoints.get(i1).xCoordinate) == 0) {
                        if (swappingPoints.get(i).yCoordinate.compareTo(swappingPoints.get(i1).yCoordinate) < 0) {
                            Point temp = swappingPoints.get(i);
                            swappingPoints.set(i, swappingPoints.get(i1));
                            swappingPoints.set(i1, temp);
                        }
                    } else {
                        Point temp = swappingPoints.get(i);
                        swappingPoints.set(i, swappingPoints.get(i1));
                        swappingPoints.set(i1, temp);
                    }
                }
            }
        }
        return swappingPoints.toArray(new Point[swappingPoints.size()]);
    }

    public static Point[] sortPointsByX(Point[] points) {
        ArrayList<Point> swappingPoints = new ArrayList<Point>(Arrays.asList(points));
        for (int i = 0; i < swappingPoints.size(); i++) {
            for (int i1 = 0; i1 < swappingPoints.size(); i1++) {
                if (i > i1 && swappingPoints.get(i).xCoordinate.compareTo(swappingPoints.get(i1).xCoordinate) < 0) {
                    Point temp = swappingPoints.get(i);
                    swappingPoints.set(i, swappingPoints.get(i1));
                    swappingPoints.set(i1, temp);
                }
            }
        }
        return swappingPoints.toArray(new Point[swappingPoints.size()]);
    }

    public static Line[] getLinesOfAllPointsSortedByXCoordinatesThenYCoordinates(Point[] points) {
        Point[] sortedPoints = sortPointsByXThenY(points);
        return LinePredicates.computeAllLinesFromPoints(sortedPoints);
    }

    public static Point getMaximumPoint(Point[] points) {
        BigDecimal max = new BigDecimal(-Double.MAX_VALUE);
        Point maxPoint = new Point(0, 0);
        for (int i = 0; i < points.length; i++) {
            if (points[i].xCoordinate.add(points[i].yCoordinate).compareTo(max) > 0) {
                max = points[i].xCoordinate.add(points[i].yCoordinate);
                maxPoint = points[i];
            }
        }
        return maxPoint;
    }

    public static Point getMinimumPoint(Point[] points) {
        BigDecimal min = new BigDecimal(Double.MAX_VALUE);
        Point minPoint = new Point(0, 0);
        for (int i = 0; i < points.length; i++) {
            if (points[i].xCoordinate.add(points[i].yCoordinate).compareTo(min) < 0) {
                min = points[i].xCoordinate.add(points[i].yCoordinate);
                minPoint = points[i];
            }
        }
        return minPoint;
    }

    public static Point[] getMaximas(Point[] points) {
        Point[] pointsSortedByX = sortPointsByX(points);
        ArrayList<Point> maximas = new ArrayList<Point>();
        for (int i = 1; i < pointsSortedByX.length - 1; i++) {
            if (pointsSortedByX[i].yCoordinate.compareTo(pointsSortedByX[i - 1].yCoordinate) > 0 &&
                    pointsSortedByX[i].yCoordinate.compareTo(pointsSortedByX[i + 1].yCoordinate) > 0) {
                maximas.add(pointsSortedByX[i]);
            }
        }
        return maximas.toArray(new Point[maximas.size()]);
    }

    public static Point[] getMinimas(Point[] points) {
        Point[] pointsSortedByX = sortPointsByX(points);
        ArrayList<Point> minimas = new ArrayList<Point>();
        for (int i = 1; i < pointsSortedByX.length - 1; i++) {
            if (pointsSortedByX[i].yCoordinate.compareTo(pointsSortedByX[i - 1].yCoordinate) < 0 &&
                    pointsSortedByX[i].yCoordinate.compareTo(pointsSortedByX[i + 1].yCoordinate) < 0) {
                minimas.add(pointsSortedByX[i]);
            }
        }
        return minimas.toArray(new Point[minimas.size()]);
    }

    public static Point[] correctPointSize(Point[] points, int correctSizeOfPoints) {
        Point[] fullPoints = new Point[correctSizeOfPoints];
        if (points.length > 0) {
            if (points.length >= fullPoints.length) {
                //If the points passed in are equal to or greater than
                // the number of points that are supposed to be in the shape, subset them
                fullPoints =
                        (Arrays.asList(points).subList(0, fullPoints.length)).toArray(new Point[fullPoints.length]);
            } else {
                //If the number of points passed in is less than the number that are supposed to be there for a
                // particular shape, we add the ones that are there and fill the rest with (0,0)
                for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
                    fullPoints[pointIndex] = points[pointIndex];
                }
                for (int pointIndex = points.length; pointIndex < fullPoints.length; pointIndex++) {
                    fullPoints[pointIndex] = new Point(0, 0);
                }
            }
        } else {
            //There are no points, we fill the shape with (0,0)
            for (int i = 0; i < fullPoints.length; i++) {
                fullPoints[i] = new Point(0, 0);
            }
        }
        return fullPoints;
    }

    public static Point[] transformPoints(Point[] points, Function<Point, Point> pointBasedMethod) {
        Point[] transformedPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            transformedPoints[i] = pointBasedMethod.apply(points[i]);
        }
        return transformedPoints;
    }
}
