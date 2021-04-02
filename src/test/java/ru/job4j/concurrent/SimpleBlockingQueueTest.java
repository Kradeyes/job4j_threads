package ru.job4j.concurrent;

import org.junit.Test;
import static org.junit.Assert.*;

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
}