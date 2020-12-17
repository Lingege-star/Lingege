package com.example.myapplication2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool extends IThreadPool {

    private final ExecutorService executorService;
    private static SingleThreadPool singleThreadPool;

    private SingleThreadPool(){
        executorService = Executors.newSingleThreadExecutor();

    }
    public static synchronized SingleThreadPool getSingleTreadPool(){
        if(singleThreadPool==null){
            synchronized (SingleThreadPool.class){
                if(singleThreadPool==null){
                    singleThreadPool = new SingleThreadPool();

                }
            }
        }
        return singleThreadPool;
    }

    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if(executorService!=null){
            executorService.execute(runnable);
        }
    }

    @Override
    public void removeTask() {
        executorService.shutdown();
    }
}
