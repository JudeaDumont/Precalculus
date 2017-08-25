package com.company;

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


}
