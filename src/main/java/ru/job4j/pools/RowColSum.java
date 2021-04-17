package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] container = new Sums[matrix.length * 2];

        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            container[i] = new Sums(rowSum, colSum);
        }
        return container;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] container = new Sums[matrix.length * 2];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getSum(matrix, i));
        }
        for (int j = 0; j < futures.size(); j++) {
            container[j] = futures.get(j).get();
        }
        return container;
    }

    public static CompletableFuture<Sums> getSum(int[][] matrix, int start) {
        return CompletableFuture.supplyAsync(() -> {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowSum += matrix[start][j];
                colSum += matrix[j][start];
            }
            return new Sums(rowSum, colSum);
        });
    }
}

