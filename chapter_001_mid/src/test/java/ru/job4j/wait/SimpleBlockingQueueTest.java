package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);

        int[] arrayFrom = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arrayTo = new int[10];

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(arrayFrom[i]);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    arrayTo[i] = queue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(arrayFrom, is(arrayTo));
    }
}