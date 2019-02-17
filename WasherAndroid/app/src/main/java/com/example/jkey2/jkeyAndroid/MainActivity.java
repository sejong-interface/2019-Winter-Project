package com.example.jkey2.jkeyAndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jkey2.myapplication.R;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Jackson = (Button)findViewById(R.id.jacksonbt);
        Button Weather = (Button)findViewById(R.id.weatherbutton);
        Button Restcalculate = (Button)findViewById(R.id.restcalculatebutton);
        Toast Errortoast = Toast.makeText(this, "Hello",Toast.LENGTH_LONG);

        try{
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d("디바이스 토큰 :", token);
        }catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "Device Token Error!",Toast.LENGTH_LONG).show();
        }

        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wIntent = new Intent(getApplicationContext(), Weatherparse.class);
                startActivity(wIntent);
            }
        });

        Jackson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Jintent = new Intent(getApplicationContext(), Jacksonparse.class);
                startActivity(Jintent);
            }
        });

        Restcalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent RIntent = new Intent(getApplicationContext(), SettingRestCalculator.class);
                startActivity(RIntent);
            }
        });
    }
}