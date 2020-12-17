package com.example.myapplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchduleTheadPool implements ThreadPoolInterface{

    private final ScheduledExecutorService scheduledExecutorService;

    public SchduleTheadPool(){
        //定时
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void executeTask(Runnable runnable) {
        if (scheduledExecutorService!=null)
            return;
        scheduledExecutorService.schedule(runnable,2, TimeUnit.SECONDS);
    }

    @Override
    public void removeTask() {
        if (scheduledExecutorService!=null)
        scheduledExecutorService.shutdown();
    }
}
