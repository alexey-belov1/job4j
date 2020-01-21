package ru.job4j;

import java.util.function.*;

public class Calculator {

    public interface Operation {
        double calc(int left, int right);
    }

    /*
    public void multiple(int start, int finish, int value, Operation op) {
        for (int index = start; index != finish; index++) {
            System.out.println(
                    op.calc(value, index)
            );
        }
    }*/

    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        /*
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                new Operation() {
                    @Override
                    public double calc(int left, int right) {
                        return left * right;
                    }
                }
        );*/
        /*
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                (value, index) -> value * index
        );*/
        /*
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                (value, index) -> {
                    int result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                }
        ); */

        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                    },
                result -> System.out.println(result)
            );

    }
}
