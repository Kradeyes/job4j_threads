package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int rsl;
        do {
            rsl = count.get();
        } while (!count.compareAndSet(rsl, ++rsl));
    }

    public int get() {
        return count.get();
    }
}