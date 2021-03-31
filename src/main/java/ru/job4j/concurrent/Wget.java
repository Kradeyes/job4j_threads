package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger index = new AtomicInteger();
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 101; i++) {
                           index.set(i);
                           System.out.print("\rLoading : " + index  + "%");
                           Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                );
        thread.start();
        thread.join();
        System.out.println();
        System.out.println("Loading is complete");
    }
}
