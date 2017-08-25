package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Owner on 7/10/2017.
 */
public abstract class Shape {
    Point[] points;
    Line[] lines;
    public BigDecimal area;
    public BigDecimal perimeter;
    public String name = "";

    public abstract String getShapeType();

    public abstract void calcShapeArea();

    public void calcPerimeter()
    {
        perimeter = new BigDecimal(0);
        for (Line line : lines) {
            perimeter = perimeter.add(line.distance);
        }
    }

    public Shape(Point[] points) {
        this.points = points;
    }

    public void initializeShape(Point[] points) {
        if (points.length > 0) {
            if (points.length >= this.points.length) {
                this.points = (Arrays.asList(points).subList(0, this.points.length)).toArray(new Point[this.points.length]);
            } else {
                for (int i = 0; i < this.points.length; i++) {
                    this.points[i] = new Point(0, 0);
                }
                for (int i = points.length; i > 0; i++) {
                    this.points[i] = points[i];
                }
            }
        } else {
            for (int i = 0; i < this.points.length; i++) {
                this.points[i] = new Point(0, 0);
            }
        }
        this.lines = LinePredicates.computeAllLinesFromPoints(points);
        calcPerimeter();
    }

    public Point[] getYIntercepts() {
        Collection<Point> xIntercepts = new ArrayList<Point>();
        for (Point point : points) {
            if (point.xCoordinate.equals(new BigDecimal(0))) {
                xIntercepts.add(point);
            }
        }
        for (Line line : lines) {
            line.getXIntercept();
        }
    return xIntercepts.toArray(new Point[xIntercepts.size()]);
    }
    public Point[] getXIntercepts() {
        Collection<Point> yIntercepts = new ArrayList<Point>();
        for (Point point : points) {
            if (point.yCoordinate.equals(new BigDecimal(0))) {
                yIntercepts.add(point);
            }
        }
        for (Line line : lines) {
            line.getYIntercept();
        }
        return yIntercepts.toArray(new Point[yIntercepts.size()]);
    }
    public Point[][] getIntercepts()
    {
        Point[] xIntercepts = getXIntercepts();
        Point[] yIntercepts = getYIntercepts();
        return new Point[][]{xIntercepts, yIntercepts};
    }

    @Override
    public String toString() {
        String shapeString = "";
        for (Line line : lines) {
            shapeString+=line.toString()+"\n";
        }
        shapeString+= "Area: " + area.toString();
        return shapeString;
    }
}

