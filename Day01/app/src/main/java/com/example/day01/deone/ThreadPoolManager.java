package com.example.day01.deone;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private  ThreadPoolExecutor mExecutor;
    private static ThreadPoolManager mManager;
//懒汉式
    private ThreadPoolManager(){
        mExecutor = new ThreadPoolExecutor(5, 20, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory()
        );
    }

    public synchronized static ThreadPoolManager getManager(){
        if(mManager!=null){
            synchronized (ThreadPoolManager.class){
                if(mManager!=null){
                    mManager = new ThreadPoolManager();
                }
            }
        }
      return mManager;
    }
    public void executeTask(Runnable runnable){
        if(mExecutor==null)
            return;
        mExecutor.execute(runnable);
    }
    public void removeTask(Runnable runnable){
        if(mExecutor==null)
            return;
        mExecutor.remove(runnable);
    }
}
