package MainSystem.Triangle;

import MainSystem.Line.Line;
import MainSystem.Line.LinePredicates;
import MainSystem.Point.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.SystemGlobal.SystemGlobal.calcPrecisionMathContext;
import static MainSystem.SystemGlobal.SystemGlobal.equalityPrecisionMathContext;

public class TrianglePredicates {
    public static Triangle[] makeTrianglesFromLine(Line line) {
        Point point1 = line.point1;
        Point point2 = line.point2;
        Point midPoint = line.getMidPoint();
        BigDecimal midPointXCoordinate = midPoint.xCoordinate;
        BigDecimal midPointYCoordinate = midPoint.yCoordinate;
        return
                new Triangle[]{
                        Triangle.createInstance(new Point[]{new Point(midPoint), new Point(point1),
                                new Point(midPointXCoordinate, point1.yCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(midPoint), new Point(point2),
                                new Point(point2.xCoordinate, midPointYCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(midPoint), new Point(point1),
                                new Point(point1.xCoordinate, midPointYCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(midPoint), new Point(point2),
                                new Point(midPointXCoordinate, point2.yCoordinate)})};

    }

    public static Triangle[] inferTrianglePoint(Point[] twoPoints, TriangleShapeType triangleShapeType) {
        Triangle[] triangles = new Triangle[2];
        int length = twoPoints.length;
        if (triangleShapeType != TriangleShapeType.Isoselece && length == 2) {
            if (triangleShapeType == TriangleShapeType.Equalateral) {
            } else {
                System.err.println("infering a point of a right triangle is not supported");
            }
        } else if (TriangleShapeType.Isoselece == triangleShapeType) {
            System.err.println("You cannot infer the point of an isoselece triangle");
        } else if (length < 2) {
            System.err.println("You cannot arbitrarily infer two points of a triangle");
        } else if (length > 2) {
            System.err.println("You supplied too many points, implying no inference necessary");
        }
        return triangles;
    }

    public static TriangleShapeType determineTriangleType(Line[] lines) {
        //Determining the type of triangle can be less precise, to the first whole number digit
        BigDecimal[] lengths = LinePredicates.getLineLengths(lines);
        TriangleShapeType type;
        BigDecimal lengths0 = lengths[0];
        BigDecimal lengths1 = lengths[1];
        BigDecimal lengths2 = lengths[2];
        boolean equals0and1 = lengths0.equals(lengths1);
        boolean equals1and2 = lengths1.equals(lengths2);
        BigDecimal pythagoreanRight = lengths0.pow(2).add(lengths1.pow(2));
        BigDecimal lengths2ToThe2 = lengths2.pow(2);
        boolean pythagoreanRightTriangleTest = pythagoreanRight
                .round(new MathContext(pythagoreanRight.
                        toBigInteger().toString().length())).stripTrailingZeros()
                .toString().equals((lengths2ToThe2.round(
                        new MathContext(lengths2ToThe2.toBigInteger().toString().length()))
                        .stripTrailingZeros().toString()));
        if ((equals0and1 || lengths0.equals(lengths2) || equals1and2) &&
                pythagoreanRightTriangleTest) {
            type = TriangleShapeType.RightIsoselece;
        } else if (pythagoreanRightTriangleTest) {
            type = TriangleShapeType.Right;
        } else if (equals0and1 && equals1and2) {
            type = TriangleShapeType.Equalateral;
        } else {
            BigDecimal divisor = new BigDecimal(1);
            BigDecimal divide0 = lengths0.divide(divisor, equalityPrecisionMathContext);
            BigDecimal divide1 = lengths1.divide(divisor, equalityPrecisionMathContext);
            BigDecimal divide2 = lengths2.divide(divisor, equalityPrecisionMathContext);
            if (divide0
                    .equals(divide1
                    ) || divide0
                    .equals(divide2
                    ) || divide1
                    .equals(divide2
                    )) {
                type = TriangleShapeType.Isoselece;
            } else {
                type = TriangleShapeType.Scalene;
            }
        }
        return type;
    }

    public static Triangle calculateEqualateralTriangleAtOriginFromDistance(BigDecimal distance) {
        try {
            return Triangle.createInstance(new Point[]{
                    new Point(0, 0),
                    new Point(distance, new BigDecimal(0)),
                    new Point(distance.divide(new BigDecimal(2), calcPrecisionMathContext),
                            new BigDecimal(Math.sqrt(distance.multiply(distance).subtract
                                    ((distance.multiply(distance).divide(new BigDecimal(4),
                                            calcPrecisionMathContext))).doubleValue()))
                    )});
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Distance is too large to create equalateral");
        }
        return null;
    }
}
