package ru.job4j.tictactoe;

import java.util.function.Predicate;

/**
 * Standard logic for playing tic tac toe when wins after collecting the entire line
 */
public class StandardLogic implements Logic {

    private Field field;

    public StandardLogic(Field field) {
        this.field = field;
    }

    private boolean fillBy(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index < this.field.getFigures().length; index++) {
            Figure cell = this.field.getFigures()[startX][startY];
            if (!predicate.test(cell)) {
                result = false;
                break;
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
        if (this.fillBy(predicate, 0, 0, 1, 1)
                || this.fillBy(predicate, this.field.getFigures().length - 1, 0, -1, 1)) {
            result = true;
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
