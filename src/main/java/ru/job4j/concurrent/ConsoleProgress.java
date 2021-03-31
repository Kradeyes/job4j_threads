package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] progress = {"\\", "|", "/"};
        int count = 0;

     while (!Thread.currentThread().isInterrupted()) {
         System.out.print("\r load: " + progress[count++]);
         if (count >= progress.length) {
             count = 0;
         }
         try {
             Thread.sleep(500);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}
