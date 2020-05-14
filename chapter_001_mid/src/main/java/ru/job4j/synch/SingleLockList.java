package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import ru.job4j.list.DynamicArray;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final DynamicArray<T> list = new DynamicArray<>();

    public synchronized void add(T value) {
        this.list.add(value);
    }

    public synchronized T get(int index) {
        return this.list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy().iterator();
    }

    private synchronized DynamicArray<T> copy() {
        DynamicArray<T> copy = new DynamicArray<>();
        this.list.forEach(copy::add);
        return copy;
    }
}