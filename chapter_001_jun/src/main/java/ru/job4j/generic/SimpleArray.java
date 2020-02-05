package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        this.array[this.index++] = model;
    }

    public boolean set(int i, T model) {
        boolean result = false;
        if (i < index) {
            this.array[i] = model;
            result = true;
        }
        return result;
    }

    public boolean remove(int i) {
        boolean result = false;
        if (i < index) {
            System.arraycopy(this.array, i + 1, this.array, i, index - 1 - i);
            index--;
            array[index] = null;
            result = true;
        }
        return result;
    }

    public T get(int i) {
        return (T) this.array[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indexIt = 0;

            @Override
            public boolean hasNext() {
                while (indexIt < array.length - 1 && array[indexIt] == null) {
                    indexIt++;
                }
                return array[indexIt] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[indexIt++];
            }
        };
    }
}
