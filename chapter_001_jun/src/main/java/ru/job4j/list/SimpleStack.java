package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private DynamicLink<T> link = new DynamicLink<>();

    public T poll() {
        int size = 0;
        for (T cell : link) {
            size++;
        }

        if (size == 0) {
            throw new NoSuchElementException();
        }

        T data = link.get(size - 1);
        link.remove(size - 1);
        return data;
    }

    public void push(T value) {
        link.add(value);
    }
}