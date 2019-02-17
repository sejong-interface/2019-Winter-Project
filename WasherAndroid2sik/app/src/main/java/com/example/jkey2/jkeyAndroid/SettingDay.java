package com.example.jkey2.jkeyAndroid;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

public class SettingDay implements Serializable
{
  private String TR;

  public String getTR(String totalRest)
  {
    return TR;
  }
  public String setTR(String TR){
    return this.TR = TR;
  }
}
