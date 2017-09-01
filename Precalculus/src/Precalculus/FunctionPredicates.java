package Precalculus;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Owner on 7/21/2017.
 */
public class FunctionPredicates {
    public static Point[] constantFunction(BigDecimal[] domainValues, BigDecimal constant) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(constant.toString()));
        }
        return rangePoints;
    }

    public static Point[] identityFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].toString()));
        }
        return rangePoints;
    }

    public static Point[] squareFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].pow(2).toString()));
        }
        return rangePoints;
    }

    public static Point[] cubeFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].pow(3).toString()));
        }
        return rangePoints;
    }

    public static Point[] squareRootFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), Math.sqrt(domainValues[i].doubleValue()));
        }
        return rangePoints;
    }

    public static Point[] recipricolFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(1).divide(domainValues[i], new MathContext(SystemGlobal.CALC_PRECISION)));
        }
        return rangePoints;
    }

    public static Point[] absoluteFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), domainValues[i].abs());
        }
        return rangePoints;
    }

    public static Point[] cubeRootFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), Math.sqrt(Math.sqrt(domainValues[i].doubleValue())));
        }
        return rangePoints;
    }

    private static BigDecimal[] convertNumericalArrayToBigDecimalArray(int[] intArray) {
        BigDecimal[] bigDArray = new BigDecimal[intArray.length];
        for (int i = 0; i < bigDArray.length; i++) {
            bigDArray[i] = new BigDecimal(intArray[i]);
        }
        return bigDArray;
    }

    public static Point[] constantFunction(int[] domainValues, BigDecimal constant) {
        return constantFunction(
                convertNumericalArrayToBigDecimalArray(domainValues),
                constant
        );
    }

    public static Point[] identityFunction(int[] domainValues) {
        return identityFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] squareFunction(int[] domainValues) {
        return squareFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] cubeFunction(int[] domainValues) {
        return cubeFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] squareRootFunction(int[] domainValues) {
        return squareRootFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] recipricolFunction(int[] domainValues) {
        return recipricolFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] absoluteFunction(int[] domainValues) {
        return absoluteFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] cubeRootFunction(int[] domainValues) {
        return cubeRootFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );

    }

    public static Point[] shiftGraph(Point[] pointsBeforeTransorfmation, double horizontalShift, double verticalShift) {
        return shiftGraph(pointsBeforeTransorfmation, new BigDecimal(horizontalShift), new BigDecimal(verticalShift));
    }

    public static Point[] shiftGraph(Point[] pointsBeforeTransorfmation, BigDecimal horizontalShift, BigDecimal verticalShift) {
        for (int i = 0; i < pointsBeforeTransorfmation.length; i++) {
            pointsBeforeTransorfmation[i].xCoordinate = pointsBeforeTransorfmation[i].xCoordinate.add(horizontalShift);
            pointsBeforeTransorfmation[i].yCoordinate = pointsBeforeTransorfmation[i].yCoordinate.add(verticalShift);
        }
        return pointsBeforeTransorfmation;
    }

    public static Point[] expandGraph(Point[] pointsBeforeTransorfmation, double horizontalExpansion, double verticalExpansion) {
        return expandGraph(pointsBeforeTransorfmation, new BigDecimal(horizontalExpansion), new BigDecimal(verticalExpansion));
    }

    public static Point[] expandGraph(Point[] pointsBeforeTransorfmation, BigDecimal horizontalExpansion, BigDecimal verticalExpansion) {
        for (int i = 0; i < pointsBeforeTransorfmation.length; i++) {
            pointsBeforeTransorfmation[i].xCoordinate = pointsBeforeTransorfmation[i].xCoordinate.multiply(horizontalExpansion);
            pointsBeforeTransorfmation[i].yCoordinate = pointsBeforeTransorfmation[i].yCoordinate.multiply(verticalExpansion);
        }
        return pointsBeforeTransorfmation;
    }
}