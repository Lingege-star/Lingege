package com.example.myapplication2;

import java.util.concurrent.TimeUnit;

public abstract class IThreadPool {
    public void executeTask(Runnable runnable){

    }
    public void executeTimerTask(Runnable runnable, long firstStartTime, long interceTime
    , TimeUnit timeUnit){}
    public abstract void removeTask();
    public void removeTask(Runnable runnable){

    }
}
