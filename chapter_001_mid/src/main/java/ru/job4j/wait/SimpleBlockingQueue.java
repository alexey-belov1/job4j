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

    public synchronized void offer(T value) throws InterruptedException  {
        if (this.count == this.size) {
            this.wait();
        }
        this.count++;
        this.queue.offer(value);
        this.notify();
    }

    public synchronized T poll() throws InterruptedException {
        if (this.count == 0) {
            this.wait();
        }
        this.count--;
        this.notify();
        return this.queue.poll();
    }

    public synchronized boolean isEmpty() {
        return this.count == 0;
    }
}