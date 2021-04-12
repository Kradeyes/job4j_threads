package ru.job4j.cache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test(expected = OptimisticException.class)
    public void whenException() {
        AtomicReference<Exception> ex = new AtomicReference<>(new Exception());
        Cache cache = new Cache();
        Base base1 = new Base(1, "Petr");
        cache.add(base1);
        cache.update(base1);
        cache.update(base1);
    }

    @Test
    public void whenUpdate() {
        AtomicReference<Exception> ex = new AtomicReference<>(new Exception());
        Cache cache = new Cache();
        Base base1 = new Base(1, "Petr");
        cache.add(base1);
        cache.update(base1);
        assertThat(1, is(cache.get(1).getVersion()));
    }

    @Test(expected = OptimisticException.class)
    public void whenDeleteException() {
        AtomicReference<Exception> ex = new AtomicReference<>(new Exception());
        Cache cache = new Cache();
        Base base1 = new Base(1, "Petr");
        cache.add(base1);
        cache.update(base1);
        cache.delete(base1);
        System.out.println(cache.get(1));
    }

    @Test
    public void whenDelete() {
        AtomicReference<Exception> ex = new AtomicReference<>(new Exception());
        Cache cache = new Cache();
        Base base1 = new Base(1, "Petr");
        cache.add(base1);
        cache.delete(base1);
        assertNull(cache.get(1));
    }
}
