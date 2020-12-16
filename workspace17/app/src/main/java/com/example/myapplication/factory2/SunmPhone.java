package com.example.myapplication.factory2;

import android.util.Log;

public class SunmPhone implements  Phone {
    @Override
    public void makePhone() {
        Log.e("TAG","制造三星Phone");
    }
}