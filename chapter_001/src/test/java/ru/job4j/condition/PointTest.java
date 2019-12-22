package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;


public class PointTest {

    @Test
    public void distance() {
        int x1 = 0;
        int y1 = 1;
        int x2 = 0;
        int y2 = -1;
        double expected = 2.0;
        double out = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, out, 0.01);
    }

}
