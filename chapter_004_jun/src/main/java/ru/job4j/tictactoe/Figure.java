package ru.job4j.tictactoe;

/**
 * One figure on field
 */
public class Figure {

    /**
     * Mark X for figure
     */
    private boolean markX = false;

    /**
     * Mark O for figure
     */
    private boolean markO = false;

    /**
     * Set mark X for figure
     */
    public void setMarkX() {
        this.markX = true;
        this.markO = false;
    }

    /**
     * Set mark O for figure
     */
    public void setMarkO() {
        this.markX = false;
        this.markO = true;
    }

    /**
     * Check if set mark X
     * @return true if set
     */
    public boolean hasMarkX() {
        return markX;
    }

    /**
     * Check if set mark O
     * @return true if set
     */
    public boolean hasMarkO() {
        return markO;
    }
}
