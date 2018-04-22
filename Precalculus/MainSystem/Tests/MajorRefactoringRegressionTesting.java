package MainSystem.Tests;

import MainSystem.CartesianCoordinateSystem.FourPropositions;
import MainSystem.Circle.Circle;
import MainSystem.Circle.CirclePredicates;
import MainSystem.Line.Line;
import MainSystem.Line.LinePredicates;
import MainSystem.Point.Point;
import MainSystem.Point.PointPredicates;
import MainSystem.Rectangles.Rectangle;
import MainSystem.Triangle.Triangle;
import MainSystem.Triangle.TrianglePredicates;

import java.math.BigDecimal;

import static MainSystem.SystemGlobal.OutputPredicates.print;
import static MainSystem.SystemGlobal.OutputPredicates.printBar;

/**
 * Created by Owner on 9/15/2017.
 */
@SuppressWarnings("WeakerAccess")
public class MajorRefactoringRegressionTesting {
    public static void main(String[] args)
    {
        Circle circle = new Circle(new Point(0,0),4, 2 );
        print(circle.pointInShape(new Point(1,1)));
        print(circle.pointInShape(new Point(2,2)));
        print(circle.pointInShape(new Point(3,3))); ///should be false

        print(CirclePredicates.deriveCircleFromLine(new Line(new Point(0,0),new Point(1,1))));
        printBar();

        print(circle);
        print(CirclePredicates.getTangent(4, circle)); //I'm not to sure if this is correct

        printBar("derive square");
        print(CirclePredicates.deriveContainingSquareFromCircle(circle));

        printBar("bottom rectangle");
        print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.BOTTOM));

        printBar("top rectangle");
        print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.TOP));

        printBar("left rectangle");
        print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.LEFT));

        printBar("right rectangle");
        print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.RIGHT));

        printBar();
        Circle circle2 = new Circle(new Point(0,10),4, 2 );
        print(circle2);

        printBar();
        print(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(1,1), new BigDecimal(2), new BigDecimal(2)));

        printBar();
        print(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(1,1), new BigDecimal(2), new BigDecimal(2)));

        printBar();
        print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(0,0),new Point(0,1),new Point(0,-1),1));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,0),new Point(1,1),new Point(10,0),new Point(-1, 1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,900),new Point(1,-450),new Point(800,0),new Point(-100, 100000))); //should fail

        printBar();
        print(LinePredicates.getIntersection(new Point(0,900),new Point(0,-450),new Point(800,10),new Point(-100, 0)));
        print(LinePredicates.getTimeOffsetOfCollision(new Point(0,900),new Point(0,-400),new Point(800,0),new Point(-250, 0)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,10),new Point(1,-1),new Point(10,6),new Point(-1, 0)));

        printBar();
        print(LinePredicates.getIntersection(new Point(10,6),new Point(-1,0),new Point(0,10),new Point(1, -1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(100,10),new Point(-10,10),new Point(10,140),new Point(0, -10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(10,140),new Point(0, -10), new Point(100,10),new Point(-10,10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,0),new Point(10, 0), new Point(10,10),new Point(0,-10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,0),new Point(10, 10), new Point(0,10),new Point(1,-1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,10),new Point(1,-1), new Point(0,0),new Point(10, 10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(10,10),new Point(-1,-1), new Point(0,0),new Point(1, 10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,0),new Point(1, 10), new Point(10,10),new Point(-1,-1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(10,10),new Point(-1, -1), new Point(0,10),new Point(2,-10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(10,10),new Point(-1, -10), new Point(0,10),new Point(1,-10)));

        printBar();
        print(LinePredicates.getIntersection(new Point(5,0),new Point(0,1), new Point(1,0),new Point(.9, 1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(1,0),new Point(.9, 1), new Point(5,0),new Point(0,1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(5,3),new Point(-1,0), new Point(0,1),new Point(1, 1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,1),new Point(1, 1), new Point(5,3),new Point(-1,0)));

        printBar();
        print(LinePredicates.getIntersection(new Point(5,-3),new Point(-1,0), new Point(0,1),new Point(-1, -1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,1),new Point(-1, -1), new Point(5,-3),new Point(-1,0)));

        printBar();
        print(LinePredicates.getIntersection(new Point(0,0),new Point(-.9, -1), new Point(-5,0),new Point(0,-1)));

        printBar();
        print(LinePredicates.getIntersection(new Point(1,0),new Point(-.9, -1), new Point(-5,0),new Point(0,-1)));

        printBar();
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)));

        printBar();
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)).pointInShape(new Point(1, 1)));

        printBar();
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,4)}));


        printBar();
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,4)}));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,4)}).pointInShape(new Point(2,2)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,4)}).pointInShape(new Point(4,0)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,4)}).pointInShape(new Point(2,4)));

        printBar();
        print(Triangle.createInstance(new Point[]{new Point(-4,0), new Point(0,0), new Point(-2,-4)}));
        print(Triangle.createInstance(new Point[]{new Point(-4,0), new Point(0,0), new Point(-2,-4)}).pointInShape(new Point(-2,-2)));
        print(Triangle.createInstance(new Point[]{new Point(-4,0), new Point(0,0), new Point(-2,-4)}).pointInShape(new Point(-4,0)));
        print(Triangle.createInstance(new Point[]{new Point(-4,0), new Point(0,0), new Point(-2,-4)}).pointInShape(new Point(-2,-4)));

        printBar();
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(2,-2)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(4,0)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(2,-4)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(4,1))); //Should be False

        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}).pointInShape(new Point(0,0)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}).pointInShape(new Point(1,0)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}).pointInShape(new Point(0,1)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}).pointInShape(new Point(1,1)));

        print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(1, 1),new Point(10,0),new Point(-1, 1), 2 ));
        print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(1, 1),new Point(10,0),new Point(-1, 1), 3 ));
        print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(1, 1),new Point(10,0),new Point(-1, 1), 4 ));
        print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(1, 1),new Point(10,0),new Point(-1, 1), 5 ));

        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)));
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)).pointInShape(new Point(0,0)));
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)).pointInShape(new Point(2,0)));
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)).pointInShape(new Point(1,1.73)));


        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(2,-2)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(4,0)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(2,-4)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).pointInShape(new Point(4,1))); //Should be False

        printBar();
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}).getMeridians());

        printBar();
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(2,-4)}));
        print(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistance(new BigDecimal(2)));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(1,0), new Point(0,1)}));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(2,0), new Point(3,2)}));
        print(Triangle.createInstance(new Point[]{new Point(0,0), new Point(2,0), new Point(0,24)}));

        print(Rectangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(4,4), new Point(0, 4)}));
        print(PointPredicates.getXAxisReflected(new Point[]{new Point(0,0), new Point(4,0), new Point(4,4), new Point(0, 4)}));
        print((Rectangle.createInstance(new Point[]{new Point(0,0), new Point(4,0), new Point(4,4), new Point(0, 4)}).rotate(new Point(0,0), 90 )));
    }
}
