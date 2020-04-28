package ru.job4j.tictactoe;

/**
 * Field for game
 */
public class Field {

    private Figure[][] figures;

    /**
     * Default constructor that sets the initial field size = 3
     */
    public Field() {
        init(3);
    }

    /**
     * Override constructor that sets the specified field size
     */
    public Field(int size) {
        init(size);
    }

    /**
     * Init field
     * @param size for field
     */
    private void init(int size) {
        this.figures = new Figure[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.figures[i][j] = new Figure();
            }
        }
    }

    /**
     * Set mark X on field
     * @param i - row
     * @param j - column
     */
    public void setMarkX(int i, int j) {
        Figure figure = this.figures[i][j];
        if (!figure.hasMarkX() && !figure.hasMarkX()) {
            figure.setMarkX();
        }
    }

    /**
     * Set mark O on field
     * @param i - row
     * @param j - column
     */
    public void setMarkO(int i, int j) {
        Figure figure = this.figures[i][j];
        if (!figure.hasMarkX() && !figure.hasMarkX()) {
            figure.setMarkO();
        }
    }

    /**
     * Print field on console
     */
    public void show() {
        StringBuilder out = new StringBuilder();
        for (Figure[] arr : this.figures) {
            for (Figure figure : arr) {
                if (figure.hasMarkX()) {
                    out.append("x ");
                } else if (figure.hasMarkO()) {
                    out.append("o ");
                } else {
                    out.append("- ");
                }
            }
            out.append(System.lineSeparator());
        }
        System.out.print(out.toString());
    }

    public Figure[][] getFigures() {
        return this.figures;
    }
}
