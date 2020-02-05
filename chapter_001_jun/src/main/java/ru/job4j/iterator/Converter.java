package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> itInteger = it.hasNext() ? it.next() : Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                while (!itInteger.hasNext() && it.hasNext()) {
                    itInteger = it.next();
                }
                return itInteger.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return itInteger.next();
            }
        };
    }
}
