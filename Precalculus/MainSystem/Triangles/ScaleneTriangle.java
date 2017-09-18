package MainSystem.Triangles;

import MainSystem.Points.Point;

import java.math.BigDecimal;

public class ScaleneTriangle extends Triangle {

    ScaleneTriangle(Point[] points) {
        super(points);
        type = TriangleShapeType.Scalene;
    }

    @Override
    public String getShapeType() {
        return "ScaleneTriangle";
    }

    @Override
    public void calcShapeArea() {
        this.area = new BigDecimal(0);
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
