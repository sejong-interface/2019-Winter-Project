package com.example.jkey2.jkeyAndroid;

import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class JacksonThread
{

  static long ParseID=0;
  static long ParseAmount=0;
  static String ParseMeasure;
  static String ParseState;
  public static String start()
  {
    Thread jacksonParseThread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try{
          ObjectMapper mapper = new ObjectMapper();
          try{
            URL ParseUrl = new URL("http://192.168.0.46:8080/android/1");
            ResultDto result = mapper.readValue(ParseUrl,ResultDto.class);

            WasherDto md = result.getItems().get(0);
            ParseID = md.getId();
            ParseAmount = md.getElectricPower();
            ParseMeasure = md.getMeasuredAt();
            ParseState = md.getState();

          } catch (MalformedURLException e)
          {
            e.printStackTrace();

          } catch (JsonParseException e)
          {
            e.printStackTrace();


          } catch (JsonMappingException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = 20;
          } catch (IOException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = 20;
          }
        }
        catch (Exception ex){
          ex.printStackTrace();
          ParseID = 20;
          ParseAmount = 20;
        }
      }
    });
    jacksonParseThread.start();

    return "parse:" + ParseID + "\nParseAmount:" + ParseAmount + "\nParseMeasure:" + ParseMeasure +"\nParseState:" + ParseState;
  }

}
