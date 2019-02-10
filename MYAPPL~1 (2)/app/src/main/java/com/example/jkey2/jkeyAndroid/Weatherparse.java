package com.example.jkey2.jkeyAndroid;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.jkey2.myapplication.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Weatherparse extends AppCompatActivity {
  TextView weatherView;
  int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weatherparse);
        weatherView = (TextView)findViewById(R.id.weatherV);
        WeatherConnection weatherConnection = new WeatherConnection();
        AsyncTask<String, String, String> result = weatherConnection.execute("" , "");
        try {
          String msg = result.get();
          weatherView.setText(msg.toString());
        } catch (Exception e) {
          weatherView.setText("Error");
        }
    }

    public class WeatherConnection extends AsyncTask<String, String, String>{
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... params) {
          try{
            String path = "https://news.nate.com/weather?areaCode=11B10101";
            String total;
            Document document = Jsoup.connect(path).get();

            Elements todayelements = document.select("p.celsius");
            Elements weekElements = document.select("p.te");
            Elements dayElements = document.select("p.day");
            Elements washindex = document.select("td.life_num");
            Elements washindexTXT= document.select("td:containsOwn(요)");

            Element washId = washindex.get(0);
            String washText = washId.text();
            Element washIdText = washindexTXT.get(0);
            String washindexText = washIdText.text();
            Element todayweather = todayelements.get(0);
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
            StringBuffer sb = new StringBuffer();
            sb.append("오늘의 온도: "+ todayWtext);
            sb.append("\n오늘의 빨래지수: "+washText+" "+washindexText+"\n");
            sb.append("\n주간날씨\n"+ dayText[0]+"의 날씨 : "+weekText[0] +"\n");
            sb.append(dayText[1]+"의 날씨 : "+weekText[1] +"\n");
            sb.append(dayText[2]+"의 날씨 : "+weekText[2] +"\n");
            sb.append(dayText[3]+"의 날씨 : "+weekText[3] +"\n");
            sb.append(dayText[4]+"의 날씨 : "+weekText[4] +"\n");
            sb.append(dayText[5]+"의 날씨 : "+weekText[5] +"\n");
            total = sb.toString();

            return total;

            }catch (Exception e){

            weatherView.setText("Error");
            e.printStackTrace();
            }
            return null;
        }
    }
}
