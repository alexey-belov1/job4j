package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

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
                || (values[indexRow].length == indexColumn && findNotEmptyRow().isPresent());
    }

    @Override
    public Object next() {
        if (values[indexRow].length == indexColumn) {
            indexRow = findNotEmptyRow().orElseThrow(NoSuchElementException::new);
            indexColumn = 0;
        }
        return values[indexRow][indexColumn++];
    }

    private Optional<Integer> findNotEmptyRow() {
        return Stream.iterate(indexRow + 1, i -> i < values.length, i -> i + 1)
                .filter(i -> values[i].length > 0)
                .findFirst();
    }

}
