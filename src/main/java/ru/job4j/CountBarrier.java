package ru.job4j;

import net.jcip.annotations.GuardedBy;

public class CountBarrier {
    @GuardedBy("monitor")
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            if (count == total) {
                monitor.notifyAll();
            }
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    monitor.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}