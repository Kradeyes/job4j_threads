package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
     while (queue.size() == size) {
         wait();
     }
     queue.offer(value);
     notifyAll();
     }

    public synchronized T poll() throws InterruptedException {
        T value;
        while (queue.isEmpty()) {
            wait();
        }
        value = queue.poll();
        notifyAll();
        return value;
    }
}
