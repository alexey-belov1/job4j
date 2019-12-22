package ru.job4j.condition;

public class SqArea {
    public static double square(int p, int k) {
        return  k * Math.pow(p, 2.0) / (4.0 * Math.pow(k + 1.0, 2.0));
    }

    public static void main(String[] args) {
        double result = square(6, 2);
        System.out.println(" p = 6, k = 2, s = 2, real = " + result);
    }
}