package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void whenPushElementsPollElements() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(1));
        stack.push(1);
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(1));
    }
}