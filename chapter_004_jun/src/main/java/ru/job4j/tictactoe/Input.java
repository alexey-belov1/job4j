package ru.job4j.tictactoe;

/**
 * Input from the user or bot
 */
public interface Input {

    /**
     * Request for a move from the user or bot
     * @param question
     * @return int[] - row and column
     */
    int[] askInt(String question);
}