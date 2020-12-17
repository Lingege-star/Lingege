package com.example.myapplication2;

public class ThreadPoolFactory {
    public static final int CUSTOM_THREADPOOL=0;
    public static final int SCHDULE_THREADPOOL=1;
    public static final int SINGLE_THREADPOOL=2;
    public static IThreadPool getThreadPool(int type){
        switch (type){
            case CUSTOM_THREADPOOL:
                return CustomThreadPool.getThreadPool();
            case SCHDULE_THREADPOOL:
                return SchduleThreadPool.getmSchduleTreadPool();
            case SINGLE_THREADPOOL:
                return SingleThreadPool.getSingleTreadPool();
        }
        return null;
    }
}
