package MainSystem.Tests;


import MainSystem.CoordinateSystem.CartesianCoordinateSystem;
import MainSystem.Functions.FunctionPredicates;
import MainSystem.GlobalSystem.OutputPredicates;
import MainSystem.Points.Point;
import MainSystem.Triangles.Triangle;

import java.math.BigDecimal;
import java.util.function.Function;

import static MainSystem.GlobalSystem.OutputPredicates.*;

/**
 * Created by Owner on 9/18/2017.
 */
@SuppressWarnings("WeakerAccess")
public class MiscTest {
    public static void main(String... args)
    {
        print(FunctionPredicates.constantFunction(new double[]{1, 2}, new BigDecimal(4)));
        print(FunctionPredicates.constantFunction(new BigDecimal[]{new BigDecimal(1), new BigDecimal(2)},new BigDecimal(3)));
        print(FunctionPredicates.identityFunction(new double[]{2, 3}));
        print(FunctionPredicates.squareFunction(new double[]{2, 3}));
        print(FunctionPredicates.cubeFunction(new double[]{2, 3}));
        print(FunctionPredicates.squareRootFunction(new double[]{2, 3}));
        print(FunctionPredicates.recipricolFunction(new double[]{2, 3}));
        print(FunctionPredicates.absoluteFunction(new double[]{2, 3}));
        print(FunctionPredicates.cubeRootFunction(new double[]{2, 3}));

        print(FunctionPredicates.constantFunction(new int[]{1, 2}, new BigDecimal(4)));
        print(FunctionPredicates.identityFunction(new int[]{2, 3}));
        print(FunctionPredicates.squareFunction(new int[]{2, 3}));
        print(FunctionPredicates.cubeFunction(new int[]{2, 3}));
        print(FunctionPredicates.squareRootFunction(new int[]{2, 3}));
        print(FunctionPredicates.recipricolFunction(new int[]{2, 3}));
        print(FunctionPredicates.absoluteFunction(new int[]{2, 3}));
        print(FunctionPredicates.cubeRootFunction(new int[]{2, 3}));
        print(FunctionPredicates.shiftGraph(new Point[]{new Point(0,1), new Point(1,2)},2,2));
        print(FunctionPredicates.expandGraph(new Point[]{new Point(0,1), new Point(1,2)},2,2));

        print(FunctionPredicates.shiftGraph(new Point[]{new Point(0,1), new Point(1,2)},3,3));
        print(FunctionPredicates.expandGraph(new Point[]{new Point(0,1), new Point(1,2)},3,3));
        print(new CartesianCoordinateSystem());
        CartesianCoordinateSystem cartesianCoordinateSystem = new CartesianCoordinateSystem(10, 10, 10);
        CartesianCoordinateSystem cartesianCoordinateSystem1 = new CartesianCoordinateSystem(20, 20, 20);
        print(cartesianCoordinateSystem);
        cartesianCoordinateSystem.plotPoint(new Point(0,0));
        print(cartesianCoordinateSystem);
        cartesianCoordinateSystem.plotShape(Triangle.createInstance(new Point[]{new Point(0,0), new Point(2,0), new Point(1, 3)}));
        cartesianCoordinateSystem.plotShape(Triangle.createInstance(new Point[]{new Point(0,0), new Point(2,0), new Point(1, -3)}));
        print(cartesianCoordinateSystem);


    }
}
