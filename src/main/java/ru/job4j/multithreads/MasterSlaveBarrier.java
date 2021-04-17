package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private boolean master = true;

    public synchronized void tryMaster() {
    while (!master) {
        try {
            this.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    }

    public synchronized void trySlave() {
        while (master) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        master = false;
        this.notify();
    }

    public synchronized void doneSlave() {
        master = true;
        this.notify();
    }
}
