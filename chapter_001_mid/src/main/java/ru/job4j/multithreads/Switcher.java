package ru.job4j.multithreads;

import java.util.concurrent.CountDownLatch;

public class Switcher {

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();
        CountDownLatch cl = new CountDownLatch(1);
        new NewThread("Thread A", monitor, cl).start();
        cl.await();
        new NewThread("Thread B", monitor, cl).start();
    }

    private static class NewThread extends Thread {
        final String name;
        final Object monitor;
        final CountDownLatch cl;

        NewThread(String name, Object monitor, CountDownLatch cl) {
            this.name = name;
            this.monitor = monitor;
            this.cl = cl;
        }

        @Override
        public void run() {
            synchronized (this.monitor) {
                cl.countDown();
                while (true) {
                    System.out.println(this.name);
                    this.monitor.notify();
                    try {
                        Thread.sleep(1000);
                        this.monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}