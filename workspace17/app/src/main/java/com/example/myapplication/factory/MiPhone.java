package com.example.myapplication.factory;

import android.util.Log;

public class MiPhone  implements  Phone{

    @Override
    public void makePhone() {
        Log.e("TAG","制造小米手机");
    }
}