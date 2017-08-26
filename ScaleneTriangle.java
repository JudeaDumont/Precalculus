package com.company;

/**
 * Created by Owner on 7/12/2017.
 */
public class ScaleneTriangle extends Triangle {

    protected ScaleneTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Scalene;
    }

    @Override
    public String getShapeType() {
        return "ScaleneTriangle";
    }

    @Override
    public void calcShapeArea() {

    }

    @Override
    public Point[] centerShapePointsUpright() {
        return null;
    }

    @Override
    public boolean pointInShape(Point point) {
        return false;
    }
}
