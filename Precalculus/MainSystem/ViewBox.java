package MainSystem;

import MainSystem.Point.Point;

import java.math.BigDecimal;

public class ViewBox {
    Point topLeft;
    BigDecimal height;
    BigDecimal width;

    public ViewBox(Point topLeft, BigDecimal height, BigDecimal width) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
    }

    public ViewBox(Point topLeft, double height, double width) {
        this.topLeft = topLeft;
        this.height = new BigDecimal(height);
        this.width = new BigDecimal(width);
    }
}
