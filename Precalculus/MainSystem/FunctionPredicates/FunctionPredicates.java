package MainSystem.FunctionPredicates;

import MainSystem.Point.Point;
import MainSystem.SystemGlobal.SystemGlobal;

import java.math.BigDecimal;
import java.math.MathContext;

@SuppressWarnings("WeakerAccess")
public class FunctionPredicates {
    @SuppressWarnings("WeakerAccess")
    public static Point[] constantFunction(BigDecimal[] domainValues, BigDecimal constant) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(constant.toString()));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] identityFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].toString()));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] squareFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].pow(2).toString()));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] cubeFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(domainValues[i].pow(3).toString()));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] squareRootFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), Math.sqrt(domainValues[i].doubleValue()));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] recipricolFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), new BigDecimal(1).divide(domainValues[i], new MathContext(SystemGlobal.CALC_PRECISION)));
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] absoluteFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), domainValues[i].abs());
        }
        return rangePoints;
    }

    @SuppressWarnings("WeakerAccess")
    public static Point[] cubeRootFunction(BigDecimal[] domainValues) {
        Point[] rangePoints = new Point[domainValues.length];
        for (int i = 0; i < domainValues.length; i++) {
            rangePoints[i] = new Point(new BigDecimal(domainValues[i].toString()), Math.sqrt(Math.sqrt(domainValues[i].doubleValue())));
        }
        return rangePoints;
    }

    private static BigDecimal[] convertNumericalArrayToBigDecimalArray(double[] doubleArray) {
        BigDecimal[] bigDArray = new BigDecimal[doubleArray.length];
        for (int i = 0; i < bigDArray.length; i++) {
            bigDArray[i] = new BigDecimal(doubleArray[i]);
        }
        return bigDArray;
    }

    public static Point[] constantFunction(double[] domainValues, BigDecimal constant) {
        return constantFunction(
                convertNumericalArrayToBigDecimalArray(domainValues),
                constant
        );
    }

    public static Point[] identityFunction(double[] domainValues) {
        return identityFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] squareFunction(double[] domainValues) {
        return squareFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] cubeFunction(double[] domainValues) {
        return cubeFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] squareRootFunction(double[] domainValues) {
        return squareRootFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] recipricolFunction(double[] domainValues) {
        return recipricolFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] absoluteFunction(double[] domainValues) {
        return absoluteFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );
    }

    public static Point[] cubeRootFunction(double[] domainValues) {
        return cubeRootFunction(
                convertNumericalArrayToBigDecimalArray(domainValues)
        );

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

    @SuppressWarnings("WeakerAccess")
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

    @SuppressWarnings("WeakerAccess")
    public static Point[] expandGraph(Point[] pointsBeforeTransorfmation, BigDecimal horizontalExpansion, BigDecimal verticalExpansion) {
        for (int i = 0; i < pointsBeforeTransorfmation.length; i++) {
            pointsBeforeTransorfmation[i].xCoordinate = pointsBeforeTransorfmation[i].xCoordinate.multiply(horizontalExpansion);
            pointsBeforeTransorfmation[i].yCoordinate = pointsBeforeTransorfmation[i].yCoordinate.multiply(verticalExpansion);
        }
        return pointsBeforeTransorfmation;
    }
}