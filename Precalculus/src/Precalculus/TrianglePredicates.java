package Precalculus;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Owner on 7/10/2017.
 */
public class TrianglePredicates {
    public static Triangle[] makeTrianglesFromLine(Line line) {
        return
                new Triangle[]{
                        Triangle.createInstance(new Point[]{new Point(line.getMidPoint()), new Point(line.point1), new Point(line.getMidPoint().xCoordinate, line.point1.yCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(line.getMidPoint()), new Point(line.point2), new Point(line.point2.xCoordinate, line.getMidPoint().yCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(line.getMidPoint()), new Point(line.point1), new Point(line.point1.xCoordinate, line.getMidPoint().yCoordinate)}),
                        Triangle.createInstance(new Point[]{new Point(line.getMidPoint()), new Point(line.point2), new Point(line.getMidPoint().xCoordinate, line.point2.yCoordinate)})};

    }

    public static Triangle[] inferTrianglePoint(Point[] twoPoints, TriangleShapeType triangleShapeType) {
        Triangle[] triangles = new Triangle[2];
        if (triangleShapeType != TriangleShapeType.Isoselece && twoPoints.length == 2) {
            if (triangleShapeType == TriangleShapeType.Equalateral) {
            } else {
                System.out.println("infering a point of a right triangle is not supported");
            }
        } else if (TriangleShapeType.Isoselece == triangleShapeType) {
            System.out.println("You cannot infer the point of an isoselece triangle");
        } else if (twoPoints.length < 2) {
            System.out.println("You cannot arbitrarily infer two points of a triangle");
        } else if (twoPoints.length > 2) {
            System.out.println("You supplied too many points, implying no inference necessary");
        }
        return triangles;
    }

    public static TriangleShapeType determineTriangleType(Line[] lines) {
        //Determining the type of triangle can be less precise, to the first whole number digit
        BigDecimal[] lengths = LinePredicates.getLineLengths(lines);
        TriangleShapeType type = null;
        if ((lengths[0].equals(lengths[1]) || lengths[0].equals(lengths[2]) || lengths[1].equals(lengths[2])) && ((lengths[0].pow(2).add(lengths[1].pow(2)))
                .round(new MathContext((lengths[0].pow(2).add(lengths[1].pow(2))).toBigInteger().toString().length())).stripTrailingZeros()
                .toString().equals((lengths[2].pow(2).round(new MathContext(lengths[2].pow(2).toBigInteger().toString().length())).stripTrailingZeros().toString())))) {
            type = TriangleShapeType.RightIsoselece;
        } else if ((lengths[0].pow(2).add(lengths[1].pow(2)))
                .round(new MathContext((lengths[0].pow(2).add(lengths[1].pow(2))).toBigInteger().toString().length())).stripTrailingZeros()
                .toString().equals((lengths[2].pow(2).round(new MathContext(lengths[2].pow(2).toBigInteger().toString().length())).stripTrailingZeros().toString()))) {
            type = TriangleShapeType.Right;
        } else if (lengths[0].equals(lengths[1]) && lengths[1].equals(lengths[2])) {
            type = TriangleShapeType.Equalateral;
        } else if (lengths[0].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(lengths[1].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                ) || lengths[0].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(lengths[2].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                ) || lengths[1].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                .equals(lengths[2].divide(new BigDecimal(1), new MathContext(SystemGlobal.EQUALITY_PRECISION))
                )) {
            type = TriangleShapeType.Isoselece;
        } else {
            type = TriangleShapeType.Scalene;
        }
        return type;
    }

    public static Triangle calculateEqualateralTriangleAtOriginFromDistance(BigDecimal distance) {
        try {
            return Triangle.createInstance(new Point[]{
                    new Point(0, 0),
                    new Point(distance, new BigDecimal(0)),
                    new Point(distance.divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION)),
                            new BigDecimal(Math.sqrt(distance.multiply(distance).subtract((distance.multiply(distance).divide(new BigDecimal(4), new MathContext(SystemGlobal.CALC_PRECISION)))).doubleValue()))
                    )});
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Distance is too large to create equalateral");
        }
        return null;
    }
}
