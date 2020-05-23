package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private boolean flag = true;

    public synchronized void tryMaster() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        flag = false;
        this.notify();
    }

    public synchronized void doneSlave() {
        flag = true;
        this.notify();
    }
}
