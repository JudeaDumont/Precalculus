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
        area = lengths[0].multiply(lengths[1]).divide(new BigDecimal(2)
                , new MathContext(lengths[0].multiply(lengths[1]).divide
                        (new BigDecimal(2)).toBigInteger().toString().length() + EQUALITY_PRECISION));
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        return false;
    }
}
