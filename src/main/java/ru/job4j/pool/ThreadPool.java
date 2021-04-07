package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void init() {
    for (int i = 0; i < size ;i++) {
        try {
            threads.add(new Thread(tasks.poll()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        threads.get(i).start();
    }
    }

    public void work(Runnable job) {
    tasks.offer(job);
    }

    public void shutdown() {
    for (int i = 0; i < size; i++) {
        threads.get(i).interrupt();
    }
    }
}