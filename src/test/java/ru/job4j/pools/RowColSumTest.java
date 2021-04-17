package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RowColSumTest {

    @Test
    public void testSync() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] sums = RowColSum.sum(matrix);
        int firstRowSum = 6;
        int firstColSum = 12;
        assertEquals(firstColSum, sums[0].getColSum());
        assertEquals(firstRowSum, sums[0].getRowSum());
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] sums = RowColSum.asyncSum(matrix);
        int firstRowSum = 6;
        int firstColSum = 12;
        assertEquals(firstColSum, sums[0].getColSum());
        assertEquals(firstRowSum, sums[0].getRowSum());
    }
    }
