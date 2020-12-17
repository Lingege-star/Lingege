package com.example.myapplication2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Day_01_3 extends AppCompatActivity implements View.OnClickListener {

    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_01_3);
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
                ThreadPoolFactory.getThreadPool(ThreadPoolFactory.SCHDULE_THREADPOOL)
                        .executeTask(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG","可选择线程池样式");
                            }
                        });
                break;
        }
    }
}
