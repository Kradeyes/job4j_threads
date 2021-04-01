package ru.job4j;

public final class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
            }
        return inst;
    }

    private DCLSingleton() {
    }
}