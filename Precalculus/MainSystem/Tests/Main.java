package MainSystem.Tests;

import MainSystem.Circle.Circle;
import MainSystem.Circle.CirclePredicates;
import MainSystem.CoordinateSystem.CartesianCoordinateSystem;
import MainSystem.CoordinateSystem.CartesianCoordinateSystemPredicates;
import MainSystem.CoordinateSystem.FourPropositions;
import MainSystem.Functions.FunctionPredicates;
import MainSystem.GlobalSystem.OutputPredicates;
import MainSystem.Lines.Line;
import MainSystem.Lines.LinePredicates;
import MainSystem.Points.Point;
import MainSystem.Points.PointPredicates;
import MainSystem.Rectangles.SquarePredicates;
import MainSystem.Trajectorys.Projectile;
import MainSystem.Triangles.Triangle;
import MainSystem.Triangles.TrianglePredicates;
import MainSystem.Triangles.TriangleShapeType;
import MainSystem.Rectangles.Rectangle;

import java.math.BigDecimal;
import java.util.function.Function;

import static MainSystem.GlobalSystem.OutputPredicates.*;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static void main(String[] args) {
        Line line = new Line(new Point(new BigDecimal("-3"), new BigDecimal("5")), new Point(new BigDecimal("3"), new BigDecimal("2")), new BigDecimal("1"));
        System.out.println(line.distance);
        System.out.println(new Point(new BigDecimal("1"), new BigDecimal("0")).equals(new Point(new BigDecimal("0"), new BigDecimal("0"))));

        LinePredicates.calculateAndDisplayLines(new Point[]{new Point(-2, 1, "A"), new Point(2, 3, "B"), new Point(3, 1, "C")});
        LinePredicates.calculateAndDisplayLines(new Point[]{
                new Point(1, 1, "1"), new Point(2, 1, "2"), new Point(3, 3, "3"), new Point(3, 4, "4"),
                new Point(5, 1, "5"), new Point(3, 6, "6"), new Point(7, 1, "7"), new Point(8, 8, "8"),
                new Point(3, 9, "9"), new Point(9, 1, "10")});
        Triangle triangle = Triangle.createInstance(new Point[]{new Point(-2, 1, "A"), new Point(2, 3, "B"), new Point(3, 1, "C")});
        System.out.println(triangle.type == TriangleShapeType.Right ? "Right" : "Nope");
        System.out.println(triangle.area);
        System.out.println(triangle.lines[0].distance);
        Triangle[] triangles = TrianglePredicates.makeTrianglesFromLine(
                new Line(new Point(0, 1), new Point(1, 0), 1)
        );

        for (Triangle triangle1 : triangles) {
            System.out.println(triangle1.toString());
        }
        System.out.println(new Line(new Point(-5, 5), new Point(3, 1), 1).getMidPoint().toString());

        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(-2, -1), new BigDecimal(5), new BigDecimal(2))));
        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(1, 2), new BigDecimal(13), new BigDecimal(-3))));

        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(4, -3), new BigDecimal(5), new BigDecimal(0))));
        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(4, 4), new BigDecimal(5), new BigDecimal(0))));
        Line[] meridians = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(4, 4)}).getMeridians();
        Triangle triangle1 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(4, 4)});
        System.out.println(triangle1.type == TriangleShapeType.Equalateral);
        LinePredicates.displayLineLengths(meridians);
        System.out.println(new Line(new Point(0, 0), new Point(4, 0), 1).distance);
        LinePredicates.displayLineLengths(triangle1.lines);
        Triangle triangle2 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(12, 0), new Point(6, 10.1)});
        LinePredicates.displayLineLengths(triangle2.lines);
        System.out.println(triangle.getShapeType());
        System.out.println(triangle.area.toString());
        System.out.println(triangle2.getShapeType());
        System.out.println(triangle2.area.toString());
        Line[] meridians1 = TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(1)).getMeridians();
        LinePredicates.displayLineLengths(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(1)).lines);

        for (Line line1 : meridians1) {
            System.out.println(line1.getMidPoint().toString());
        }
        System.out.println("Meridians of an eqalateral triangle intersect at their midpoints: " + (
                meridians1[0].getMidPoint().equals(meridians1[1].getMidPoint()) && meridians1[0].getMidPoint().equals(meridians1[2].getMidPoint())));
        System.out.println("Meridians of a square intersect at their midpoints: ");
        LinePredicates.displayLineLengths(new Line[]{new Line(new Point(0, 0), new Point(0, 4), 1)});
        System.out.println("A: " + (Triangle.createInstance(new Point[]{new Point(2, 1), new Point(-4, 1), new Point(-4, -3)}).getShapeType()) +
                "\nB: " + (Triangle.createInstance(new Point[]{new Point(-1, 4), new Point(6, 2), new Point(4, -5)}).getShapeType()) +
                "\nC: " + (Triangle.createInstance(new Point[]{new Point(-2, -1), new Point(0, 7), new Point(3, 2)}).getShapeType()) +
                "\nD: " + (Triangle.createInstance(new Point[]{new Point(7, 2), new Point(-4, 0), new Point(4, 6)}).getShapeType()));
        System.out.println(PointPredicates.getPointString(PointPredicates.sortPoints(new Point[]{new Point(0, 1), new Point(0, 5), new Point(1, 5), new Point(1, 4), new Point(0, 0), new Point(0, 4), new Point(4, 4), new Point(2, 4), new Point(4, 0), new Point(1, 0), new Point(4, 4), new Point(3, 4), new Point(3, 3), new Point(0, 0)})));
        System.out.println(PointPredicates.getPointString(PointPredicates.sortPoints(new Point[]{new Point(0, 0), new Point(90, 0), new Point(90, 90), new Point(0, 90)})));
        LinePredicates.displayLineLengths(Rectangle.createInstance(new Point[]{new Point(0, 0), new Point(90, 0), new Point(90, 90), new Point(0, 90)}).getDiagnalLines());
        LinePredicates.displayLineLengths(Rectangle.createInstance(new Point[]{new Point(0, 0), new Point(60, 0), new Point(60, 60), new Point(0, 60)}).getDiagnalLines());
        Line line1 = new Line(new Point(310, 15), new Point(90, 90), 0);
        System.out.println(line.distance);
        System.out.println(new Line(new Point(300, 300), new Point(0, 90), 0));
        BigDecimal bigDecimal = LinePredicates.calculateDistanceBetweenTwoPointsAfterTime
                (new Point(0, 0), new Point(30, 0), new Point(0, 0), new Point(0, -40), 2);
        System.out.println(bigDecimal.toString());
        BigDecimal bigDecimal1 = LinePredicates.calculateDistanceBetweenTwoPointsAfterTime
                (new Point(0, 0), new Point(0, 0), new Point(0, 100), new Point(79200, 0), 1);
        System.out.printf("%f\n", 1d / 3600d);
        System.out.println(bigDecimal1.toString());
        System.out.println(new Line(new Point(-1, 8), new Point(2, 3), 1).toString());
        System.out.println(LinePredicates.doubleLine(new Line(new Point(-1, 8), new Point(2, 3), 1)).toString());

        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(1, 1)));
        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(-1, 1)));
        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(-1, -1)));
        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(1, -1)));
        System.out.println(new Line(new Point(1, 1), new Point(-3, 3)).getXIntercept().toString());
        System.out.println(new Line(new Point(-3, 3), new Point(1, 1)).getXIntercept().toString());

        System.out.println(new Line(new Point(1, 3), new Point(-3, 1)).getXIntercept().toString());
        System.out.println(new Line(new Point(-3, 1), new Point(1, 3)).getXIntercept().toString());

        System.out.println(new Line(new Point(-50, -2), new Point(1, 2)).getYIntercept().toString());
        System.out.println(PointPredicates.symmetricAboutXAxis(new Point[]{new Point(1, 1), new Point(1, -1)}));
        System.out.println(PointPredicates.symmetricAboutOrigin(new Point[]{new Point(1, -1), new Point(-1, 1)}));
        System.out.println(PointPredicates.symmetricAboutYAxis(new Point[]{new Point(1, 1), new Point(-1, 1)}));
        System.out.println(PointPredicates.symmetricAboutOrigin(new Point[]{new Point(1, 1), new Point(-1, -1)}));
        System.out.println(PointPredicates.getPointString(PointPredicates.getOriginReflected(new Point[]{new Point(1, 2)})));
        System.out.println(new Line(new Point(0, 0), new Point(1, 0)).getXIntercept());

        System.out.println(LinePredicates.createLineWithPointSlopeAndMagnitude(new Point(0, 39), new BigDecimal(0.60), new BigDecimal(110)).point2.yCoordinate);
        Line line11 = new Line(new Point(0, 0), new Point(1, 1));
        Line line2 = new Line(new Point(0, 0), new Point(-1, 1));
        System.out.println(LinePredicates.checkPerpendicularity(line1, line2));
        System.out.println(line1.slope);
        System.out.println(line2.slope);
        System.out.println(Math.sqrt(8));
        Circle circle = new Circle(new Point(0, 0), 1, 10);
        System.out.println(PointPredicates.getPointString(circle.points));
        System.out.println(0.70710678118 * 0.70710678118);
        Circle derivedCircle = CirclePredicates.deriveCircleFromLine(new Line(new Point(-10, -10), new Point(-10, -9)));
        System.out.println(PointPredicates.getPointString(derivedCircle.getPointsOfACircle(10)));
        Rectangle containingSquareDerivedFromCircle = SquarePredicates.deriveContainingDiamondFromCircle(derivedCircle);
        System.out.println(containingSquareDerivedFromCircle.getShapeType());
        System.out.println(derivedCircle.area.subtract(containingSquareDerivedFromCircle.area));
        Rectangle containedSquareDerivedFromCircle = SquarePredicates.deriveContainedSquareFromCircle(derivedCircle);
        System.out.println(containedSquareDerivedFromCircle.getShapeType());

        System.out.println(derivedCircle.area.toString());
        System.out.println(containedSquareDerivedFromCircle.area.toString());
        System.out.println(derivedCircle.area.subtract(containedSquareDerivedFromCircle.area));
        System.out.println(PointPredicates.getPointString(circle.getPointsOfACircle(3)));
        System.out.println(CirclePredicates.getTangent(0, circle).toString());
        System.out.println(CirclePredicates.getTangent(1, circle).toString());
        System.out.println(CirclePredicates.getTangent(2, circle).toString());
        System.out.println(CirclePredicates.getTangent(3, circle).toString());
        System.out.println(CirclePredicates.getTangent(8, circle).toString());
        System.out.println(CirclePredicates.getTangent(8, circle).getMidPoint().toString());
        System.out.printf("\n%f", CirclePredicates.getTangent(8, circle).getMidPoint().xCoordinate.doubleValue());
        System.out.printf("\n%f\n", CirclePredicates.getTangent(8, circle).getMidPoint().yCoordinate.doubleValue());
        System.out.println(CirclePredicates.getTangent(8, circle).slope.toString());
        System.out.println(new Line(circle.center, circle.points[8]).slope.toString());
        Line midLine = LinePredicates.deriveAverageLine(
                new Point[]
                        {
                                new Point(1, 1),
                                new Point(2, 1),
                                new Point(3, 1),
                                new Point(4, 1),
                                new Point(1, 3),
                                new Point(2, 3),
                                new Point(3, 3),
                                new Point(4, 3)
                        }
        );
        System.out.println(midLine.toString());
        System.out.println(PointPredicates.checkIfPointsRepresentFunction(circle.points));
        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]{new Point(0, 0), new Point(0, 1)}));
        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]{new Point(0, 0), new Point(0, 0)}));
        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]
                {
                        new Point(1, 1),
                        new Point(2, 1),
                        new Point(3, 1),
                        new Point(4, 1),
                        new Point(1, 3),
                        new Point(2, 3),
                        new Point(3, 3),
                        new Point(4, 3)
                }));

        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]
                {
                        new Point(1, 1),
                        new Point(2, 2),
                        new Point(3, 3),
                        new Point(4, 4),
                        new Point(1, 1),
                        new Point(2, 2),
                        new Point(3, 3),
                        new Point(4, 4)
                }));
        Line[] lines = PointPredicates.getLinesOfAllPointsSortedByXCoordinatesThenYCoordinates(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(0, 0)});
        LinePredicates.displayLineLengths(lines);
        System.out.println(PointPredicates.getMaximumPoint(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(0, 0), new Point(4,4), new Point(1,1)}));
        System.out.println(PointPredicates.getMinimumPoint(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(-1, -1), new Point(4,4), new Point(1,1)}));
        System.out.println(PointPredicates.getPointString(PointPredicates.sortPointsByX(
                new Point[]{new Point(0,5), new Point(2, 1), new Point(1, 4), new Point(0, 12), new Point(4,-5), new Point(1,10)}))
        );
        System.out.println(PointPredicates.getPointString(PointPredicates.getMaximas(
                new Point[]{new Point(0,5), new Point(2, 1), new Point(1, 4), new Point(0, 12), new Point(4,-5), new Point(1,10)}))
        );
        FunctionPredicates.cubeRootFunction(new int[]{0,1,2,3,4});
        Point intersection = LinePredicates.getIntersection(new Point(600, 0), new Point(-250, 10), new Point(0, 600), new Point(-10, -400));
        System.out.println(intersection.toString());

        Point intersection1 = LinePredicates.getIntersection(new Point(-600, 0), new Point(-250, 10), new Point(0, -600), new Point(-10, -400));
        print(intersection1);

        Point intersection2 = LinePredicates.getIntersection(new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400));
        System.out.println(intersection2.toString());

        Point intersection3 = LinePredicates.getIntersection(new Point(-600, 600), new Point(250, 125), new Point(0, 600), new Point(0, -400));
        print(intersection3);

        Point intersection4 = LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400));
        System.out.println(intersection4.toString());
        printBar();
        print(LinePredicates.getIntersection(new Point(-600, 600), new Point(250, 125), new Point(0, 600), new Point(0, -400)));
        print(LinePredicates.getIntersection(new Point(600, 0), new Point(-250, 10), new Point(0, 600), new Point(-10, -400)));
        print(LinePredicates.getIntersection(new Point(-600, 0), new Point(-250, 10), new Point(0, -600), new Point(-10, -400)));
        print(LinePredicates.getIntersection(new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400)));
        print(LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400)));

        LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400));
        System.out.println(LinePredicates.getTimeOffsetOfCollision
                (new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400)).toString());

        System.out.println(LinePredicates.getTimeOffsetOfCollision(new Point(400, 0), new Point(-250, 0), new Point(0, 600), new Point(0, -400)).toString());
        System.out.println(LinePredicates.getClosestDistanceOfTwoIntersectingTrejectorys(new Point(400, 0), new Point(-250, 0), new Point(0, 600), new Point(0, -400)).toString());
        print(CirclePredicates.deriveContainingSquareFromCircle(new Circle(new Point(0,0), 2, 1 )));
        printBar();
        print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.BOTTOM));
        printBar();
        print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.TOP));
        printBar();
        print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.LEFT));
        printBar();
        print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.RIGHT));
        print(new Circle(new Point(0,0), 2, 1 ));

        Triangle triangle11 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(3, 4)});
        System.out.println(triangle1.type == TriangleShapeType.Equalateral);
        System.out.println(triangle1.type == TriangleShapeType.Isoselece);
        print(triangle1);
        print(triangle1.lines);
        printBar();
        Line[] meridians11 = triangle1.getMeridians();
        print(meridians);

        printBar();
        Triangle triangle21 = TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(1));
        System.out.println(triangle2.type == TriangleShapeType.Equalateral);
        print(triangle2);
        print(triangle2.lines);

        printBar();
        Line[] meridians111 = triangle2.getMeridians();
        print(meridians1);
        printBar();

        Point point = new Point(0,2);
        Point rotate = point.rotate(new Point(0, 0), 45);
        print(rotate);

        Rectangle square = Rectangle.createInstance(new Point[]{new Point(1, 1), new Point(5, 1), new Point(1, 5), new Point(5, 5)});
        print(square);
        square.rotate(new Point(1,1), 45);
        printBar();
        print(square);
        System.out.println(square.pointInShape(new Point(1,2)));//PASS
        System.out.println(square.pointInShape(new Point(2,1)));//SHOULD FAIL
        System.out.println(square.pointInShape(new Point(2,2)));//PASS //THIS IS THE MOMENT OF TRUTH TEST
        System.out.println(square.pointInShape(new Point(1,6.67)));//SHOULD FAIL
        printBar();


        Circle circle1 = new Circle(new Point(1,1),1,2 );
        System.out.println(circle.pointInShape(new Point(1,1)));
        System.out.println(circle.pointInShape(new Point(0,0)));
        System.out.println(circle.pointInShape(new Point(0.3,0.3)));
        System.out.println(circle.pointInShape(new Point(1.7,1.7)));
          Triangle triangle111 = Triangle.createInstance(new Point[]{new Point(1,1), new Point(5,1), new Point(1,4)});
        System.out.println(triangle.pointInShape(new Point(2.5,2.8)));

        Triangle triangle211 = TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2));
        print(triangle2);
        System.out.println(triangle2.pointInShape(new Point(0.9,0.9)));
        System.out.println(triangle2.pointInShape(new Point(0,0)));
        System.out.println(triangle2.pointInShape(new Point(2,0)));
        System.out.println(triangle2.pointInShape(new Point(1,1.73)));
        System.out.println(triangle2.pointInShape(new Point(0,0)));
        Triangle triangleIso = Triangle.createInstance(new Point[]
                {
                        new Point(0,0),
                        new Point(-4,0),
                        new Point(-2, -6)
                });

        System.out.println(triangleIso.getShapeType());
        System.out.println(triangleIso.pointInShape(new Point(0,0)));
        System.out.println(triangleIso.pointInShape(new Point(-4,0)));
        System.out.println(triangleIso.pointInShape(new Point(-2,-6)));
        System.out.println(triangleIso.pointInShape(new Point(-2,-3)));
        System.out.println(triangleIso.pointInShape(new Point(-2,-3.1)));
        System.out.println(triangleIso.pointInShape(new Point(-2,-6.1))); //should be false

        printBar();
        Triangle triangleIso2 = Triangle.createInstance(new Point[]
                {
                        new Point(0,0),
                        new Point(4,0),
                        new Point(2, 6)
                });

        System.out.println(triangleIso2.getShapeType());
        System.out.println(triangleIso2.pointInShape(new Point(0,0)));
        System.out.println(triangleIso2.pointInShape(new Point(4,0)));
        System.out.println(triangleIso2.pointInShape(new Point(2,6)));
        System.out.println(triangleIso2.pointInShape(new Point(2,3)));
        System.out.println(triangleIso2.pointInShape(new Point(2,3.1)));
        System.out.println(triangleIso2.pointInShape(new Point(2,6.1))); //should be false

        printBar();
        Triangle triangleIso3 = Triangle.createInstance(new Point[]
                {
                        new Point(0,0),
                        new Point(-4,0),
                        new Point(-2, 6)
                });

        System.out.println(triangleIso3.getShapeType());
        System.out.println(triangleIso3.pointInShape(new Point(0,0)));
        System.out.println(triangleIso3.pointInShape(new Point(-4,0)));
        System.out.println(triangleIso3.pointInShape(new Point(-2,6)));
        System.out.println(triangleIso3.pointInShape(new Point(-2,3)));
        System.out.println(triangleIso3.pointInShape(new Point(-2,3.1)));
        System.out.println(triangleIso3.pointInShape(new Point(-2,6.1))); //should be false
        Triangle triangleRight = Triangle.createInstance(new Point[]
                {
                        new Point(0, 0),
                        new Point(0, 4),
                        new Point(3, 0)
                }
        );
        System.out.println(triangleRight.pointInShape(new Point(1.5, 2)));
        System.out.println(triangleRight.pointInShape(new Point(0, 0)));
        System.out.println(triangleRight.pointInShape(new Point(0, 4)));
        System.out.println(triangleRight.pointInShape(new Point(3, 0)));
        System.out.println(triangleRight.pointInShape(new Point(3, 0.1)));//should be false
        System.out.println(triangleRight.pointInShape(new Point(2.9, 0.1)));//should be true
        print(triangleRight);
        Point[] transformedPoints = PointPredicates.transformPoints(triangleRight.points, new Function<Point, Point>() {
            @Override
            public Point apply(Point point) {
                return new Point(point.xCoordinate.multiply(new BigDecimal(2)),point.yCoordinate.multiply(new BigDecimal(2)));
            }
        });
        print(transformedPoints);
        print(Triangle.createInstance(new Point[]{}));
        //This doesnt work because the sorting logic for points is broken in that it removes all of the points of some huebrus.
        print(Triangle.createInstance(new Point[]{new Point(1, 0)}));
        print(Triangle.createInstance(new Point[]{new Point(1, 0), new Point(0, 1)}));
        print(Rectangle.createInstance(new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)}));
        Projectile projectile = new Projectile(new Point[]{new Point(1,1)},new BigDecimal(1),new BigDecimal(0),new Point(1000, 10));
        print(projectile.trajectedObject.points);

        print(projectile.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        for(int i = 1; i < 100; i++)
        {
            print(projectile.getProjectileAtTime(new BigDecimal(i)).trajectedObject.points);
            System.out.println(i);
        }
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(14)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(15)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        print(projectile.getProjectileAtTime(new BigDecimal(15000)).trajectedObject.points);

        Projectile projectile2 = new Projectile(new Point[]{new Point(1,1)},new BigDecimal(1),new BigDecimal(0),new Point(0, 1000));
        print(projectile2.trajectedObject.points);

        print(projectile2.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        for(int i = 100; i < 2000; i+=100)
        {
            print(projectile2.getProjectileAtTime(new BigDecimal(i)).trajectedObject.points);
            System.out.println(i);
        }
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(14)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(15)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        print(projectile2.getProjectileAtTime(new BigDecimal(15000)).trajectedObject.points);

        Projectile leftwardProjectile = new Projectile(new Point[]{new Point(1,1)},new BigDecimal(1),new BigDecimal(0),new Point(-10, 10));
        print(leftwardProjectile.trajectedObject.points);

        print(leftwardProjectile.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        for(int i = 1; i < 20; i++)
        {
            print(leftwardProjectile.getProjectileAtTime(new BigDecimal(i)).trajectedObject.points);
            System.out.println(i);
        }
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(14)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(15)).trajectedObject.points);
//        OutputPredicates.print(projectile.getProjectileAtTime(new BigDecimal(16.7)).trajectedObject.points);
        print(leftwardProjectile.getProjectileAtTime(new BigDecimal(15000)).trajectedObject.points);

    Circle circle2 = new Circle(new Point(0,0),4, 2 );
        print(circle.pointInShape(new Point(1,1)));
        print(circle.pointInShape(new Point(2,2)));

        print(CirclePredicates.deriveCircleFromLine(new Line(new Point(0,0),new Point(1,1))));
        printBar();

        print(circle);
        print(CirclePredicates.getTangent(4, circle));

        printBar();
        print(CirclePredicates.deriveContainingSquareFromCircle(circle));



    }


}
