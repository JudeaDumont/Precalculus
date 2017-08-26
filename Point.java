package com.company;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class Point {
    public BigDecimal xCoordinate = new BigDecimal("0");
    public BigDecimal yCoordinate = new BigDecimal("0");
    public String pointName = "";

    private void intializeProperties(BigDecimal xCoordinate, BigDecimal yCoordinate, String name) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.pointName = name;
    }

    public Point(BigDecimal xCoordinate, BigDecimal yCoordinate) {
        intializeProperties(xCoordinate, yCoordinate, "");
    }

    public Point(double xCoordinate, double yCoordinate) {
        intializeProperties(new BigDecimal(xCoordinate), new BigDecimal(yCoordinate), "");
    }

    public Point(double xCoordinate, double yCoordinate, String pointName) {
        intializeProperties(new BigDecimal(xCoordinate), new BigDecimal(yCoordinate), pointName);
    }

    public Point(BigDecimal xCoordinate, double yCoordinate) {
        intializeProperties(xCoordinate, new BigDecimal(yCoordinate), "");
    }

    public Point(double xCoordinate, BigDecimal yCoordinate) {
        intializeProperties(new BigDecimal(xCoordinate), yCoordinate, "");
    }

    public Point(Point point) {
        intializeProperties(new BigDecimal(point.xCoordinate.toString()), new BigDecimal(point.yCoordinate.toString()), new String(point.pointName));
    }

    @Override
    public boolean equals(Object obj) {
        return (((Point) obj).xCoordinate.equals(this.xCoordinate)
                && ((Point) obj).yCoordinate.equals(this.yCoordinate));
    }

    @Override
    public String toString() {
        return (this.pointName.equals("") ? "Unnamed" : this.pointName) + " (" + this.xCoordinate.doubleValue() + "," + this.yCoordinate.toString() + ")\n";
    }

    public Point rotate(Point centerOfRotation, double rotationInDegrees) {
        Point point = new Point(xCoordinate.subtract(centerOfRotation.xCoordinate), yCoordinate.subtract(centerOfRotation.yCoordinate));
        Point transformedOrigin = new Point(0, 0);
        double radius = new Line(point, transformedOrigin).distance.doubleValue();
        double angle = 450 - Math.toDegrees(Math.atan2(point.xCoordinate.doubleValue(), point.yCoordinate.doubleValue()));
        rotationInDegrees = rotationInDegrees % 360;
        angle = (angle + rotationInDegrees) % 360;
        BigDecimal xCoordinate = new BigDecimal(Double.toString(Math.cos(Math.toRadians(angle)) * radius));
        BigDecimal yCoordinate = new BigDecimal(Double.toString(Math.sin(Math.toRadians(angle)) * radius));
        return new Point(xCoordinate.add(centerOfRotation.xCoordinate), yCoordinate.add(centerOfRotation.yCoordinate));
    }



    public void display() {
        System.out.println(this.toString());
    }
}
