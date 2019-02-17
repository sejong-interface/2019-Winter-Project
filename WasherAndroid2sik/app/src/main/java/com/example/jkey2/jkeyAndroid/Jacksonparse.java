package com.example.jkey2.jkeyAndroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jkey2.myapplication.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static android.widget.Toast.LENGTH_LONG;

public class Jacksonparse extends AppCompatActivity
{
    TextView restTimeText;
    TextView timeText;
    String ParsetotalText;
    String RestTime;
    boolean Loopflag;
    Button timebt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jacksonparse);
        timebt = (Button) findViewById(R.id.timebt);
        timeText = (TextView) findViewById(R.id.timeText);
        restTimeText = (TextView) findViewById(R.id.resttime);
        final Toast RefreshToast = Toast.makeText(this, "새로고침 완료", LENGTH_LONG);
        final Toast LoopToast = Toast.makeText(this, "자동새로고침 완료", LENGTH_LONG);
        JacksonThread.start();
        Loopflag = true;
        final Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (Loopflag)
                {
                    JacksonThread.start();

                    try
                    {
                        RestTime = (JacksonThread.ParseMeasure);
                        ParsetotalText = JacksonThread.start();
                        timeText.setText(ParsetotalText);
                        //         restTimeText.setText();
                        LoopToast.show();
                        //mHandler.sendEmptyMessage(0);
                    } catch (Exception e)
                    {
                        restTimeText.setText("Loading Error");
                        timeText.setText("Loading Error");
                        break;
                    }
                }

            }
        });
        thread.start();
/*
        timebt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                JacksonThread.start();
                        try
                        {
                            RestTime = (JacksonThread.ParseMeasure);
                            ParsetotalText = JacksonThread.start();
                            timeText.setText(ParsetotalText);
                   //         restTimeText.setText();
                            RefreshToast.show();
                            //mHandler.sendEmptyMessage(0);
                        } catch (Exception e){
                            restTimeText.setText("Loading Error");
                            timeText.setText("Loading Error");
                        }
            }
        });
    }
    */
/*
    Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            restTimeText.setText(Integer.toString(RestTime / 3600) + "시" + Integer.toString(RestTime / 60) + "분" + Integer.toString(RestTime % 60) + "초");
            mHandler.sendEmptyMessageDelayed(0, 1000);
            RestTime--;
            if (RestTime < 0)
            {
                mHandler.removeMessages(0); // 핸들러 정지
            }
        }
    };
    */
    }
}