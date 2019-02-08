package com.example.jkey2.myapplication;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

public class jacksonparse extends AppCompatActivity {
    /* int value=100; // 시간 설정*/
    TextView mText;
    TextView cText;
    int Tid;
    String Tamount;
    String Ttime;
    String Tstate;

    Button bt;

    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jacksonparse);
        bt = (Button) findViewById(R.id.timebt);
        mText = (TextView) findViewById(R.id.ac2tv); // 텍스트뷰 연결
        cText = (TextView) findViewById(R.id.ac3tv);
        /* mHandler.sendEmptyMessage(0); // 앱 시작과 동시에 핸들러에 메세지 전달 */
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });

    }
private void request(){
        String title = "원격요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
    AlertDialog dialog = makeRequestDialog(title,message, titleButtonYes,titleButtonNo);
        dialog.show();
        cText.setText("원격데이터 요청중");
}

private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo){
    AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
    requestDialog.setTitle(title);
    requestDialog.setMessage(message);
    requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            RequestHandler handler = new RequestHandler();
            handler.sendEmptyMessageDelayed(0, 20);

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            URL url = new URL("http://192.168.123.103:8080/android");
                            model md = mapper.readValue(url, model.class);

                            Tid = md.getId();
                            Tamount=md.getAmountElectric();
                            Ttime = md.getTime();
                            Tstate = md.getState();

                            mText.setText(md.getTime());

                        }
                        catch (MalformedURLException e){
                            cText.setText("에러발생 다시 실행해 주십시오");
                            e.printStackTrace();

                        } catch (JsonParseException e) {
                            cText.setText("에러발생 다시 실행해 주십시오");
                            e.printStackTrace();

                        } catch (JsonMappingException e) {
                            cText.setText("에러발생 다시 실행해 주십시오");
                            e.printStackTrace();

                        } catch (IOException e) {
                            cText.setText("에러발생 다시 실행해 주십시오");
                            e.printStackTrace();

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            thread.start();




        }

    });
    requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            cText.setText("요청을 취소하였습니다");
        }
    });


    return requestDialog.show();
}


class RequestHandler extends Handler{
        public void handleMessage(Message msg){
            for(int k=0; k<10; k++){
                try{
                Thread.sleep(500);
                }
                catch (InterruptedException ex){
                    cText.setText("에러발생 다시 실행해 주십시오");
                }

                cText.setText("원격데이터 요청완료");

            }
        }
}


}