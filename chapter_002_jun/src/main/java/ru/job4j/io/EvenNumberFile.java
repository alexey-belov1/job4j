package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("even.txt"))) {
            in.lines().forEach(x -> {
                if (Integer.valueOf(x) % 2 == 0) {
                    System.out.println(x + " - четное число");
                } else {
                    System.out.println(x + " - нечетное число");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
