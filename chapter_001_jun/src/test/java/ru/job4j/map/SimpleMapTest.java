package ru.job4j.map;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {

    @Test
    public void whenInsert() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("first", 1);
        map.insert("second", 2);

        assertThat(map.get("first"), is(1));
        assertThat(map.get("second"), is(2));
    }

    @Test
    public void whenInsertAndReplace() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("first", 1);
        map.insert("first", 2);

        assertThat(map.get("first"), is(2));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.delete("first");
        map.delete("second");
        assertThat(map.get("first"), is(nullValue()));
        assertThat(map.get("second"), is(nullValue()));
    }

    @Test
    public void whenInsertAndReplaceAndDeleteNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert(null, 1);
        map.insert(null, 2);
        assertThat(map.get(null), is(2));
        map.delete(null);
        assertThat(map.get(null), is(nullValue()));
    }

    @Test
    public void whenElementsMoreThanCapacityAndResize() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i <= 18; i++) {
            map.insert(i, 100 + i);
        }
        assertThat(map.get(17), is(117));
        assertThat(map.get(18), is(118));
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("fisrt", 1);
        map.insert("second", 2);
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }
}
