package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distance2d() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 2);
        double out = a.distance(b);
        double expected = 1.0;
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3d() {
        Point a = new Point(1, 1, 1);
        Point b = new Point(1, 1, 2);
        double out = a.distance3d(b);
        double expected = 1.0;
        Assert.assertEquals(expected, out, 0.01);
    }

}
