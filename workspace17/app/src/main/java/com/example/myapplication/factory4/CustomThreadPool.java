package com.example.myapplication.factory4;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//对外提供自定义线程池对象： 场景： 耗时操作
public class CustomThreadPool extends IThreadPool {
    private static CustomThreadPool mThreadPool;
    private ThreadPoolExecutor mExecutor;

    private CustomThreadPool() {
        mExecutor = new ThreadPoolExecutor(5, 30, 30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());

    }

    public synchronized static CustomThreadPool getThreadPool() {
        if (mThreadPool == null) {
            synchronized (CustomThreadPool.class) {
                if (mThreadPool == null) {
                    mThreadPool = new CustomThreadPool();
                }
            }
        }
        return mThreadPool;
    }


    @Override
    public void executeTask(Runnable runnable) {
        super.executeTask(runnable);
        if (mExecutor != null) {
            mExecutor.execute(runnable);
        }

    }

    @Override
    public void removeTask() {

    }


    @Override
    public void removeTask(Runnable runnable) {
        if (mExecutor != null) {
            mExecutor.remove(runnable);
        }
    }
}