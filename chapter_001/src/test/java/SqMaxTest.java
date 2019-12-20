package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void MaxTestFirst() {
        int result = SqMax.max(4, 1,2,3);
        assertThat(result, is(4));
    }

    @Test
    public void MaxTestSecond() {
        int result = SqMax.max(1, 4,2,3);
        assertThat(result, is(4));
    }

    @Test
    public void MaxTestThird() {
        int result = SqMax.max(1, 2,4,3);
        assertThat(result, is(4));
    }

    @Test
    public void MaxTestFourth() {
        int result = SqMax.max(1, 2,3,4);
        assertThat(result, is(4));
    }

    @Test
    public void MaxTestAll() {
        int result = SqMax.max(4, 4,4,4);
        assertThat(result, is(4));
    }
}
