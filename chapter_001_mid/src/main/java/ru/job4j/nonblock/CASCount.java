package ru.job4j.nonblock;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int value) {
        this.count.set(value);
    }

    public void increment() {
        Integer ref;
        do {
            ref = this.count.get();
        } while (!this.count.compareAndSet(ref, ref + 1));
    }

    public void decrement() {
        Integer ref;
        do {
            ref = this.count.get();
        } while (!this.count.compareAndSet(ref, ref - 1));
    }

    public int get() {
        return this.count.get();
    }
}