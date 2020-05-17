package ru.job4j.pool;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    @Test
    public void whenWork() throws InterruptedException {
        ThreadPool pool = new ThreadPool(3);
        AtomicInteger x = new AtomicInteger();

        pool.work(x::incrementAndGet);
        pool.work(x::incrementAndGet);
        pool.work(x::incrementAndGet);

        Thread.sleep(50);
        pool.shutdown();

        assertThat(x.get(), is(3));
    }
}