package Precalculus;

import java.math.BigDecimal;

/**
 * Created by Owner on 7/12/2017.
 */
public class RectanglePredicates {
    public static RectangleShapeType determineRectangleType(Line[] lines) {
        RectangleShapeType type = null;
        BigDecimal[] distances = LinePredicates.getLineLengths(lines);
        if (distances[0].equals(distances[1]) && distances[1].equals(distances[2]) && distances[2].equals(distances[3])) {
            type = RectangleShapeType.Square;
        } else if (distances[0].equals(distances[1]) || distances[0].equals(distances[2])) {
            type = RectangleShapeType.Rectangle;
        } else {//default is just a rectangle
            type = RectangleShapeType.Rectangle;
        }
        return type;
    }
}