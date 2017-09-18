package MainSystem.CoordinateSystem;

import MainSystem.Lines.Line;
import MainSystem.Points.Point;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
public class RectangularTwoDimensionalPlane {
    @SuppressWarnings({"WeakerAccess", "CanBeFinal"})
    public TwoDimensionalPlane twoDimensionalPlane = new TwoDimensionalPlane
            (
                    new Line(new Point(0, 0), new Point(0, 0), 1),
                    new Line(new Point(0, 0), new Point(0, 0), 1),
                    0, 0
            );

    //This method is specific to the cartesian coordinate system in the the plane is distributed across the x an y axis
    //For the actual2dimensional plane, there would be min and max varaibles for each axis
    private void modifyAxisMaxLengths(BigDecimal xAxisStartingMaxValue, BigDecimal yAxisStartingMaxValue) {
        Line twoDimensionalXAxis = twoDimensionalPlane.xAxis;
        Point xAxisPoint1 = twoDimensionalXAxis.point1;
        xAxisPoint1.xCoordinate = new BigDecimal(xAxisStartingMaxValue.negate().toString());
        xAxisPoint1.yCoordinate = new BigDecimal("0");

        Point xAxisPoint2 = twoDimensionalXAxis.point2;
        xAxisPoint2.xCoordinate = new BigDecimal(xAxisStartingMaxValue.toString());
        xAxisPoint2.yCoordinate = new BigDecimal("0");

        Line twoDimensionalYAxis = twoDimensionalPlane.yAxis;
        Point yAxisPoint1 = twoDimensionalYAxis.point1;
        yAxisPoint1.xCoordinate = new BigDecimal("0");
        yAxisPoint1.yCoordinate = new BigDecimal(yAxisStartingMaxValue.negate().toString());

        Point yAxisPoint2 = twoDimensionalYAxis.point2;
        yAxisPoint2.xCoordinate = new BigDecimal("0");
        yAxisPoint2.yCoordinate = new BigDecimal(yAxisStartingMaxValue.toString());
    }

    public RectangularTwoDimensionalPlane(BigDecimal xAxisStartingMaxValue, BigDecimal yAxisStartingMaxValue) {
        modifyAxisMaxLengths(xAxisStartingMaxValue, yAxisStartingMaxValue);
    }

    public RectangularTwoDimensionalPlane(BigDecimal evenAxisMaxStartingValue) {
        modifyAxisMaxLengths(evenAxisMaxStartingValue, evenAxisMaxStartingValue);
    }

    public RectangularTwoDimensionalPlane(double xAxisStartingMaxValue, double yAxisStartingMaxValue) {
        modifyAxisMaxLengths(new BigDecimal(xAxisStartingMaxValue), new BigDecimal(yAxisStartingMaxValue));
    }

    public RectangularTwoDimensionalPlane(double evenAxisMaxStartingValue) {
        modifyAxisMaxLengths(new BigDecimal(evenAxisMaxStartingValue), new BigDecimal(evenAxisMaxStartingValue));
    }
}
