package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private SimpleStack<T> stack1 = new SimpleStack<>();
    private SimpleStack<T> stack2 = new SimpleStack<>();
    private int size1 = 0;
    private int size2 = 0;

    public T poll() {
        if (size2 == 0) {
            if (size1 == 0) {
                throw new NoSuchElementException();
            }
            while (size1 > 0) {
                stack2.push(stack1.poll());
                size1--;
                size2++;
            }
        }
        size2--;
        return stack2.poll();
    }

    public void push(T value) {
        stack1.push(value);
        size1++;
    }
}
