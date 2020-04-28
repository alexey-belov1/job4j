package ru.job4j.tictactoe;

import java.util.function.Predicate;

/**
 * Non-standard logic for playing tic-tac-toe when winning by collecting 5 in a row
 */
public class HardLogic implements Logic {

    private Field field;

    /**
     * Size to determine the win
     */
    private final int sizeWin = 5;

    public HardLogic(Field field) {
        this.field = field;
        if (this.field.getFigures().length < this.sizeWin) {
            throw new IllegalStateException(String.format("Length must be more or equal 5. Have a %s", this.field.getFigures().length));
        }
    }

    private boolean fillBy(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = false;
        int count = 0;
        while (startX != this.field.getFigures().length && startY != this.field.getFigures().length
        && startX != -1 && startY != -1) {
            Figure cell = this.field.getFigures()[startX][startY];
            if (predicate.test(cell)) {
                count++;
                if (count == sizeWin) {
                    result = true;
                    break;
                }
            } else {
                count = 0;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }

    private boolean horizontalLineWin(Predicate<Figure> predicate) {
        boolean result = false;
        for (int i = 0; i < this.field.getFigures().length; i++) {
            if (this.fillBy(predicate, 0, i, 1, 0)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean verticalLineWin(Predicate<Figure> predicate) {
        boolean result = false;
        for (int i = 0; i < this.field.getFigures().length; i++) {
            if (this.fillBy(predicate, i, 0, 0, 1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean diagonalLineWin(Predicate<Figure> predicate) {
        boolean result = false;
        for (int index = 0; index < this.field.getFigures().length - this.sizeWin + 1; index++) {
            if (this.fillBy(predicate, index, 0, 1, 1)
                || this.fillBy(predicate, 0, index, 1, 1)
                || this.fillBy(predicate, this.field.getFigures().length - 1 - index, 0, -1, 1)
                || this.fillBy(predicate, this.field.getFigures().length - 1, index, -1, 1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean isWinnerX() {
        return  horizontalLineWin(Figure::hasMarkX)
                || verticalLineWin(Figure::hasMarkX)
                || diagonalLineWin(Figure::hasMarkX);
    }

    @Override
    public boolean isWinnerO() {
        return  horizontalLineWin(Figure::hasMarkO)
                || verticalLineWin(Figure::hasMarkO)
                || diagonalLineWin(Figure::hasMarkO);
    }

    @Override
    public boolean hasGap() {
        return new Gap().hasGap(this.field);
    }
}
