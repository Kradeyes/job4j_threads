package ru.job4j;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void test() throws InterruptedException {
        CASCount count = new CASCount(0);
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        count.increment();
                    }
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        count.increment();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(count.get(), is(20));
    }
}
