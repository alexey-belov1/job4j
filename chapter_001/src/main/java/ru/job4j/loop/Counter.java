package ru.job4j.loop;

public class Counter {
    public static int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            sum = (i % 2 == 0) ? sum + i : sum;
        }
        return sum;
    }
}