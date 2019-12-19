package ru.job4j.converter;

public class ConverterReserve {

    public static int rubleToEuro(int value) {
        return value / 70;
    }

    public static int rubleToDollar(int value) {
        return value / 60;
    }

    public static int euroToRuble(int value) {
        return value * 70;
    }

    public static int dollarToRuble(int value) {
        return value * 60;
    }

    public static void main(String[] args) {
        int euro = rubleToEuro(140);
        System.out.println("140 rubles are " + euro + " euro.");

        int dollar = rubleToDollar(120);
        System.out.println("120 rubles are " + dollar + " dollar.");

        int ruble1 = euroToRuble(2);
        System.out.println("2 euro are " + ruble1 + " rubles.");

        int ruble2 = dollarToRuble(2);
        System.out.println("2 dollar are " + ruble2 + " rubles.");
    }
}