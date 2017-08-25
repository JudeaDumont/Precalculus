package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

/**
 * Created by Owner on 7/12/2017.
 */
public class IsoceleceTriangle extends Triangle {

    protected IsoceleceTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Isoselece;
    }

    @Override
    public String getShapeType() {
        return "IsoceleceTriangle";
    }

    @Override
    public void calcShapeArea() {
        Line[] equalLines = new Line[2];
        for (int i = 0; i < lines.length; i++) {
            for (int i1 = 0; i1 < lines.length; i1++) {
                if (i != i1 && lines[i].distance.toString().equals(lines[i1].distance.toString())) {
                    equalLines = new Line[]{lines[i], lines[i1]};
                    break;
                }
            }
        }
        Point commonPoint = null;
        if (equalLines[0].point1.equals(equalLines[1].point1) || equalLines[0].point1.equals(equalLines[1].point2)) {
            commonPoint = equalLines[0].point1;
        } else {
            commonPoint = equalLines[0].point2;
        }
        for (int i = 0; i < lines.length; i++) {
            for (Point point : points) {
                if (!point.equals(lines[i].point1) && !point.equals(lines[i].point2) && point.equals(commonPoint)) {
                    area = lines[i].distance.multiply(new Line(point, lines[i].getMidPoint(), 0).distance).divide(new BigDecimal(2))
                            .round(new MathContext(lines[i].distance.multiply(new Line(point, lines[i].getMidPoint(), 0).distance).divide(new BigDecimal(2)).toBigInteger().toString().length() + EQUALITY_PRECISION));
                    break;
                }
            }
        }
    }
}
