package ru.job4j.nonblock;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class CacheTest {

    @Test
    public void whenUpdatingInTwoThreadsSuccessfully() throws InterruptedException {
        Cache cache = new Cache();
        cache.add(new Base(1, "base"));

        Thread thread1 = new Thread(
                () -> cache.update(cache.get(1))
        );

        Thread thread2 = new Thread(
                () -> cache.update(cache.get(1))
        );

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        Base result = cache.get(1);
        assertThat(result.getName(), is("base"));
        assertThat(result.getVersion(), is(2));
    }

    @Test
    public void whenUpdatingInTwoThreadsUnSuccessfully() throws InterruptedException {
        Cache cache = new Cache();
        Base base = new Base(1, "base");
        cache.add(base);

        AtomicReference<Exception> ex = new AtomicReference<>();

        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(cache.get(1));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(base);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        Assert.assertThat(ex.get().getMessage(), CoreMatchers.is("Versions are not equal"));
    }
}