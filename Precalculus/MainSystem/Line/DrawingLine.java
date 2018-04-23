package MainSystem.Line;

import MainSystem.Point.DrawingPoint;

public class DrawingLine extends Line {
    public DrawingPoint drawingPoint1;
    public DrawingPoint drawingPoint2;

    public DrawingLine(Line line) {
        super(line);
        this.drawingPoint1 = new DrawingPoint(line.point1);
        this.drawingPoint2 = new DrawingPoint(line.point2);
    }

    @Override
    public boolean equals(Object obj) {
        return (((DrawingLine) obj).drawingPoint1.equals(this.drawingPoint1)
                && ((DrawingLine) obj).drawingPoint2.equals(this.drawingPoint2));
    }

    @Override
    public String toString() {
        return drawingPoint1.toString() + "to\n" + drawingPoint2.toString() + "Length:" + distance + "\n" + "Slope:" + slope.toString() + "\n";
    }
}
