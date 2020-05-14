package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        if (check()) {
            monitor.notifyAll();
        }
    }

    public synchronized void await() {
        if (!check()) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized boolean check() {
        return count == total;
    }
}