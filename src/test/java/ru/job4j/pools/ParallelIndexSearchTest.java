package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelIndexSearchTest {
     private static final Integer[] ARRAY = {1, 2, 2, 2, 5, 6};

    @Test
    public void whenFound() {
      int result = new ParallelIndexSearch<Integer>().findIndex(ARRAY, 5);
      assertThat(result, is(4));
  }

    @Test
    public void whenNotFound() {
        int result = new ParallelIndexSearch<Integer>().findIndex(ARRAY, 8);
        assertThat(result, is(-1));
  }
}