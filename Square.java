package com.company;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/12/2017.
 */
public class Square extends Rectangle {
    protected Square(Point[] points) {
        super(points);
    }

    @Override
    public String getShapeType() {
        return "Square";
    }

    @Override
    public void calcShapeArea() {
        area = lines[0].distance.multiply(lines[1].distance);
    }

    @Override
    public Point[] centerShapePointsUpright() {
        Point[] uprightPoints = new Point[points.length];
        Point difference = points[0];
        Point[] modPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            modPoints[i] = new Point(points[i].xCoordinate.subtract(difference.xCoordinate), points[i].yCoordinate.subtract(difference.yCoordinate));
        }

        double angle = 450 - Math.toDegrees(Math.atan2(modPoints[1].xCoordinate.doubleValue(), modPoints[1].yCoordinate.doubleValue()));
        double angleOfChange = 90 - (angle%90);

        for (int i = 0; i < modPoints.length; i++) {
            double radius = new Line(modPoints[0], modPoints[i]).distance.doubleValue();
            BigDecimal xCoordinate = new BigDecimal(Double.toString(Math.cos(Math.toRadians(angleOfChange)) * radius));
            BigDecimal yCoordinate = new BigDecimal(Double.toString(Math.sin(Math.toRadians(angleOfChange)) * radius));
            uprightPoints[i] = new Point(xCoordinate,yCoordinate);
        }
        return uprightPoints;
    }

    @Override
    public boolean pointInShape(Point point) {
        boolean inShape = false;

        Point[] uprightPoints = new Point[points.length];
        Point difference = points[0];
        Point[] modPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            modPoints[i] = new Point(points[i].xCoordinate.subtract(difference.xCoordinate), points[i].yCoordinate.subtract(difference.yCoordinate));
        }

        double angle = 450 - Math.toDegrees(Math.atan2(modPoints[1].xCoordinate.doubleValue(), modPoints[1].yCoordinate.doubleValue()));
        double angleOfChange = 90 - (angle%90);

        for (int i = 0; i < modPoints.length; i++) {
            uprightPoints[i] = modPoints[i].rotate(
                    new Point(0,0),
                    angleOfChange
                    );
        }

        BigDecimal maxX = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal maxY = new BigDecimal(-Double.MAX_VALUE);
        BigDecimal minX = new BigDecimal(Double.MAX_VALUE);
        BigDecimal minY = new BigDecimal(Double.MAX_VALUE);
        for (Point point1 : uprightPoints) {
            if(point1.xCoordinate.compareTo(maxX)>0)
            {
                maxX = new BigDecimal(point1.xCoordinate.toString());
            }
            if(point1.yCoordinate.compareTo(maxY)>0)
            {
                maxY = new BigDecimal(point1.yCoordinate.toString());
            }
            if(point1.xCoordinate.compareTo(minX)<0)
            {
                minX = new BigDecimal(point1.xCoordinate.toString());
            }
            if(point1.yCoordinate.compareTo(minY)<0)
            {
                minY = new BigDecimal(point1.yCoordinate.toString());
            }
        }
        point = new Point(point.xCoordinate.subtract(difference.xCoordinate), point.yCoordinate.subtract(difference.yCoordinate));
        point = point.rotate(new Point(0,0), angleOfChange);
        if(point.xCoordinate.compareTo(maxX)<=0 && point.xCoordinate.compareTo(minX)>=0 &&
                point.yCoordinate.compareTo(maxY)<=0 && point.yCoordinate.compareTo(minY)>=0)
        {
            inShape = true;
        }
        return inShape;
    }
}
