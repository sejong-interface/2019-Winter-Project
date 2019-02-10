package com.example.jkey2.jkeyAndroid;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JacksonThread
{
  static int ParseID=0;
  static String ParseAmount="nothing";
  static String ParseTime="nothing";
  static String ParseState="nothing";

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
            URL ParseUrl = new URL("http://172.30.1.55:8080/android");
            Model md = mapper.readValue(ParseUrl,Model.class);

            ParseID = md.getId();
            ParseAmount = md.getAmountElectric();
            ParseTime= md.getTime();
            ParseState = md.getState();

          } catch (MalformedURLException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = "error";
            ParseTime = "error";
            ParseState = "error";
          } catch (JsonParseException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = "error";
            ParseTime = "error";
            ParseState = "error";
          } catch (JsonMappingException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = "error";
            ParseTime = "error";
            ParseState = "error";
          } catch (IOException e)
          {
            e.printStackTrace();
            ParseID = 20;
            ParseAmount = "error";
            ParseTime = "error";
            ParseState = "error";
          }
        }
        catch (Exception ex){
          ex.printStackTrace();
          ParseID = 20;
          ParseAmount = "error";
          ParseTime = "error";
          ParseState = "error";
        }
      }
    });
    jacksonParseThread.start();

    return " 받은 ID:" + ParseID + "\n 받은 전력량:" + ParseAmount + "\n 받은 시간:" + ParseTime + "\n 받은 상태:" + ParseState;
  }
  public int ParseID(){
    return ParseID;
  }
  public  String ParseAmount(){
    return ParseAmount;
  }
  public String ParseTime(){
    return ParseTime;
  }
  public String ParseState(){
    return ParseState;
  }
}
