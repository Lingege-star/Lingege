package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Day01_2 extends AppCompatActivity implements View.OnClickListener {

    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day01_2);
        initView();
    }

    private void initView() {
        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                ThreadPoolFactory
                        .getData(ThreadPoolFactory.CACHE_THREADPOOL)
                        .executeTask(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG","这是线程");
                            }
                        });

                break;
        }
    }
}
