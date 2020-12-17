package com.example.myapplication2;

import android.util.JsonReader;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchduleThreadPool extends IThreadPool{

    private final ScheduledExecutorService sExecutorService;
    private static SchduleThreadPool schduleThreadPool;

    @Override
    public void executeTimerTask(Runnable runnable, long firstStartTime, long interceTime, TimeUnit timeUnit) {
        super.executeTimerTask(runnable, firstStartTime, interceTime, timeUnit);
        if(sExecutorService!=null){
            sExecutorService.scheduleWithFixedDelay(runnable,firstStartTime,interceTime,timeUnit);
        }

    }
    private SchduleThreadPool(){
        sExecutorService = Executors.newSingleThreadScheduledExecutor();

    }
    public synchronized static  SchduleThreadPool getmSchduleTreadPool(){
        if(schduleThreadPool==null){
            synchronized (SchduleThreadPool.class){
                if(schduleThreadPool==null){
                    schduleThreadPool = new SchduleThreadPool();

                }
            }
        }
        return schduleThreadPool;
    }

    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if(sExecutorService!=null){
            sExecutorService.execute(runnable);
        }
    }

    @Override
    public void removeTask() {

    }
}
