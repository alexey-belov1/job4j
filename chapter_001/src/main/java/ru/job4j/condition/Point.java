package ru.job4j.condition;

public class Point {
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2.0) + Math.pow(y2-y1, 2.0));
    }

    public static void main(String[] args) {
        double result = distance(0, 0, 2, 0);
        System.out.println("result (0, 0) to (2, 0) " + result);

        result = distance(1, 1, 2, 2);
        System.out.println("result (1, 1) to (2, 2) " + result);

        result = distance(-3, 1, 1, 3);
        System.out.println("result (-3, 1) to (1, 3) " + result);
    }
}