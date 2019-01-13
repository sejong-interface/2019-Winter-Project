package com.example.jkey2.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    int value=0; // 시간 설정
    TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mText=(TextView)findViewById(R.id.ac2tv);
        mHandler.sendEmptyMessage(0); // 앱 시작과 동시에 핸들러에 메세지 전달


    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            value--; // 초 카운트

            mText.setText(value/3600 + "시" + value/60 + "분" + value%60 + "초");

            // 메세지를 처리하고 또다시 핸들러에 메세지 전달 (1초 지연)
            mHandler.sendEmptyMessageDelayed(0,1000);
            if(value==0) {
                mHandler.removeMessages(0); // 핸들러 정지
            }
        }
    };

    class testcode{

    }
}
