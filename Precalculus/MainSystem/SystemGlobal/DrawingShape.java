package MainSystem.SystemGlobal;

import MainSystem.Line.DrawingLine;
import MainSystem.Line.Line;
import MainSystem.Point.DrawingPoint;
import MainSystem.Point.Point;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/10/2017.
 */
public class DrawingShape extends Shape {
    public DrawingPoint[] drawingPoints;
    public DrawingLine[] drawingLines;
    private Shape shape;

    public DrawingShape(Shape shape) {
        super(shape, false);
        this.shape = shape;
        this.drawingPoints = new DrawingPoint[shape.points.length];
        for (int i = 0; i < shape.points.length; i++) {
            this.drawingPoints[i] = new DrawingPoint(shape.points[i]);
        }

        this.drawingLines = new DrawingLine[shape.lines.length];
        for (int i = 0; i < shape.lines.length; i++) {
            this.drawingLines[i] = new DrawingLine(shape.lines[i]);
        }

        this.area = new BigDecimal(shape.area.doubleValue());
        this.perimeter = new BigDecimal(shape.perimeter.doubleValue());
        this.name = shape.name;
    }

    @Override
    public String getShapeType() {
        return this.shape.getShapeType();
    }

    @Override
    public void calcShapeArea() {
        this.shape.calcShapeArea();
    }

    @Override
    public Point[] centerShapePointsUpright() {
        return this.shape.centerShapePointsUpright();
    }

    @Override
    public boolean pointInShape(Point point) {
        return this.pointInShape(point);
    }

    @Override
    public String toString() {
        String shapeString = "";
        shapeString += "\n\n" +
                "" + getShapeType() + "\n";
        for (Line line : drawingLines) {
            shapeString += line.toString() + "\n";
        }
        shapeString += "Area: " + area.toString();
        return shapeString;
    }
}

