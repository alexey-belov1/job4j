package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> itInteger = it.hasNext() ? it.next() : Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                nextWhileEmpty();
                return itInteger.hasNext();
            }

            @Override
            public Integer next() {
                nextWhileEmpty();
                return itInteger.next();
            }

            private void nextWhileEmpty() {
                while (!itInteger.hasNext() && it.hasNext()) {
                    itInteger = it.next();
                }
            }
        };
    }
}
