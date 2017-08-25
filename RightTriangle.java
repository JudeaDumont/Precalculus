package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class RightTriangle extends Triangle {

    protected RightTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Right;
    }

    @Override
    public String getShapeType() {
        return "RightTriangle";
    }

    @Override
    public void calcShapeArea() {
            BigDecimal[] lengths = LinePredicates.getLineLengths(lines);
            area = lengths[0].multiply(lengths[1]).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION))
                    .round(new MathContext(lengths[0].multiply(lengths[1]).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION))
                            .toBigInteger().toString().length() + EQUALITY_PRECISION));
    }
}
