package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements Iterable<E> {

    private Object[] objects = new Object[10];
    private int index = 0;
    private int modCount = 0;

    public void add(E value) {
        if (this.index == this.objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
            modCount++;
        }
        objects[this.index++] = value;
    }

    public E get(int index) {
        return (E) objects[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int indexIt = 0;
            private int modCountIt = modCount;

            @Override
            public boolean hasNext() {
                return (this.indexIt != objects.length) && objects[this.indexIt] != null;
            }

            @Override
            public E next() {
                if (this.modCountIt != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) objects[this.indexIt++];
            }
        };
    }

}
