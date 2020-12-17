package com.example.myapplication.factory2;

public class SunmPhoneFactory extends CreatePhoneFactory {
    @Override
  public   Phone createPhone() {
        return new SunmPhone();
    }
}