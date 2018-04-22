package MainSystem;

import MainSystem.Point.Point;

import java.math.BigDecimal;

public class DrawingArea {
    Point topLeft;
    BigDecimal height;
    BigDecimal width;
    ViewBox viewBox;

    public DrawingArea(Point topLeft,
                       BigDecimal height,
                       BigDecimal width) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
        viewBox = new ViewBox(topLeft, height, width);
    }

    public DrawingArea(Point topLeft,
                       BigDecimal height,
                       BigDecimal width,
                       Point viewBoxTopLeft,
                       BigDecimal viewBoxHeightView,
                       BigDecimal viewBoxwidth) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
        viewBox = new ViewBox(viewBoxTopLeft, viewBoxHeightView, viewBoxwidth);
    }

    public DrawingArea(Point topLeft, double height, double width) {
        this.topLeft = topLeft;
        this.height = new BigDecimal(height);
        this.width = new BigDecimal(width);
        viewBox = new ViewBox(topLeft, height, width);
    }

    public DrawingArea(Point topLeft,
                       double height,
                       double width,
                       Point viewBoxTopLeft,
                       double viewBoxHeightView,
                       double viewBoxwidth) {
        this.topLeft = topLeft;
        this.height = new BigDecimal(height);
        this.width = new BigDecimal(width);
        viewBox = new ViewBox(viewBoxTopLeft, viewBoxHeightView, viewBoxwidth);
    }

    public void setDrawingArea(Point topLeft, int height, int width) {
        this.topLeft = topLeft;
        this.height = new BigDecimal(height);
        this.width = new BigDecimal(width);
    }
}
