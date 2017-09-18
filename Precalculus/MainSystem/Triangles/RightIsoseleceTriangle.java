package MainSystem.Triangles;

import MainSystem.Lines.LinePredicates;
import MainSystem.Points.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.GlobalSystem.SystemGlobal.EQUALITY_PRECISION;

public class RightIsoseleceTriangle extends Triangle {

    RightIsoseleceTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.RightIsoselece;
    }

    @Override
    public String getShapeType() {
        return "RightIsoseleceTriangle";
    }

    @Override
    public void calcShapeArea() {
        BigDecimal[] lengths = LinePredicates.getLineLengths(lines);
        BigDecimal length0 = lengths[0];
        BigDecimal length1 = lengths[1];
        BigDecimal divisor = new BigDecimal(2);
        area = length0.multiply(length1).divide(divisor
                , new MathContext(length0.multiply(length1).divide
                        (divisor).toBigInteger().toString().length() + EQUALITY_PRECISION));
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        return RightTrianglePredicates.pointInRightTriangle(point, this.points);
    }
}
