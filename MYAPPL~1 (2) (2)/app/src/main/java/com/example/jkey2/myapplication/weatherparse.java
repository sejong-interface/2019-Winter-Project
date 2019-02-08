package com.example.jkey2.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class weatherparse extends AppCompatActivity {
TextView weatherView;
TextView dustView;
Button weatherButton;
int i;
int k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherparse);
        weatherView = (TextView)findViewById(R.id.weatherV);





                    WeatherConnection weatherConnection = new WeatherConnection();

                    AsyncTask<String, String, String> result = weatherConnection.execute("", "");

                    System.out.println("RESULT");

                    try {
                        String msg = result.get();
                        System.out.println(msg);

                        weatherView.setText(msg.toString());


                    } catch (Exception e) {

                    }




    }




    public class WeatherConnection extends AsyncTask<String, String, String>{

        // 백그라운드에서 작업하게 한다
        @Override
        protected String doInBackground(String... params) {


            // Jsoup을 이용한 날씨데이터 Pasing하기.
            try{

                String path = "https://news.nate.com/weather?areaCode=11B10101";

                Document document = Jsoup.connect(path).get();

                Elements todayelements = document.select("p.celsius"); // <p class="celsius"
                Elements weekElements = document.select("p.te"); // <p class="te"
                Elements dayElements = document.select("p.day"); // <p class="day
                Elements washindex = document.select("td.life_num");
                Elements washindexTXT= document.getElementsContainingOwnText("요");

                System.out.println("@@@오늘의 날씨 엘리먼트: "+ todayelements);
                System.out.println("\n@@@주간 날씨 엘리먼트: "+ weekElements);
                System.out.println("\n@@@주간 날짜 엘리먼트: "+ dayElements);
                System.out.println("\n@@@빨래지수 엘리먼트: "+ washindex);
                System.out.println("\n@@@빨래지수 코멘트 엘리먼트" + washindexTXT) ;

                Element washId = washindex.get(0);
                Element washIdText = washindexTXT.get(0);
                String washText = washId.text();
                String washindexText = washIdText.text();
                Element todayweather = todayelements.get(1);
                String todayWtext = todayweather.text();

                Element[] weekweather  = new Element[10];
                Element[] dayget = new Element[10];
                String[] weekText  = new String[10];
                String[] dayText = new String[10];
                for(i=0; i<6; i++){
                    weekweather[i] = weekElements.get(i);

                    dayget[i] = dayElements.get(i);

                    weekText[i] = weekweather[i].text();

                    dayText[i] = dayget[i].text();
                    }



                    return "오늘의 온도 : " + todayWtext + "\n" +
                            "\n"+ "오늘의 빨래지수: " + washText + " " + washindexText+ "\n" +
                            "\n" + "주간날씨" + "\n" +
                            "\n" + dayText[0] + "의 온도 : " + weekText[0]+"\n" +
                            "\n" + dayText[1] + "의 온도 : " + weekText[1]+"\n" +
                            "\n"+ dayText[2] + "의 온도 : "  +weekText[2]+"\n" +
                            "\n"+ dayText[3] + "의 온도 : "  +weekText[3]+"\n" +
                            "\n"+ dayText[4] + "의 온도 : "  +weekText[4]+"\n" +
                            "\n"+ dayText[5] + "의 온도 : "  +weekText[5];














                /*
                // URL접속하여 HTML 가져오기.
                URL url = new URL("http://weather.naver.com/rgn/townWetr.nhn?naverRgnCd=09650510");

                System.out.println("Call target URL try....");

                InputStream inputStream = url.openStream();
                Scanner scanner = new Scanner(inputStream, "UTF-8");

                 while (true){
                    try{
                        System.out.println(scanner.nextLine());
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }
                }
                */
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
