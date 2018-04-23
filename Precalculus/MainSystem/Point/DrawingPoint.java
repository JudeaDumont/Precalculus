package MainSystem.Point;
//Drawing classes are used to store backingsystem actuals in terms of GUI representations,
// such that calculations involving translation of drawingPoints, drawingLines, and shapes are done only once.

public class DrawingPoint extends Point {
    public int drawingX = 0;
    public int drawingY = 0;
    public String pointName = "";

    public DrawingPoint(Point point) {
        super(point);
        intializeProperties(point.xCoordinate.intValue(), point.yCoordinate.intValue(), pointName);
    }

    public DrawingPoint(int drawingX, int drawingY) {
        super(new Point(drawingX, drawingY, ""));
        intializeProperties(drawingX, drawingY, "");
    }

    private void intializeProperties(int xCoordinate, int yCoordinate, String name) {
        this.drawingX = xCoordinate;
        this.drawingY = yCoordinate;
        this.pointName = name;
    }

    @Override
    public boolean equals(Object obj) {
        return (((DrawingPoint) obj).drawingX == (this.drawingX)
                && ((DrawingPoint) obj).drawingY == (this.drawingY));
    }

    @Override
    public String toString() {
        return (this.pointName.equals("") ? "Unnamed" : this.pointName) + " (" + this.drawingX + "," + this.drawingY + ")\n";
    }
}
