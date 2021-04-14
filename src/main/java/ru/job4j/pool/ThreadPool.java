package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void init() {
    for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                try {
                    while (!tasks.isEmpty()) {
                        tasks.poll().run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ));
    }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
    tasks.offer(job);
    }

    public void shutdown() {
    for (int i = 0; i < size; i++) {
        threads.get(i).interrupt();
    }
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("loading " + i);
            }
        });
        threadPool.work(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("loading " + i);
            }
        });
        threadPool.init();
        threadPool.shutdown();
    }
}