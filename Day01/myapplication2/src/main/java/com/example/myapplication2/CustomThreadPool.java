package com.example.myapplication2;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPool extends IThreadPool{

    private static CustomThreadPool customThreadPool;
    private final ThreadPoolExecutor executor;

    private CustomThreadPool(){
        executor = new ThreadPoolExecutor(5, 30, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());
    }
    public synchronized static CustomThreadPool getThreadPool(){
        if(customThreadPool==null){
           synchronized (CustomThreadPool.class){
               if(customThreadPool==null){
                   customThreadPool = new CustomThreadPool();
               }
           }
        }
        return customThreadPool;
    }



    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if(executor!=null){
            executor.execute(runnable);
        }
    }
    @Override
    public void removeTask() {

    }
    @Override
    public void removeTask(Runnable runnable) {
        super.removeTask(runnable);
        if(executor!=null){
            executor.remove(runnable);
        }
    }
}
