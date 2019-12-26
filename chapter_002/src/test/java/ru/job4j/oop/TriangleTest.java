package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenExist() {
        Triangle triangle = new Triangle(new Point(0, 0), new Point(1, 0), new Point(0, 1));
        double out = triangle.area();
        double expected = 0.5;
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void notExist() {
        Triangle triangle = new Triangle(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        double out = triangle.area();
        double expected = -1.0;
        assertThat(out, is(expected));
    }
}