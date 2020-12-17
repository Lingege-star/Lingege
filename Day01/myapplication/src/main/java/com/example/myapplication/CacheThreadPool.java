package com.example.myapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadPool implements ThreadPoolInterface{

    private  ExecutorService executorService;

    public CacheThreadPool(){
        //缓存
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void executeTask(Runnable runnable) {
        if(executorService==null){
            return;
        }
        executorService.execute(runnable);
    }

    @Override
    public void removeTask() {
        if (executorService==null)
            return;
        executorService.shutdown();
    }
}
