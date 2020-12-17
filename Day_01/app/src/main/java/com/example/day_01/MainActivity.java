package com.example.day_01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn_get1;
    private Button btn_get2;
    private Button btn_post1;
    private Button btn_post2;
    private Button btn_postString;
    private OkHttpClient mclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //推荐让OkHttpClient保持单例
        mclient = new OkHttpClient();
        initView();
    }

    private void initView() {
        btn_get1 = (Button) findViewById(R.id.btn_get1);
        btn_get2 = (Button) findViewById(R.id.btn_get2);
        btn_post1 = (Button) findViewById(R.id.btn_post1);
        btn_post2 = (Button) findViewById(R.id.btn_post2);

        btn_get1.setOnClickListener(this);
        btn_get2.setOnClickListener(this);
        btn_post1.setOnClickListener(this);
        btn_post2.setOnClickListener(this);
        btn_postString = (Button) findViewById(R.id.btn_postString);
        btn_postString.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get1://get 同步
                execute();
                break;
            case R.id.btn_get2://get 异步
                enqueue();
                break;
            case R.id.btn_post1://post 同步
                postExecute();
                break;
            case R.id.btn_post2://post 异步
                postEnqueue();
                break;
            case R.id.btn_postString://post String字符串
                postString();
                break;
        }
    }
    //java中的调用方法
    //普通方法：对象.方法() 对象调用方法
    //静态方法：类名.方法() 类名调用方法

    //同一个类中，方法名相同，参数列表不同 --- 重载
    //子类重新复写父类的方法 --- 重写

    //post String字符串
    private void postString() {
        //MediaType：媒体类型，网络交互的数据都是媒体(文字、图片、音视频...)
        //text:文本
        //audio：音频
        //video：视频
        //image:图片
        //二进制流：application/octet-stream
        //创建媒体类型，进行解析 文本类型，编码类型
        MediaType type = MediaType.parse("text/x-markdown; charset=utf-8");
        //创建请求体，参数为媒体类型，传递的内容
        RequestBody body = RequestBody.create(type, "没人发现");
        Request request = new Request.Builder()
                .post(body)
                .url("https://api.github.com/markdown/raw")
                .build();
//        new OkHttpClient()
                mclient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: " + response.body().string());
                    }
                });

    }

    //post 异步
    private void postEnqueue() {
        //请求体中添加参数
        RequestBody body = new FormBody.Builder()
                .add("username", "xts")
                .add("password", "123456")
                .add("repassword", "123456")
                .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url("https://www.wanandroid.com/user/register")
                .post(body)
                .build();
//        new OkHttpClient()
                 mclient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "onResponse: " + response.body().string());
                    }
                });
        Log.d(TAG, "postEnqueue: 执行到这里");
    }

    //post 同步
    private void postExecute() {
        //client.newCall().enqueue(异步)/excute(同步)
        //开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //username：用户名
                //password：密码
                //repassword：确认密码
                //addEncoded()：添加参数，需要编码，参数有中文时使用
                //add参数没有中文时使用
                //因为RequestBody()方法要重写里面的方法，不方便，使用RequestBody()的子类来创建请求体
                //请求体中添加参数
                RequestBody body = new FormBody.Builder()
                        .add("username", "xts")
                        .add("password", "123456")
                        .add("repassword", "123456")
                        .build();
                Request request = new Request.Builder()
                        .post(body)//指定post请求方式，参数RequestBody请求体
                        .url("https://www.wanandroid.com/user/register")
                        .build();
                try {
                    Response response = new OkHttpClient()
                            .newCall(request)
                            .execute();
                    Log.d(TAG, "run: " + response.body().string());
                    Log.d(TAG, "run: 执行到这里");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //get 异步
    private void enqueue() {
        //client.newCall().enqueue(异步)/excute(同步)
        //请求对象
        Request request = new Request.Builder()
                .url("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/3")
                .build();
//        new OkHttpClient.Builder()
//                .build()//返回的就是OkhttpClient对象
                 //使用成员变量
        Call call = mclient.newCall(request);
        //异步请求方式，不会阻塞当前线程
               call.enqueue(new Callback() {
                    @Override//访问网络失败时
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        //toString是将异常中的内容用字符串形式表现出来
                    }

                    @Override//访问网络成功时
                    public void onResponse(Call call, Response response) throws IOException {

                        //打印当前线程的名字 current当前的
                        Log.d(TAG, "onResponse: "+Thread.currentThread().getName());
                        //string是将解析到的流变成字符串
                        Log.e(TAG, "onResponse: " + response.body().string());
                        //response.body().string()只能调用一次
                        //异常为：IllegalStateException: closed 非法状态异常 已经关闭
                        //response.body()底层是一个io流，使用完了之后就关闭
//                        Log.e(TAG, "onResponse: " + response.body().string());

                        //什么是线程
                        //主线程和子线程的区别
                        //主线程可以刷新UI，主线程的名字：main
                        //子线程，名字随意
                        //回调方法时子线程，如果要刷新界面，切换到主线程
                        //切换主线程，底层都是handler
                        //handler
                        //runOnUiThread
                        //view.post()
                        /*
                        btn_get2.post(new Runnable() {
                            @Override
                            public void run() {
                                //主线程
                            }
                        });
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //主线程
                            }
                        });
                         */
                    }
                });
        //每一个Call(其实是RealCall)，只能执行一次，否则会报异常
        //异常为IllegalStateException: Already Executed 非法状态异常：已经执行
        /*
        call.enqueue(new Callback() {
            @Override//访问网络失败时
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.toString());
                //toString是将异常中的内容用字符串形式表现出来
            }

            @Override//访问网络成功时
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string());
                //string是将解析到的流变成字符串
            }
        });
         */
        Log.d(TAG, "enqueue: 执行到这里");
    }

    //get 同步
    //如果没有添加网络权限，会崩，报异常
    //异常内容
    //SecurityException: Permission denied (missing INTERNET permission?)
    private void execute() {
        //创建OKhttpClient对象
        //创建request对象
        //OKhttpClient对象.newCall(request)
        //调用enqueue(回调对象)

        //链式调用
        //client.newCall().enqueue(异步)/excute(同步)
//        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()//指定请求方式，默认是get
                .url("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/3")
                .build();
//        final Call call = client.newCall(request);
        final Call call = mclient.newCall(request);
        //开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步方法，会阻塞当前线程
                    Response response = call.execute();
                    Log.d(TAG, "run: " + response.body().string());
                    Log.d(TAG, "run: " + "执行到这里");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}