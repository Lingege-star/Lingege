package com.example.myapplication;

public interface ThreadPoolInterface {
    void executeTask(Runnable runnable);
    void removeTask();
}
