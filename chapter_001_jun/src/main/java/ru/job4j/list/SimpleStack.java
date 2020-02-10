package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private DynamicLink<T> link = new DynamicLink<>();
    private int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T data = link.get(size - 1);
        link.remove(size - 1);
        size--;
        return data;
    }

    public void push(T value) {
        link.add(value);
        size++;
    }
}