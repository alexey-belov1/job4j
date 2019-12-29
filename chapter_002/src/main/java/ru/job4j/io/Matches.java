package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int matches = 11;
        int count = 0;

        while (matches != 0) {
            System.out.println("Осталось спичек на столе: " + matches);
            if (count % 2 == 0) {
                System.out.println("Ход первого игрока. Возьмите от 1 до 3 спичек");
            } else {
                System.out.println("Ход второго игрока. Возьмите от 1 до 3 спичек");
            }

            int select = Integer.valueOf(input.nextLine());
            if (select >= 1 && select <= 3) {
                if (matches - select < 0) {
                    System.out.println("На столе осталось меньше спичек, чем вы хотите взять.");
                } else {
                    matches -= select;
                    count++;
                }
            } else {
                System.out.println("Необходимо выбрать от 1 до 3 спичек.");
            }
            System.out.println();
        }

        if (count % 2 == 0) {
            System.out.println("Выиграл второй игрок.");
        } else {
            System.out.println("Выиграл первый игрок.");
        }
    }
}
