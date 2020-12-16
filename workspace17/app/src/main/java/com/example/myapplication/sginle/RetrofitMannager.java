package com.example.myapplication.sginle;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *    //以单例的方式封装Retrofit对象
 */
public class RetrofitMannager {
    private static RetrofitMannager mManager;
    private ApiService mApiService;


    private RetrofitMannager() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        // TODO  添加拦截器  okBuilder.addInterceptor();
        mApiService = new Retrofit.Builder().
                baseUrl(ApiService.BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                client(okBuilder.build()).build().
                create(ApiService.class);
    }

    public synchronized static RetrofitMannager getmManager() {
        if (mManager == null) {
            synchronized (RetrofitMannager.class) {
                if (mManager == null) {
                    mManager = new RetrofitMannager();
                }
            }
        }
        return mManager;
    }


    public ApiService getApiSercice() {
        return mApiService;
    }


}