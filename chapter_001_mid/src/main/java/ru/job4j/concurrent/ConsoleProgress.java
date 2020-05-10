package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            String ball;
            boolean flag = false;
            while (!Thread.currentThread().isInterrupted()) {
                ball = flag ? "- \\ | /  " : "  \\ | / -";
                flag = !flag;
                System.out.print("\rload: " + ball);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("\n" + Thread.currentThread().getName() + " interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}