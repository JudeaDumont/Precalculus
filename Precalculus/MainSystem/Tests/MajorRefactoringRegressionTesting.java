package MainSystem.Tests;

import MainSystem.Circle.Circle;
import MainSystem.Circle.CirclePredicates;
import MainSystem.CoordinateSystem.FourPropositions;
import MainSystem.GlobalSystem.OutputPredicates;
import MainSystem.Lines.Line;
import MainSystem.Lines.LinePredicates;
import MainSystem.Points.Point;

import java.math.BigDecimal;

/**
 * Created by Owner on 9/15/2017.
 */
public class MajorRefactoringRegressionTesting {
    public static void main(String[] args)
    {
        Circle circle = new Circle(new Point(0,0),4, 2 );
        OutputPredicates.print(circle.pointInShape(new Point(1,1)));
        OutputPredicates.print(circle.pointInShape(new Point(2,2)));
        OutputPredicates.print(circle.pointInShape(new Point(3,3))); ///should be false

        OutputPredicates.print(CirclePredicates.deriveCircleFromLine(new Line(new Point(0,0),new Point(1,1))));
        OutputPredicates.printBar();

        OutputPredicates.print(circle);
        OutputPredicates.print(CirclePredicates.getTangent(4, circle)); //I'm not to sure if this is correct

        OutputPredicates.printBar("derive square");
        OutputPredicates.print(CirclePredicates.deriveContainingSquareFromCircle(circle));

        OutputPredicates.printBar("bottom rectangle");
        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.BOTTOM));

        OutputPredicates.printBar("top rectangle");
        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.TOP));

        OutputPredicates.printBar("left rectangle");
        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.LEFT));

        OutputPredicates.printBar("right rectangle");
        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(circle, FourPropositions.RIGHT));

        OutputPredicates.printBar();
        Circle circle2 = new Circle(new Point(0,10),4, 2 );
        OutputPredicates.print(circle2);

        OutputPredicates.printBar();
        OutputPredicates.print(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(1,1), new BigDecimal(2), new BigDecimal(2)));

        OutputPredicates.printBar();
        OutputPredicates.print(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(1,1), new BigDecimal(2), new BigDecimal(2)));

        OutputPredicates.printBar();
        OutputPredicates.print(LinePredicates.calculateDistanceBetweenTwoPointsAfterTime(new Point(0,0),new Point(0,0),new Point(0,1),new Point(0,-1),1));

        OutputPredicates.printBar();

        //OutputPredicates.print(LinePredicates.getIntersection(new Point(0,0),new Point(1,1),new Point(10,0),new Point(-1, 1))); //BUG

        OutputPredicates.printBar();
        OutputPredicates.print(LinePredicates.getIntersection(new Point(0,900),new Point(1,-450),new Point(800,0),new Point(-100, 100000))); //BUG
        //GET INTERSECTION DOES NOT WORK

        OutputPredicates.printBar();
        OutputPredicates.print(LinePredicates.getIntersection(new Point(0,900),new Point(0,-450),new Point(800,10),new Point(-100, 0))); //asmptotes seem to work

    }
}
