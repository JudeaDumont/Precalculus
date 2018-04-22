package MainSystem.Line;

import MainSystem.Point.Point;

import java.math.BigDecimal;
import java.math.MathContext;

import static MainSystem.SystemGlobal.SystemGlobal.*;

public class Line {
    public LineType lineType = null;
    public Point point1 = new Point(0, 0);
    public Point point2 = new Point(0, 0);
    public BigDecimal distance;
    public BigDecimal slope = new BigDecimal(0);
    //On a number line the scale is usually the distance between 0 and one
    @SuppressWarnings("WeakerAccess")
    public BigDecimal segmentScalar = new BigDecimal(1);

    public Line(Line line) {
        this.lineType = line.lineType;
        this.point1 = new Point(line.point1);
        this.point2 = new Point(line.point2);
        this.distance = new BigDecimal(line.distance.doubleValue());
        this.slope = new BigDecimal(line.slope.doubleValue());
    }

    @SuppressWarnings("WeakerAccess")
    public double calcDistance(Point point1, Point point2) {
        double distance = 0;
        try {
            //distance(point1, point2) = sqrt((x2 - x1)^2 + (y2 - y1)^2)
            BigDecimal length = new BigDecimal(Math.sqrt(Double.parseDouble(((
                    point2.xCoordinate.subtract(point1.xCoordinate).pow(2).add(point2.yCoordinate.subtract(point1.yCoordinate).pow(2)))).toString())));
            distance = Double.parseDouble(length.round(new MathContext(length.toBigInteger().toString().length() + EQUALITY_PRECISION)).toString()
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
        this.lineType = (slope.equals(zero)
                ? (point1.xCoordinate.equals(point2.xCoordinate)
                ? LineType.Vertical : LineType.Horizontal) : LineType.Diagonal);
    }

    private BigDecimal calcSlope(Point point1, Point point2) {
        try {
            BigDecimal xCoordinate1 = point1.xCoordinate;
            BigDecimal yCoordinate1 = point1.yCoordinate;
            BigDecimal yCoordinate2 = point2.yCoordinate;
            BigDecimal xCoordinate2 = point2.xCoordinate;
            return !xCoordinate1.equals(xCoordinate2) &&
                    !yCoordinate1.equals(yCoordinate2) ? (
                    yCoordinate2.subtract(yCoordinate1)
                            .divide(xCoordinate2.subtract(xCoordinate1)
                                    , calcPrecisionMathContext)) : new BigDecimal(0);
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
        BigDecimal divisor = new BigDecimal(2);
        return new Point(point1.xCoordinate.add(point2.xCoordinate).divide(divisor, calcPrecisionMathContext),
                point1.yCoordinate.add(point2.yCoordinate).divide(divisor, calcPrecisionMathContext));
    }

    @SuppressWarnings("UnusedReturnValue")
    public Point getXIntercept() {
        Point xIntercept = null;
        BigDecimal point1XCoordinate = point1.xCoordinate;
        BigDecimal point2XCoordinate = point2.xCoordinate;
        int x1NegOrPos = point1XCoordinate.compareTo(zero);
        int x2NegOrPos = point2XCoordinate.compareTo(zero);
        if (x1NegOrPos <= 0 && x2NegOrPos >= 0
                || x1NegOrPos >= 0 && x2NegOrPos <= 0) {
            BigDecimal point2y = point2.yCoordinate;
            xIntercept = (new Point(0,
                    point2y.subtract(
                            point2y.subtract(point1.yCoordinate).multiply(point2XCoordinate.divide(
                                    point2XCoordinate.subtract(point1XCoordinate), calcPrecisionMathContext)))));
        }
        return xIntercept;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Point getYIntercept() {
        Point yIntercept = null;
        BigDecimal point1YCoordinate = point1.yCoordinate;
        BigDecimal point2YCoordinate = point2.yCoordinate;
        int y1NegOrPos = point1YCoordinate.compareTo(zero);
        int y2NegOrPos = point2YCoordinate.compareTo(zero);
        if (y1NegOrPos <= 0 && y2NegOrPos >= 0
                || y1NegOrPos >= 0 && y2NegOrPos <= 0) {
            BigDecimal x2Coordinate = point2.xCoordinate;
            yIntercept = (new Point(
                    x2Coordinate.subtract(
                            x2Coordinate.subtract(point1.xCoordinate).multiply(point2YCoordinate.divide(
                                    point2YCoordinate.subtract(point1YCoordinate), calcPrecisionMathContext))), 0));
        }
        return yIntercept;
    }
}
