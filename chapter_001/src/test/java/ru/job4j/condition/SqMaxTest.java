package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {
    @Test
    public void maxTestFirst() {
        int result = SqMax.max(4, 1, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void maxTestSecond() {
        int result = SqMax.max(1, 4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void maxTestThird() {
        int result = SqMax.max(1, 2, 4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void maxTestFourth() {
        int result = SqMax.max(1, 2, 3, 4);
        assertThat(result, is(4));
    }

    @Test
    public void maxTestAll() {
        int result = SqMax.max(4, 4, 4, 4);
        assertThat(result, is(4));
    }
}
