package ru.job4j.list;

import java.util.NoSuchElementException;

public class Node<T> {

    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }

    /**
     * Метод, который проверяет цикличность в списке.
     * В методе используется цикл для движения по элементам списка. Количество итераций цикла сохраняется в переменной beginCount.
     * На каждой итерации:
     *      - у текущего узла временно удаляется ссылка на следующий и рассчитывается количество узлов от первого до текущего узла (метод count(Node first));
     *      - проверяется условие beginCount > count(Node first), выполнение которого говорит о том, что в списке имеется цикличность;
     *      - ссылка на следующий узел обратно вставляется в текущий узел.
     */
    public static <E> boolean hasCycle(Node<E> first) {
        if (first == null) {
            throw new NoSuchElementException();
        }

        boolean result = false;
        Node<E> initialFirst = first;
        int beginCount = 0;
        while (first.next != null) {
            beginCount++;
            Node<E> tempDel = first.next;
            first.next = null;
            if (beginCount > Node.count(initialFirst)) {
                result = true;
                first.next = tempDel;
                break;
            }
            first.next = tempDel;
            first = first.next;
        }

        return result;
    }

    private static <E> int count(Node<E> first) {
        return (first.next == null) ? 1 : count(first.next) + 1;
    }
}