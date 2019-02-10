package com.example.jkey2.jkeyAndroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.jkey2.myapplication.R;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Jackson = (Button) findViewById(R.id.jacksonbt);
        Button Weather = (Button) findViewById(R.id.weatherbutton);

        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wIntent = new Intent(getApplicationContext(), weatherparse.class);
                startActivity(wIntent);
            }
        });
        Jackson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), jacksonparse.class);
                startActivity(intent);
            }
        });
    }
}


















