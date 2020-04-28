package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Input from the user (with validate)
 */
public class UserInput implements Input {

    private final Scanner scanner = new Scanner(System.in);
    private Field field;

    public UserInput(Field field) {
        this.field = field;
    }

    @Override
    public int[] askInt(String question) {
        boolean invalid = true;
        int[] out = null;
        do {
            try {
                System.out.print(question);
                String answer = scanner.nextLine();

                if (!Pattern.compile(String.format("[0-%1$s]\\s[0-%1$s]", field.getFigures().length - 1)).matcher(answer).matches()) {
                    throw new Exception();
                }

                out = Arrays.stream(answer.split("\\s")).mapToInt(Integer::parseInt).toArray();

                Figure figure = field.getFigures()[out[0]][out[1]];
                if (figure.hasMarkO() || figure.hasMarkX()) {
                    throw new Exception();
                }
                invalid = false;
            } catch (Exception e) {
                System.out.println("Wrong input! Try again!");
            }
        } while (invalid);

        return out;
    }
}
