package com.company;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
////        Line line = new Line(new Point(new BigDecimal("-3"), new BigDecimal("5")), new Point(new BigDecimal("3"), new BigDecimal("2")), new BigDecimal("1"));
////        System.out.println(line.distance);
////        System.out.println(new Point(new BigDecimal("1"), new BigDecimal("0")).equals(new Point(new BigDecimal("0"), new BigDecimal("0"))));
////
////        LinePredicates.calculateAndDisplayLines(new Point[]{new Point(-2, 1, "A"), new Point(2, 3, "B"), new Point(3, 1, "C")});
////        LinePredicates.calculateAndDisplayLines(new Point[]{
////                new Point(1, 1, "1"), new Point(2, 1, "2"), new Point(3, 3, "3"), new Point(3, 4, "4"),
////                new Point(5, 1, "5"), new Point(3, 6, "6"), new Point(7, 1, "7"), new Point(8, 8, "8"),
////                new Point(3, 9, "9"), new Point(9, 1, "10")});
////        Triangle triangle = Triangle.createInstance(new Point[]{new Point(-2, 1, "A"), new Point(2, 3, "B"), new Point(3, 1, "C")});
////        System.out.println(triangle.type == TriangleShapeType.Right ? "Right" : "Nope");
////        System.out.println(triangle.area);
////        System.out.println(triangle.lines[0].distance);
////        Triangle[] triangles = TrianglePredicates.makeTrianglesFromLine(
////                new Line(new Point(0, 1), new Point(1, 0), 1)
////        );
////
////        for (Triangle triangle1 : triangles) {
////            System.out.println(triangle1.toString());
////        }
////        System.out.println(new Line(new Point(-5, 5), new Point(3, 1), 1).getMidPoint().toString());
////
////        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(-2, -1), new BigDecimal(5), new BigDecimal(2))));
////        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(1, 2), new BigDecimal(13), new BigDecimal(-3))));
////
////        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedYCoordinate(new Point(4, -3), new BigDecimal(5), new BigDecimal(0))));
////        System.out.println(PointPredicates.getPointString(LinePredicates.findPointsFromDistanceAndFixedXCoordinate(new Point(4, 4), new BigDecimal(5), new BigDecimal(0))));
////        Line[] meridians = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(4, 4)}).getMeridians();
//        Triangle triangle1 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(4, 4)});
//        System.out.println(triangle1.type == TriangleShapeType.Equalateral);
////        LinePredicates.displayLineLengths(meridians);
////        System.out.println(new Line(new Point(0, 0), new Point(4, 0), 1).distance);
////        LinePredicates.displayLineLengths(triangle1.lines);
////        Triangle triangle2 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(12, 0), new Point(6, 10.1)});
////        LinePredicates.displayLineLengths(triangle2.lines);
////        System.out.println(triangle.getShapeType());
////        System.out.println(triangle.area.toString());
////        System.out.println(triangle2.getShapeType());
////        System.out.println(triangle2.area.toString());
////        Line[] meridians1 = TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistanceOfOne().getMeridians();
////        LinePredicates.displayLineLengths(TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistanceOfOne().lines);
////
////        for (Line line1 : meridians1) {
////            System.out.println(line1.getMidPoint().toString());
////        }
////        System.out.println("Meridians of an eqalateral triangle intersect at their midpoints: " + (
////                meridians1[0].getMidPoint().equals(meridians1[1].getMidPoint()) && meridians1[0].getMidPoint().equals(meridians1[2].getMidPoint())));
////        System.out.println("Meridians of a square intersect at their midpoints: ");
////        LinePredicates.displayLineLengths(new Line[]{new Line(new Point(0, 0), new Point(0, 4), 1)});
//        System.out.println("A: " + (Triangle.createInstance(new Point[]{new Point(2, 1), new Point(-4, 1), new Point(-4, -3)}).getShapeType()) +
//                "\nB: " + (Triangle.createInstance(new Point[]{new Point(-1, 4), new Point(6, 2), new Point(4, -5)}).getShapeType()) +
//                "\nC: " + (Triangle.createInstance(new Point[]{new Point(-2, -1), new Point(0, 7), new Point(3, 2)}).getShapeType()) +
//                "\nD: " + (Triangle.createInstance(new Point[]{new Point(7, 2), new Point(-4, 0), new Point(4, 6)}).getShapeType()));
//        System.out.println(PointPredicates.getPointString(PointPredicates.sortPoints(new Point[]{new Point(0, 1), new Point(0, 5), new Point(1, 5), new Point(1, 4), new Point(0, 0), new Point(0, 4), new Point(4, 4), new Point(2, 4), new Point(4, 0), new Point(1, 0), new Point(4, 4), new Point(3, 4), new Point(3, 3), new Point(0, 0)})));
//        System.out.println(PointPredicates.getPointString(PointPredicates.sortPoints(new Point[]{new Point(0, 0), new Point(90, 0), new Point(90, 90), new Point(0, 90)})));
//        LinePredicates.displayLineLengths(Rectangle.createInstance(new Point[]{new Point(0, 0), new Point(90, 0), new Point(90, 90), new Point(0, 90)}).getDiagnalLines());
//        LinePredicates.displayLineLengths(Rectangle.createInstance(new Point[]{new Point(0, 0), new Point(60, 0), new Point(60, 60), new Point(0, 60)}).getDiagnalLines());
//        Line line = new Line(new Point(310, 15), new Point(90, 90), 0);
//        System.out.println(line.distance);
//        System.out.println(new Line(new Point(300, 300), new Point(0, 90), 0));
//        BigDecimal bigDecimal = LinePredicates.calculateDistanceBetweenTwoPointsAfterTime
//                (new Point(0, 0), new Point(0, 0), new Point(30, 0), new Point(0, -40), 1, 1, 2);
//        System.out.println(bigDecimal.toString());
//        BigDecimal bigDecimal1 = LinePredicates.calculateDistanceBetweenTwoPointsAfterTime
//                (new Point(0, 0), new Point(0, 100), new Point(0, 0), new Point(79200, 0), 1, 1, 0.000278d);
//        System.out.printf("%f\n", 1d / 3600d);
//        System.out.println(bigDecimal1.toString());
//        System.out.println(new Line(new Point(-1, 8), new Point(2, 3), 1).toString());
//        System.out.println(LinePredicates.doubleLine(new Line(new Point(-1, 8), new Point(2, 3), 1)).toString());
//
//        CartesianCoordinateSystem cartesianCoordinateSystem = new CartesianCoordinateSystem();
////        JFrame grid = new JFrame("Grid");
////        grid.setBounds(400, 400, 600, 600);
////        grid.setVisible(true);
////        grid.getGraphics().drawLine(cartesianCoordinateSystem.twoDimensionalPlane.xAxis.point1.xCoordinate.intValue(),
////                cartesianCoordinateSystem.twoDimensionalPlane.xAxis.point1.yCoordinate.intValue(),
////                cartesianCoordinateSystem.twoDimensionalPlane.xAxis.point2.xCoordinate.intValue(),
////                cartesianCoordinateSystem.twoDimensionalPlane.xAxis.point2.yCoordinate.intValue());
////        grid.getGraphics().drawLine(0,0,1000,1000);
//        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(1, 1)));
//        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(-1, 1)));
//        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(-1, -1)));
//        System.out.println(CartesianCoordinateSystemPredicates.getQuadrantFromPoint(new Point(1, -1)));
//        System.out.println(new Line(new Point(1, 1), new Point(-3, 3)).getXIntercept().toString());
//        System.out.println(new Line(new Point(-3, 3), new Point(1, 1)).getXIntercept().toString());
////
//        System.out.println(new Line(new Point(1, 3), new Point(-3, 1)).getXIntercept().toString());
//        System.out.println(new Line(new Point(-3, 1), new Point(1, 3)).getXIntercept().toString());
////
//        System.out.println(new Line(new Point(-50, -2), new Point(1, 2)).getYIntercept().toString());
//        System.out.println(PointPredicates.symmetricAboutXAxis(new Point[]{new Point(1, 1), new Point(1, -1)}));
//        System.out.println(PointPredicates.symmetricAboutOrigin(new Point[]{new Point(1, -1), new Point(-1, 1)}));
//        System.out.println(PointPredicates.symmetricAboutYAxis(new Point[]{new Point(1, 1), new Point(-1, 1)}));
//        System.out.println(PointPredicates.symmetricAboutOrigin(new Point[]{new Point(1, 1), new Point(-1, -1)}));
////        System.out.println(new Line(new Point(-1, -1), new Point(3, 3)).getXIntercept().toString());
//        System.out.println(PointPredicates.getPointString(PointPredicates.getOriginReflected(new Point[]{new Point(1, 2)})));
//        System.out.println(new Line(new Point(0, 0), new Point(1, 0)).getXIntercept());
//
//        System.out.println(LinePredicates.createLineWithPointSlopeAndMagnitude(new Point(0, 39), new BigDecimal(0.60), new BigDecimal(110)).point2.yCoordinate);
//        Line line1 = new Line(new Point(0, 0), new Point(1, 1));
//        Line line2 = new Line(new Point(0, 0), new Point(-1, 1));
//        System.out.println(LinePredicates.checkPerpendicularity(line1, line2));
//        System.out.println(line1.slope);
//        System.out.println(line2.slope);
//        System.out.println(Math.sqrt(8));
//        Circle circle = new Circle(new Point(0, 0), 1, 10);
//        System.out.println(PointPredicates.getPointString(circle.points));
//        System.out.println(0.70710678118 * 0.70710678118);
//        Circle derivedCircle = CirclePredicates.deriveCircleFromLine(new Line(new Point(-10, -10), new Point(-10, -9)));
//        System.out.println(PointPredicates.getPointString(derivedCircle.getPointsOfACircle(10)));
//        Rectangle containingSquareDerivedFromCircle = SquarePredicates.deriveContainingSquareFromCircle(derivedCircle);
//        System.out.println(containingSquareDerivedFromCircle.getShapeType());
//        System.out.println(derivedCircle.area.subtract(containingSquareDerivedFromCircle.area));
//        Rectangle containedSquareDerivedFromCircle = SquarePredicates.deriveContainedSquareFromCircle(derivedCircle);
//        System.out.println(containedSquareDerivedFromCircle.getShapeType());
//
//        System.out.println(derivedCircle.area.toString());
//        System.out.println(containedSquareDerivedFromCircle.area.toString());
//        System.out.println(derivedCircle.area.subtract(containedSquareDerivedFromCircle.area));
//        System.out.println(PointPredicates.getPointString(circle.getPointsOfACircle(3)));
//        System.out.println(CirclePredicates.getTangent(0, circle).toString());
//        System.out.println(CirclePredicates.getTangent(1, circle).toString());
//        System.out.println(CirclePredicates.getTangent(2, circle).toString());
//        System.out.println(CirclePredicates.getTangent(3, circle).toString());
//        System.out.println(CirclePredicates.getTangent(8, circle).toString());
//        System.out.println(CirclePredicates.getTangent(8, circle).getMidPoint().toString());
//        System.out.printf("\n%f", CirclePredicates.getTangent(8, circle).getMidPoint().xCoordinate.doubleValue());
//        System.out.printf("\n%f\n", CirclePredicates.getTangent(8, circle).getMidPoint().yCoordinate.doubleValue());
//        System.out.println(CirclePredicates.getTangent(8, circle).slope.toString());
//        System.out.println(new Line(circle.center, circle.points[8]).slope.toString());
//        Line midLine = LinePredicates.deriveAverageLine(
//                new Point[]
//                        {
//                                new Point(1, 1),
//                                new Point(2, 1),
//                                new Point(3, 1),
//                                new Point(4, 1),
//                                new Point(1, 3),
//                                new Point(2, 3),
//                                new Point(3, 3),
//                                new Point(4, 3)
//                        },
//                new Point(0, 2)
//        );
//        System.out.println(midLine.toString());
//        System.out.println(PointPredicates.checkIfPointsRepresentFunction(circle.points));
//        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]{new Point(0, 0), new Point(0, 1)}));
//        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]{new Point(0, 0), new Point(0, 0)}));
//        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]
//                {
//                        new Point(1, 1),
//                        new Point(2, 1),
//                        new Point(3, 1),
//                        new Point(4, 1),
//                        new Point(1, 3),
//                        new Point(2, 3),
//                        new Point(3, 3),
//                        new Point(4, 3)
//                }));
//
//        System.out.println(PointPredicates.checkIfPointsRepresentFunction(new Point[]
//                {
//                        new Point(1, 1),
//                        new Point(2, 2),
//                        new Point(3, 3),
//                        new Point(4, 4),
//                        new Point(1, 1),
//                        new Point(2, 2),
//                        new Point(3, 3),
//                        new Point(4, 4)
//                }));
//        Line[] lines = PointPredicates.getLinesOfAllPointsSortedByXCoordinatesThenYCoordinates(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(0, 0)});
//        LinePredicates.displayLineLengths(lines);
//        System.out.println(PointPredicates.getMaximumPoint(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(0, 0), new Point(4,4), new Point(1,1)}));
//        System.out.println(PointPredicates.getMinimumPoint(new Point[]{new Point(0,0), new Point(2, 2), new Point(1, 1), new Point(-1, -1), new Point(4,4), new Point(1,1)}));
//        System.out.println(PointPredicates.getPointString(PointPredicates.sortPointsByX(
//                new Point[]{new Point(0,5), new Point(2, 1), new Point(1, 4), new Point(0, 12), new Point(4,-5), new Point(1,10)}))
//        );
//        System.out.println(PointPredicates.getPointString(PointPredicates.getMaximas(
//                new Point[]{new Point(0,5), new Point(2, 1), new Point(1, 4), new Point(0, 12), new Point(4,-5), new Point(1,10)}))
//        );
////        FunctionPredicates.cubeRootFunction(new int[]{0,1,2,3,4});
////        Point intersection = LinePredicates.getIntersection(new Point(600, 0), new Point(-250, 10), new Point(0, 600), new Point(-10, -400));
////        System.out.println(intersection.toString());
////
////        Point intersection1 = LinePredicates.getIntersection(new Point(-600, 0), new Point(-250, 10), new Point(0, -600), new Point(-10, -400));
////        System.out.println(intersection1.toString());
////
//////        Point intersection2 = LinePredicates.getIntersection(new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400));
//////        System.out.println(intersection2.toString());
////
////        Point intersection3 = LinePredicates.getIntersection(new Point(-600, 600), new Point(250, 125), new Point(0, 600), new Point(0, -400));
////        System.out.println(intersection3.toString());
////
//////        Point intersection4 = LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400));
//////        System.out.println(intersection4.toString());
////        OutputPredicates.printBar();
////        LinePredicates.getIntersection(new Point(-600, 600), new Point(250, 125), new Point(0, 600), new Point(0, -400)).display();
////        LinePredicates.getIntersection(new Point(600, 0), new Point(-250, 10), new Point(0, 600), new Point(-10, -400)).display();
////        LinePredicates.getIntersection(new Point(-600, 0), new Point(-250, 10), new Point(0, -600), new Point(-10, -400)).display();
////        LinePredicates.getIntersection(new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400)).display();
////        LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400)).display();
////
////        LinePredicates.getIntersection(new Point(-600, -600), new Point(250, 125), new Point(0, 600), new Point(0, -400)).display();
////        System.out.println(LinePredicates.getTimeOffsetOfCollision
////                (new Point(600, 600), new Point(-250, -125), new Point(0, 600), new Point(0, -400)).toString());

//        System.out.println(LinePredicates.getTimeOffsetOfCollision(new Point(400, 0), new Point(-250, 0), new Point(0, 600), new Point(0, -400)).toString());
//        System.out.println(LinePredicates.getClosestDistanceOfTwoIntersectingTrejectorys(new Point(400, 0), new Point(-250, 0), new Point(0, 600), new Point(0, -400)).toString());
//        OutputPredicates.print(CirclePredicates.deriveSquareFromCircle(new Circle(new Point(0,0), 2, 1 )));
//        OutputPredicates.printBar();
//        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.BOTTOM));
//        OutputPredicates.printBar();
//        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.TOP));
//        OutputPredicates.printBar();
//        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.LEFT));
//        OutputPredicates.printBar();
//        OutputPredicates.print(CirclePredicates.deriveRectangleFromCircle(new Circle(new Point(0,0), 2, 1 ), FourPropositions.RIGHT));
//        OutputPredicates.print(new Circle(new Point(0,0), 2, 1 ));

        Triangle triangle1 = Triangle.createInstance(new Point[]{new Point(0, 0), new Point(6, 0), new Point(3, 4)});
        System.out.println(triangle1.type == TriangleShapeType.Equalateral);
        System.out.println(triangle1.type == TriangleShapeType.Isoselece);
        OutputPredicates.print(triangle1);
        OutputPredicates.print(triangle1.lines);
        OutputPredicates.printBar();
        Line[] meridians = triangle1.getMeridians();
        OutputPredicates.print(meridians);

        OutputPredicates.printBar();
        Triangle triangle2 = TrianglePredicates.calculateEqualateralTriangleAtOriginFromDistanceOfOne(new BigDecimal(1));
        System.out.println(triangle2.type == TriangleShapeType.Equalateral);
        OutputPredicates.print(triangle2);
        OutputPredicates.print(triangle2.lines);

        OutputPredicates.printBar();
        Line[] meridians1 = triangle2.getMeridians();
        OutputPredicates.print(meridians1);
        OutputPredicates.printBar();
    }
}
