package MainSystem.CoordinateSystem;

import MainSystem.Lines.Line;
import MainSystem.Points.Point;

import java.math.BigDecimal;

//XYPlane
public class TwoDimensionalPlane {
    public Line xAxis = new Line(new Point(0, 0), new Point(0, 0), 1);
    public Line yAxis = new Line(new Point(0, 0), new Point(0, 0), 1);

    //The point where the two lines intersect
    //origin is relative to a certain movement across an axis
    public BigDecimal originXAxis = new BigDecimal("0");
    public BigDecimal originyAxis = new BigDecimal("0");

    private void initializeProperties(Line xAxis, Line yAxis, BigDecimal originXAxis, BigDecimal originYAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.originXAxis = originXAxis;
        this.originyAxis = originYAxis;
    }

    //Usually points to the right of zero are associated with positive xCoordinate values same with vertical distance from 0 for yCoordinate
    //This is not the case for every two dimensional plane, the origin for an xCoordinate line or yCoordinate line could be -2 for instance
    public Quadrant getQuadrantFromPoint(Point point) {
        Quadrant quadrant = null;
        //calculate the quadrant via the origin as the ending length starting at the beginning of the axis line
        return quadrant;
    }

    public TwoDimensionalPlane(Line xAxis, Line yAxis, BigDecimal originXAxis, BigDecimal originYAxis) {
        initializeProperties(xAxis, yAxis, originXAxis, originYAxis);
    }

    public TwoDimensionalPlane(Line xAxis, Line yAxis, double originXAxis, double originYAxis) {
        initializeProperties(xAxis, yAxis, new BigDecimal(originXAxis), new BigDecimal(originYAxis));
    }
}
