package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int size;

    @GuardedBy("this")
    private int count = 0;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(final int size) {
        this.size = size;
    }

    public synchronized void offer(T value) {
        if (this.count == this.size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.count++;
        this.queue.offer(value);
        this.notify();
    }

    public synchronized T poll() {
        if (this.count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.count--;
        this.notify();
        return this.queue.poll();
    }
}