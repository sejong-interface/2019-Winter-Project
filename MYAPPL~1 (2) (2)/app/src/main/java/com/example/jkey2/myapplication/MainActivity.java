package com.example.jkey2.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.bt1);        // 잭슨
     /*   Button refbt = (Button) findViewById(R.id.refresh);
        Button setPro = (Button) findViewById(R.id.progressset);
       */
        Button weather = (Button) findViewById(R.id.weatherbutton); // 날씨

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wIntent = new Intent(getApplicationContext(), weatherparse.class);
                startActivity(wIntent);
            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), jacksonparse.class);
                startActivity(intent);


            }
        });

        /*
        progressBar = (ProgressBar)findViewById(R.id.p_Bar);

        setPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(80);
            }
        });




        refbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            showProgressDialog();

            }
        });
    }


    private void showProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("데이터 확인중");
        dialog.show();
    }

       */
    }
}
















