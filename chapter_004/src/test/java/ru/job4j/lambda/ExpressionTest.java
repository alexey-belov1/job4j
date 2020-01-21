package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionTest {
    private final Expression function = new Expression();

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunction() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x * x + 1);
        List<Double> expected = Arrays.asList(51D, 73D, 99D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunction() {
        List<Double> result = new ArrayList<>();
        result.addAll(function.diapason(10, 11, x -> Math.log10(x)));
        result.addAll(function.diapason(100, 101, x -> Math.log10(x)));
        List<Double> expected = Arrays.asList(1D, 2D);
        assertThat(result, is(expected));
    }
}
