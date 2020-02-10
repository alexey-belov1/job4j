package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkTest {
    private DynamicLink<Integer> link;

    @Before
    public void beforeTest() {
        link = new DynamicLink<Integer>();
        link.add(1);
        link.add(2);
        link.add(3);
    }

    @Test
    public void whenAddElementsGetElements() {
        assertThat(link.get(0), is(1));
        assertThat(link.get(1), is(2));
        assertThat(link.get(2), is(3));
    }

    @Test
    public void whenRemove() {
        link.remove(1);
        assertThat(link.get(0), is(1));
        assertThat(link.get(1), is(3));
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> it = link.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModCountItFromIteratorNotEqualModCount() {
        Iterator<Integer> it = link.iterator();
        assertThat(it.next(), is(1));
        link.add(4);
        it.next();
    }
}