package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class EqualateralTriangle extends Triangle {
    @Override
    public String getShapeType() {
        return "EqualateralTriangle";
    }

    protected EqualateralTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Equalateral;
    }

    @Override
    public void calcShapeArea() {
        area = lines[0].distance.multiply(getMeridians()[0].distance).divide(new BigDecimal(2)
        , new MathContext(lines[0].distance.multiply(getMeridians()[0].distance)
                        .divide(new BigDecimal(2)).toBigInteger().toString().length() + EQUALITY_PRECISION));
    }
}
