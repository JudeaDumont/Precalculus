package com.company;

/**
 * Created by Owner on 7/12/2017.
 */
public class RectangleConcrete extends Rectangle {
    public RectangleConcrete(Point[] points) {
        super(points);
    }

    @Override
    public String getShapeType() {
        return "Rectangle";
    }

    @Override
    public void calcShapeArea() {
        area = lines[0].distance.multiply(lines[1].distance);
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
