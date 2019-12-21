/**
 * Class for Arithmetic operations.
 *
 * @author Alexey Belov (veshousis1@gmail.com)
 */

package ru.job4j.calculate;

public class Calculate {

    /**
     * Method add
     * @param first - first number
     * @param second - second number
     * @return addition of numbers
     */
    public static double add(double first, double second) {
       return first + second;
    }

    /**
     * Method div
     * @param first - first number
     * @param second - second number
     * @return division of numbers
     */
    public static double div(double first, double second) {
        return first / second;
    }

    /**
     * Method multiply
     * @param first - first number
     * @param second - second number
     * @return multiply of numbers
     */
    public static double multiply(double first, double second) {
        return first * second;
    }

    /**
     * Method subtract
     * @param first - first number
     * @param second - second number
     * @return subtract of numbers
     */
    public static double subtract(double first, double second) {
        return first - second;
    }

    /**
     * Method main
     *
     * @param args - first and second numbers (type String[])
     * @return print result of addition, division, multiply and subtract of numbers
     */
    public static void main(String[] args) {
        double first = Double.valueOf(args[0]);
        double second = Double.valueOf(args[1]);
        System.out.println(String.format("%s + %s = %s", first, second, add(first, second)));
        System.out.println(String.format("%s + %s = %s", first, second, div(first, second)));
        System.out.println(String.format("%s + %s = %s", first, second, multiply(first, second)));
        System.out.println(String.format("%s + %s = %s", first, second, subtract(first, second)));
    }
}