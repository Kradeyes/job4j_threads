package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final static int MAXSIZE = 10;
    private  T[] array;
    private int leftBound;
    private int rightBound;
    private  T obj;

    public ParallelIndexSearch(T[] array, int leftBound, int rightBound, T obj) {
        this.array = array;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.obj = obj;
    }

    public ParallelIndexSearch(T[] array, T obj) {
        this.array = array;
        this.leftBound = 0;
        this.rightBound = array.length - 1;
        this.obj = obj;
    }

    public ParallelIndexSearch() {
    }

    private Integer linearSearch() {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(obj)) {
              result = i;
              break;
            }
        }
        return result;
    }

    public Integer findIndex(T[] array, T obj) {
        return new ForkJoinPool().invoke(new ParallelIndexSearch<>(array, obj));
    }

    @Override
    protected Integer compute() {
        final int result;
        if (rightBound - leftBound + 1 <= MAXSIZE) {
            result = linearSearch();
        } else {
            int mid = (leftBound + rightBound) / 2;
            ParallelIndexSearch<T> leftPart = new ParallelIndexSearch<>(array, leftBound, mid, obj);
            ParallelIndexSearch<T> rightPart = new ParallelIndexSearch<>(array, mid + 1, rightBound, obj);
            leftPart.fork();
            rightPart.fork();
            final int leftResult = leftPart.join();
            final int rightResult = rightPart.join();
            result = Math.max(leftResult, rightResult);
        }
        return result;
    }
}
