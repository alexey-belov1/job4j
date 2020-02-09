package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLink<E> implements Iterable<E> {

    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    //Метод добавляет данные в конец списка
    public void add(E value) {
        if (size == 0) {
           this.first = new Node<>(value);
        } else {
           getNode(size - 1).next = new Node<>(value);
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        Node<E> nodeTemp = this.first;
        for (int i = 0; i < index; i++) {
            nodeTemp = nodeTemp.next;
        }
        return nodeTemp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int indexIt = 0;
            private int modCountIt = modCount;

            @Override
            public boolean hasNext() {
                return this.indexIt != size;
            }

            @Override
            public E next() {
                if (this.modCountIt != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(this.indexIt++);
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
