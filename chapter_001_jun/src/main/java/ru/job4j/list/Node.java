package ru.job4j.list;

public class Node<T> {

    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }

    public static <E> boolean hasCycle(Node<E> first) {
        boolean result = false;
        Node<E> node1 = first;
        Node<E> node2 = first;

        while (node2 != null && node2.next != null) {
            node1 = node1.next;
            node2 = node2.next.next;

            if (node1 == node2) {
                result = true;
                break;
            }
        }

        return result;
    }
}