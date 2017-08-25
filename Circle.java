package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Owner on 7/17/2017.
 */
public class Circle extends Shape{
    Point center = new Point(0, 0);
    double radius = 0;

    @Override
    public void calcPerimeter() {
        perimeter = new BigDecimal(2).multiply(new BigDecimal(SystemGlobal.PI)).multiply(new BigDecimal(radius));
    }

    public Circle(Point center, double radius, long precision) {
        super(new Point[]{});
        //bypass point validation with intializeShape
        this.center = center;
        this.radius = radius;
        this.points= getPointsOfACircle(precision);
        calcShapeArea();
        calcPerimeter();
    }

    @Override
    public String toString() {
        return "\nRadius:" + radius + "\nPerimeter:" + perimeter + "\nCenter:" + center.toString() + "\nPoints:" + PointPredicates.getPointString(points);
    }


    public Point[] getPointsOfACircle(long precision) {
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collection<Double> divisionsOfRadius = new ArrayList<>();
        for (long i = 0; i < precision; i++) {
            Point point = (calculatePointOfCircle(radius / (i + 1)));

            if (!PointPredicates.doesDuplicateExist(circlePoints,new Point(point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints,new Point(new Point(-point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue())))) {
                circlePoints.add(new Point(-point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(-point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(-point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(point.xCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.yCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(-point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(-point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(-point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(-point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
            if (!PointPredicates.doesDuplicateExist(circlePoints, new Point(point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()))) {
                circlePoints.add(new Point(point.yCoordinate.doubleValue()+center.xCoordinate.doubleValue(), -point.xCoordinate.doubleValue()+center.yCoordinate.doubleValue()));
            }
        }
        return circlePoints.toArray(new Point[circlePoints.size()]);
    }

    public Point calculatePointOfCircle(double radiusPortion) {
        double remainingRadius = radius - radiusPortion;
        return new Point(
                radiusPortion!=0?Math.sqrt(radiusPortion):0
                ,
                remainingRadius!=0?Math.sqrt(remainingRadius):0
        );
    }

    @Override
    public String getShapeType() {
        return null;
    }

    @Override
    public void calcShapeArea() {
        area=new BigDecimal(Math.pow(radius, 2)*SystemGlobal.PI);
    }
}
