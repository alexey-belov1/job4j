package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private DynamicArray<E> array = new DynamicArray<>();

    public void add(E value) {
        if (!hasSameElement(value)) {
            array.add(value);
        }
    }

    private boolean hasSameElement(E value) {
        boolean result = false;
        for (E cell : array) {
            if (Objects.equals(cell, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
