package ru.job4j.tictactoe;

/**
 * Class that contains the method for defined opportunity to move
 */
public class Gap {

    /**
     * Determines whether there is an opportunity for a move
     * @param field
     * @return true if has opportunity
     */
    public boolean hasGap(Field field) {
        boolean result = false;
        for (Figure[] arr : field.getFigures()) {
            for (Figure figure : arr) {
                if (!figure.hasMarkO() && !figure.hasMarkX()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
