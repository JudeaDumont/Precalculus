package com.company;

/**
 * Created by Owner on 7/26/2017.
 */
public class OutputPredicates {
    public static void printBar() {
        System.out.println("##################################################################################################################################");
        System.out.println("##################################################################################################################################");
        System.out.println("##################################################################################################################################");
    }

    //    public static void print(Shape shape) {
//            System.out.println(shape.toString());
//    }
    public static void print(Object someType) {
        System.out.println(someType.toString());
    }
    public static void print(Object[] someTypeArray) {
        for (Object o : someTypeArray) {
            System.out.println(o.toString());
        }
    }
}
