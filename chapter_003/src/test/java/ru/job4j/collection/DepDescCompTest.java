package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class DepDescCompTest {

    @Test
    public void whenCompareSameLengthThanLess() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompareDifLengthThanGreater() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK1",
                "K2/SK1"
        );
        assertThat(rsl, greaterThan(0));
    }
}