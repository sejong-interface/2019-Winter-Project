package com.example.jkey2.jkeyAndroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jkey2.myapplication.R;

public class SettingRestCalculator extends AppCompatActivity
{

  String TotalRest;
  private SharedPreferences appData;
  private boolean saveSettingData;
  EditText EditT;
  Button Savebt;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting_rest_calculator);
    appData = getSharedPreferences("appData", MODE_PRIVATE);
    load();
    Savebt = (Button)findViewById(R.id.savebt);
    EditT = (EditText)findViewById(R.id.EditText1);

    if(saveSettingData){
      EditT.setText(TotalRest);

    }
    Savebt.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        save();
      }
    });
  }

  private void save(){
    SharedPreferences.Editor editor = appData.edit();
    editor.putBoolean("SAVE_Setting_DATA", true);
    editor.putString("Rest", EditT.getText().toString().trim());
    editor.apply();

  }
  private void load(){
    saveSettingData = appData.getBoolean("SAVE_Setting_DATA", false);
    TotalRest= appData.getString("Rest", "");

  }

}
