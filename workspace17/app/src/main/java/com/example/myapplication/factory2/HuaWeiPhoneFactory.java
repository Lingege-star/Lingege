package com.example.myapplication.factory2;

public class HuaWeiPhoneFactory extends CreatePhoneFactory {
    @Override
  public   Phone createPhone() {
        return new HuaWeiPhone();
    }
}