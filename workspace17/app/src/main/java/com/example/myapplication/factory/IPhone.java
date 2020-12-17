package com.example.myapplication.factory;

import android.util.Log;

public class IPhone implements Phone {
    @Override
    public void makePhone() {
        Log.e("TAG", "制造苹果手机");
    }
}