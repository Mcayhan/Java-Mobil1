package com.example.myapplication;

public class Counter {

    private int unsafeCount = 0;
    private int safeCount = 0;

    //unsafe: race condition. multiple threads can access
    public void incrementUnsafe() {
        unsafeCount++;
    }

    // safe: only one thread can enter this method
    public synchronized void incrementSafe() {
        safeCount++;
    }

    public int getUnsafeCount() {
        return unsafeCount;
    }

    public int getSafeCount() {
        return safeCount;
    }
}
