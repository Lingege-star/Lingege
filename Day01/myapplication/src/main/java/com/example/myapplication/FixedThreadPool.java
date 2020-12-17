package com.example.myapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPoolInterface{

    private final ExecutorService executorService;

    public FixedThreadPool(){
        //定长
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (executorService==null)
            return;
        executorService.execute(runnable);
    }

    @Override
    public void removeTask() {
        if (executorService==null)
            return;
        executorService.shutdown();
    }
}
