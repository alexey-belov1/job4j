package ru.job4j.io;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String inStr) {
        System.out.print(inStr);
        return scanner.nextLine();
    }
}