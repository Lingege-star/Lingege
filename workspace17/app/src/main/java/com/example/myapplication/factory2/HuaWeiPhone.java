package com.example.myapplication.factory2;

import android.util.Log;

public class HuaWeiPhone implements  Phone{
    @Override
    public void makePhone() {
        Log.e("TAG","制造华为Phone");
    }
}