package ru.job4j.nonblock;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASQueue<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    private final AtomicReference<Node<T>> tail = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);

        if (head.compareAndSet(null, temp)) {
            tail.set(head.get());
            return;
        }

        Node<T> ref;
        do {
            ref = head.get();
        } while (!head.compareAndSet(ref, temp));
        ref.next = temp;
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;

        do {
            if (tail.get() == null) {
                throw new IllegalStateException("Queue is empty");
            }
            ref = tail.get();
            temp = ref.next;
        } while (!tail.compareAndSet(ref, temp));

        return ref.value;
    }

    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}