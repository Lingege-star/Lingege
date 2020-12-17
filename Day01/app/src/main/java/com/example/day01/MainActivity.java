package com.example.day01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day01.fartey.Phone;
import com.example.day01.fartey.PhoneFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button but_deone;
    private Button but_fartey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        but_deone = (Button) findViewById(R.id.but_deone);
        but_fartey = (Button) findViewById(R.id.but_fartey);

        but_deone.setOnClickListener(this);
        but_fartey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_deone:

                break;
            case R.id.but_fartey:
                Phone miPhone = PhoneFactory.createPhone("miPhone");
                miPhone.makePhone();
                Phone iPhone = PhoneFactory.createPhone("iPhone");
                iPhone.makePhone();
                break;
        }
    }
}
