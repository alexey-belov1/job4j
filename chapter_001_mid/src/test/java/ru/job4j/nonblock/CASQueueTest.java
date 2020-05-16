package ru.job4j.nonblock;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class CASQueueTest {

    @Test
    public void when3PushThen3Poll() {
        CASQueue<Integer> queue = new CASQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }
}