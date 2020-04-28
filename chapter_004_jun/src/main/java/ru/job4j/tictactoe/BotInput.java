package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Input from the bot
 */
public class BotInput implements Input {

    private Field field;

    public BotInput(Field field) {
        this.field = field;
    }

    @Override
    public int[] askInt(String question) {
        List<int[]> answer = new ArrayList<>();
        for (int i = 0; i < field.getFigures().length; i++) {
            for (int j = 0; j < field.getFigures().length; j++) {
                if (!field.getFigures()[i][j].hasMarkX() && !field.getFigures()[i][j].hasMarkO()) {
                    answer.add(new int[]{i, j});
                }
            }
        }
        int[] out = answer.get(new Random().nextInt(answer.size()));
        System.out.println(String.format("%s%s %s", question, out[0], out[1]));
        return out;
    }
}
