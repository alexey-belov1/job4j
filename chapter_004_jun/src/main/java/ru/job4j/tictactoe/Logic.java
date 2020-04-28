package ru.job4j.tictactoe;

/**
 * Logic for game
 */
public interface Logic {
    /**
     * Defines logic when X win
     * @return true if win X
     */
    boolean isWinnerX();

    /**
     * Defines logic when O win
     * @return true if win O
     */
    boolean isWinnerO();

    /**
     * Determines whether there is an opportunity for a move
     * @return true if has opportunity
     */
    boolean hasGap();
}
