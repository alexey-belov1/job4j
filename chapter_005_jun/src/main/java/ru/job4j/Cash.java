package ru.job4j;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash<K, V> {
    private Map<K, SoftReference<V>> cash = new HashMap<>();

    public void put(K key, V value) {
        this.cash.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V value = null;
        if (this.cash.containsKey(key)) {
            value = cash.get(key).get();
        }
        return value;
    }
}
