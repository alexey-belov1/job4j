package ru.job4j.nonblock;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void whenIncrementAndDecrementCount() throws InterruptedException {
        CASCount count = new CASCount(0);
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        count.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 1000000; i++) {
                        count.decrement();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(count.get(), is(0));
    }
}
