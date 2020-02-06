package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayTest {

    @Test
    public void whenAddElementsGetElementsAndArrayChangeSize() {
        DynamicArray<Integer> array = new DynamicArray<Integer>();
        for (int i = 1; i <= 15; i++) {
            array.add(i);
        }
        assertThat(array.get(0), is(1));
        assertThat(array.get(14), is(15));
    }

    @Test
    public void whenCheckIterator() {
        DynamicArray<Integer> array = new DynamicArray<Integer>();
        array.add(1);
        array.add(2);
        Iterator<Integer> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModCountItFromIteratorNotEqualModCount() {
        DynamicArray<Integer> array = new DynamicArray<Integer>();
        for (int i = 1; i <= 10; i++) {
            array.add(i);
        }
        Iterator<Integer> it = array.iterator();
        it.next();
        array.add(11);
        it.next();
    }
}