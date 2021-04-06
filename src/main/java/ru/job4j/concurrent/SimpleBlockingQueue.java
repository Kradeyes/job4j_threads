package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int size = 5;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public SimpleBlockingQueue() {
    }

    public synchronized void offer(T value) {
     while (queue.size() == size) {
         try {
             wait();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
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

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

