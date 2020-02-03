package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = -1;

    public EvenIterator(final int[]values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return nextIndex() != -1;
    }

    @Override
    public Object next() {
        this.index = nextIndex();
        if (this.index == -1) {
            throw new NoSuchElementException();
        }
        return values[this.index];
    }

    private int nextIndex() {
        return Stream.iterate(this.index + 1, i -> i < values.length, i -> i + 1)
                .filter(i -> values[i] % 2 == 0)
                .findFirst()
                .orElse(-1);
    }
}
