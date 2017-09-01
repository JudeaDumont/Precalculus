package Precalculus;

public abstract class Triangle extends Shape {
    //Factory Method
    TriangleShapeType type;

    public static Triangle createInstance(Point[] threePoints) {
        TriangleShapeType type = TrianglePredicates.determineTriangleType(LinePredicates.computeAllLinesFromPoints(threePoints));
        Triangle specificTriangle = null;
        switch (type) {
            case Equalateral:
                specificTriangle = new EqualateralTriangle(threePoints);
                break;
            case Right:
                specificTriangle = new RightTriangle(threePoints);
                break;
            case Isoselece:
                specificTriangle = new IsoceleceTriangle(threePoints);
                break;
            case Scalene:
                specificTriangle = new ScaleneTriangle(threePoints);
                break;
            case RightIsoselece:
                specificTriangle = new RightIsoseleceTriangle(threePoints);

        }
        return specificTriangle;
    }

    protected Triangle(Point[] threePoints) {
        super(new Point[3]);
        initializeShape(threePoints);
        calcShapeArea();
        //Truncates any points that were additionally added
    }

    @Override
    public String toString() {
        return PointPredicates.getPointString(this.points);
    }

    public Line[] getMeridians() {
        Line[] meridians = new Line[3];

        for (int i = 0; i < lines.length; i++) {
            for (Point point : points) {
                if (!point.equals(lines[i].point1) && !point.equals(lines[i].point2)) {
                    meridians[i] = new Line(point, lines[i].getMidPoint(), 0);
                }
            }
        }
        return meridians;
    }
}
