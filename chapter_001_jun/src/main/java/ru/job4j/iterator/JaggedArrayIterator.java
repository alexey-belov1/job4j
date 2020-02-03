package ru.job4j.iterator;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {
    private final int[][] values;
    private int indexRow = 0;
    private int indexColumn = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values[indexRow].length != indexColumn
                || values.length != indexRow + 1;
    }

    @Override
    public Object next() {
        if (values[indexRow].length == indexColumn) {
            indexRow++;
            indexColumn = 0;
        }
        return values[indexRow][indexColumn++];
    }
}
