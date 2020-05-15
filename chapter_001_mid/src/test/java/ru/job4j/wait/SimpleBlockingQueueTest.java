package ru.job4j.wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        i -> {
                            try {
                                queue.offer(i);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }

                        }
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}