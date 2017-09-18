package MainSystem.Rectangles;

import MainSystem.Lines.Line;
import MainSystem.Lines.LinePredicates;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
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
