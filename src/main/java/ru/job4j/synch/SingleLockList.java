package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Iterator;


@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> simpleArray;

    public SingleLockList() {
        this.simpleArray = new SimpleArray<>();
    }

    public synchronized void add(T value) {
        simpleArray.add(value);
    }

    public synchronized T get(int index) {
        return simpleArray.get(index);
    }

    public synchronized SimpleArray<T> copy(SimpleArray<T> list) {
        SimpleArray<T> copy = new SimpleArray<>();
        for (T x:list) {
            copy.add(x);
        }
        return copy;

    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.simpleArray).iterator();
    }
}