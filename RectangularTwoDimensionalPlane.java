package com.company;

import java.math.BigDecimal;

public class RectangularTwoDimensionalPlane {
    public TwoDimensionalPlane twoDimensionalPlane = new TwoDimensionalPlane
            (
                    new Line(new Point(0, 0), new Point(0, 0), 1),
                    new Line(new Point(0, 0), new Point(0, 0), 1),
                    0, 0
            );

    //This method is specific to the cartesian coordinate system in the the plane is distributed across the x an y axis
    //For the actual2dimensional plane, there would be min and max varaibles for each axis
    private void modifyAxisMaxLengths(BigDecimal xAxisStartingMaxValue, BigDecimal yAxisStartingMaxValue) {
        twoDimensionalPlane.xAxis.point1.xCoordinate = new BigDecimal(xAxisStartingMaxValue.negate().toString());
        twoDimensionalPlane.xAxis.point1.yCoordinate = new BigDecimal("0");

        twoDimensionalPlane.xAxis.point2.xCoordinate = new BigDecimal(xAxisStartingMaxValue.toString());
        twoDimensionalPlane.xAxis.point2.yCoordinate = new BigDecimal("0");

        twoDimensionalPlane.yAxis.point1.xCoordinate = new BigDecimal("0");
        twoDimensionalPlane.yAxis.point1.yCoordinate = new BigDecimal(yAxisStartingMaxValue.negate().toString());

        twoDimensionalPlane.yAxis.point2.xCoordinate = new BigDecimal("0");
        twoDimensionalPlane.yAxis.point2.yCoordinate = new BigDecimal(yAxisStartingMaxValue.toString());
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
