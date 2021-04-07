package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(int value) {
        this.count = new AtomicReference<>(value);
    }

    public void increment() {
        int rsl;
        do {
            rsl = count.get();
        } while (!count.compareAndSet(rsl, rsl + 1));
    }

    public int get() {
        return count.get();
    }
}