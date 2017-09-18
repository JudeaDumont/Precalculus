package MainSystem.Lines;
import MainSystem.Points.Point;
import java.math.BigDecimal;
import static MainSystem.Lines.LinePredicates.checkIfHeadingTowardsEachOther;

/**
 * Created by Owner on 9/15/2017.
 */
public class IntersectionInformation {
    public boolean futureIntersection = true;
    public BigDecimal line1HighestXPoint = null;
    public BigDecimal line2HighestXPoint = null;
    public BigDecimal line1HighestYPoint = null;
    public BigDecimal line2HighestYPoint = null;
    public BigDecimal line1LowestXPoint = null;
    public BigDecimal line2LowestXPoint = null;
    public BigDecimal line1LowestYPoint = null;
    public BigDecimal line2LowestYPoint = null;

    public IntersectionInformation(Point startingPoint1, Point velocity1, Point startingPoint2, Point velocity2) {
        //checking if they are heading towards each other is an issue because they're paths may still intersect
        boolean headingTowardsEachOther =
                checkIfHeadingTowardsEachOther(startingPoint1, velocity1, startingPoint2, velocity2);
        if (headingTowardsEachOther) {
            if (velocity2.xCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line2HighestXPoint = startingPoint2.xCoordinate;
            }
            if (velocity1.xCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line1HighestXPoint = startingPoint1.xCoordinate;
            }
            if (velocity1.yCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line1HighestYPoint = startingPoint1.yCoordinate;
            }
            if (velocity2.yCoordinate.compareTo(new BigDecimal(0)) <= 0) {
                line2HighestYPoint = startingPoint2.yCoordinate;
            }
            if (velocity2.xCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line2LowestXPoint = startingPoint2.xCoordinate;
            }
            if (velocity1.xCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line1LowestXPoint = startingPoint1.xCoordinate;
            }
            if (velocity1.yCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line1LowestYPoint = startingPoint1.yCoordinate;
            }
            if (velocity2.yCoordinate.compareTo(new BigDecimal(0)) >= 0) {
                line2LowestYPoint = startingPoint2.yCoordinate;
            }
        }
        else {
            futureIntersection = false;
        }
        if(futureIntersection) {
            if (line1HighestXPoint != null && line2LowestXPoint != null) {
                if (line1HighestXPoint.compareTo(line2LowestXPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1LowestXPoint != null && line2HighestXPoint != null) {
                if (line2HighestXPoint.compareTo(line1LowestXPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1HighestYPoint != null && line2LowestYPoint != null) {
                if (line1HighestYPoint.compareTo(line2LowestYPoint) < 1) {
                    futureIntersection = false;
                }
            }
            if (line1LowestYPoint != null && line2HighestYPoint != null) {
                if (line2HighestYPoint.compareTo(line1LowestYPoint) < 1) {
                    futureIntersection = false;
                }
            }
        }
    }
}
