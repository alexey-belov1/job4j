package ru.job4j.oop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenMax2Numbers() {
        int result = Max.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenMax3Numbers() {
        int result = Max.max(3, 7, 5);
        assertThat(result, is(7));
    }

    @Test
    public void whenMax4Numbers() {
        int result = Max.max(3, 9, 5, 7);
        assertThat(result, is(9));
    }
}