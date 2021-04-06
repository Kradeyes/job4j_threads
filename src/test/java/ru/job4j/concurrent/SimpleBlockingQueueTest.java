package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whileAddOnlyOne() throws InterruptedException {
        SimpleBlockingQueue<String> sbq = new SimpleBlockingQueue<>(10);
        StringBuilder sb = new StringBuilder();
        Thread producer = new Thread(
                () -> {
                    try {
                        sbq.offer("Privet");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        sb.append(sbq.poll());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        consumer.start();
        producer.start();
        producer.join();
        assertEquals("Privet", sb.toString());
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        IntStream.range(0, 5).forEach(queue::offer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}
