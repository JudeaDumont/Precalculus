package com.company;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.SystemGlobal.EQUALITY_PRECISION;

public class Line {
    public LineType lineType = null;
    public Point point1 = new Point(0, 0);
    public Point point2 = new Point(0, 0);
    public BigDecimal distance;
    public BigDecimal slope = new BigDecimal(0);
    //On a number line the scale is usually the distance between 0 and one
    public BigDecimal segmentScalar = new BigDecimal("1");

    public double calcDistance(Point point1, Point point2) {
        double distance = 0;
        try {
            //distance(point1, point2) = sqrt((x2 - x1)^2 + (y2 - y1)^2)
            distance = Double.parseDouble(new BigDecimal(
                    Math.sqrt(Double.parseDouble(((point2.xCoordinate.subtract(point1.xCoordinate).pow(2).add(point2.yCoordinate.subtract(point1.yCoordinate).pow(2)))).toString())))
                    .round(new MathContext(new BigDecimal(
                            Math.sqrt(Double.parseDouble(((point2.xCoordinate.subtract(point1.xCoordinate).pow(2).add(point2.yCoordinate.subtract(point1.yCoordinate).pow(2)))).toString()))).toBigInteger().toString().length() + EQUALITY_PRECISION)).toString()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Value of points was too large to calculate the distance into a double");
        }
        return distance;
    }

    private void initializeProperties(Point point1, Point point2, BigDecimal segmentScalar) {
        this.point1 = point1;
        this.point2 = point2;
        this.segmentScalar = segmentScalar;
        this.distance = new BigDecimal(calcDistance(this.point1, this.point2));
        this.slope = new BigDecimal(calcSlope(this.point1, this.point2).toString());
        this.lineType = (slope.equals(new BigDecimal(0)) ? (point1.xCoordinate.equals(point2.xCoordinate) ? LineType.Vertical : LineType.Horizontal) : LineType.Diagonal);
    }

    private BigDecimal calcSlope(Point point1, Point point2) {
        try {
            return !point1.xCoordinate.toString().equals(point2.xCoordinate.toString()) &&
                    !point1.yCoordinate.toString().equals(point2.yCoordinate.toString()) ? (
                    point2.yCoordinate.subtract(point1.yCoordinate)
                            .divide(
                                    point2.xCoordinate.subtract(point1.xCoordinate)
                                    , new MathContext(SystemGlobal.CALC_PRECISION))) : new BigDecimal(0);
        } catch (Exception ex) {

        }
        return new BigDecimal(0);
    }

    public Line(Point point1, Point point2, double segmentScalar) {
        initializeProperties(point1, point2, new BigDecimal(segmentScalar));
    }

    public Line(Point point1, Point point2) {
        initializeProperties(point1, point2, new BigDecimal(1));
    }

    public Line(Point point1, Point point2, BigDecimal segmentScalar) {
        initializeProperties(point1, point2, segmentScalar);
    }

    @Override
    public boolean equals(Object obj) {
        return (((Line) obj).point1.equals(this.point1)
                && ((Line) obj).point2.equals(this.point2));
    }

    @Override
    public String toString() {
        return point1.toString() + "to\n" + point2.toString() + "Length:" + distance + "\n" + "Slope:" + slope.toString() + "\n";
    }

    public Point getMidPoint() {
        return new Point(point1.xCoordinate.add(point2.xCoordinate).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION)),
                point1.yCoordinate.add(point2.yCoordinate).divide(new BigDecimal(2), new MathContext(SystemGlobal.CALC_PRECISION)));
    }

    public Point getXIntercept() {
        Point xIntercept = null;
        if (point1.xCoordinate.compareTo(new BigDecimal(0)) <= 0 && point2.xCoordinate.compareTo(new BigDecimal(0)) >= 0
                || point1.xCoordinate.compareTo(new BigDecimal(0)) >= 0 && point2.xCoordinate.compareTo(new BigDecimal(0)) <= 0) {
            xIntercept = (new Point(0,
                    point2.yCoordinate.subtract(
                            point2.yCoordinate.subtract(point1.yCoordinate).multiply(point2.xCoordinate.divide(
                                    point2.xCoordinate.subtract(point1.xCoordinate), new MathContext(SystemGlobal.CALC_PRECISION))))));
        }
        return xIntercept;
    }

    public Point getYIntercept() {
        Point yIntercept = null;
        if (point1.yCoordinate.compareTo(new BigDecimal(0)) <= 0 && point2.yCoordinate.compareTo(new BigDecimal(0)) >= 0
                || point1.yCoordinate.compareTo(new BigDecimal(0)) >= 0 && point2.yCoordinate.compareTo(new BigDecimal(0)) <= 0) {
            yIntercept = (new Point(
                    point2.xCoordinate.subtract(
                            point2.xCoordinate.subtract(point1.xCoordinate).multiply(point2.yCoordinate.divide(
                                    point2.yCoordinate.subtract(point1.yCoordinate), new MathContext(SystemGlobal.CALC_PRECISION)))), 0));
        }
        return yIntercept;
    }

}
