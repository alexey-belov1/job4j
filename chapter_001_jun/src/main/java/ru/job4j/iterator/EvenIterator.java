package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenIterator(final int[]values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        if (this.index != -1) {
            this.index = Stream.iterate(this.index, i -> i < values.length, i -> i + 1)
                    .filter(i -> values[i] % 2 == 0)
                    .findFirst()
                    .orElse(-1);
        }
        return this.index != -1;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[this.index++];
    }
}
