package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> {
    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Начальный размер массива
     */
    private int initialCapacity = 16;

    /**
     * Коэффициент загруженности массива
     */
    private float loadFactor = 0.75f;

    /**
     * Массив для хранения элементов
     */
    private Node<K, V>[] table;

    /**
     * Количество добавленных элементов в массив
     */
    private int size = 0;

    /**
     * Счетчик изменений структуры массива
     */
    private int modCount = 0;

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (table == null) {
            table = resize();
        }

        int i = (table.length - 1) & hash(key);
        if (table[i] == null) {
            table[i] = new Node<>(hash(key), key, value);
            result = true;
            if (++size > (int) (loadFactor * table.length)) {
                table = resize();
            }
        } else if (hash(key) == table[i].hash && Objects.equals(key, table[i].key)) {
            table[i].value = value;
            result = true;
        }

        return result;
    }

    private Node<K, V>[] resize() {
        Node<K, V>[] tableTemp;
        if (table == null) {
            tableTemp = new Node[initialCapacity];
        } else {
            tableTemp = new Node[table.length << 1];
            for (Node<K, V> cell : table) {
                if (cell != null) {
                    int i = (tableTemp.length - 1) & cell.hash;
                    tableTemp[i] = cell;
                }
            }
        }
        modCount++;
        return tableTemp;
    }

    public V get(K key) {
        V result = null;
        int i = (table.length - 1) & hash(key);
        if (table != null && table[i] != null
                && hash(key) == table[i].hash && Objects.equals(key, table[i].key)) {
            result = table[i].value;
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int i = (table.length - 1) & hash(key);
        if (table != null && table[i] != null
                && hash(key) == table[i].hash && Objects.equals(key, table[i].key)) {
            table[i] = null;
            result = true;
        }
        return result;
    }

    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int indexIt = 0;
            private int modCountIt = modCount;
            private int sizeIt = 0;

            @Override
            public boolean hasNext() {
                while (table[this.indexIt] == null && this.indexIt < table.length - 1) {
                    this.indexIt++;
                }
                return this.sizeIt < size;
            }

            @Override
            public V next() {
                if (this.modCountIt != modCount) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.sizeIt++;
                return table[this.indexIt++].value;
            }
        };
    }
}
