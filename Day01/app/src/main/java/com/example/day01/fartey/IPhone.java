package com.example.day01.fartey;

import android.util.Log;

public class IPhone implements Phone{

    @Override
    public void makePhone() {
        Log.e("TAG","制造苹果手机");
    }
}
