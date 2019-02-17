package com.example.jkey2.jkeyAndroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.example.jkey2.myapplication.R;

public class ServerConnect extends AppCompatActivity implements View.OnClickListener {

  TextView tvIsConnected, tvResponse;
  EditText etName,etCountry,etTwitter;
  Button btnPost;
  Person person;
  static    String strJson = "";
  Spinner spinner;
  String SpinnerText;
  String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_server_connect);

    // get reference to the views
    tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);
    etName = (EditText) findViewById(R.id.etName);
    etCountry = (EditText) findViewById(R.id.etCountry);
    etTwitter = (EditText) findViewById(R.id.etTwitter);
    btnPost = (Button) findViewById(R.id.btnPost);
    tvResponse = (TextView) findViewById(R.id.tvResponse);
    spinner = (Spinner) findViewById(R.id.Spinner);

    ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_dropdown_item_1line);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
      {
        Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
        SpinnerText = days[position];
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent)
      {

      }
    });
    // check if you are connected or not
    if(isConnected()){
      tvIsConnected.setBackgroundColor(0xFF00CC00);
      tvIsConnected.setText("You are conncted");
    }
    else{
      tvIsConnected.setText("You are NOT conncted");
    }

    // add click listener to Button "POST"
    btnPost.setOnClickListener(this);

  }

  public static String POST(String url, Person person){
    InputStream is = null;
    String result = "";
    try {
      URL urlCon = new URL(url);
      HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();

      String json = "";

      // build jsonObject
      JSONObject jsonObject = new JSONObject();
      jsonObject.accumulate("name", person.getName());
      jsonObject.accumulate("country", person.getCountry());
      jsonObject.accumulate("twitter", person.getTwitter());


      // convert JSONObject to JSON to String
      json = jsonObject.toString();

      // ** Alternative way to convert Person object to JSON string usin Jackson Lib
      // ObjectMapper mapper = new ObjectMapper();
      // json = mapper.writeValueAsString(person);

      // Set some headers to inform server about the type of the content
      httpCon.setRequestProperty("Accept", "application/json");
      httpCon.setRequestProperty("Content-type", "application/json");

      // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
      httpCon.setDoOutput(true);
      // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
      httpCon.setDoInput(true);

      OutputStream os = httpCon.getOutputStream();
      os.write(json.getBytes("euc-kr"));
      os.flush();
      // receive response as inputStream
      try {
        is = httpCon.getInputStream();
        // convert inputstream to string
        if(is != null)
          result = convertInputStreamToString(is);
        else
          result = "Did not work!";
      }
      catch (IOException e) {
        e.printStackTrace();
      }catch (NullPointerException e){
        e.printStackTrace();
      }
      finally {
        httpCon.disconnect();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
      Log.d("Nullpoint", e.getLocalizedMessage());
    } catch (Exception e) {
      Log.d("InputStream", e.getLocalizedMessage());
    }

    return result;
  }

  public boolean isConnected(){
    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected())
      return true;
    else
      return false;
  }
  @Override
  public void onClick(View view) {

    switch(view.getId()){
      case R.id.btnPost:
        if(!validate())
          Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
        else {
          // call AsynTask to perform network operation on separate thread
          HttpAsyncTask httpTask = new HttpAsyncTask(ServerConnect.this);
          httpTask.execute("http://hmkcode.appspot.com/jsonservlet", SpinnerText, etCountry.getText().toString(), etTwitter.getText().toString());
        }
        break;
    }

  }
  private class HttpAsyncTask extends AsyncTask<String, Void, String> {

    private   ServerConnect mainAct;

    HttpAsyncTask(ServerConnect mainActivity) {
      this.mainAct = mainActivity;
    }
    @Override
    protected String doInBackground(String... urls) {

      person = new Person();
      person.setName(SpinnerText);
      person.setCountry(urls[2]);
      person.setTwitter(urls[3]);

      return POST(urls[0],person);
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      strJson = result;
      mainAct.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          Toast.makeText(mainAct, "Received!", Toast.LENGTH_LONG).show();
          try {
            JSONArray json = new JSONArray(strJson);
            mainAct.tvResponse.setText(json.toString(1));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      });

    }
  }

  private boolean validate(){
    if(etName.getText().toString().trim().equals(""))
      return false;
    else if(etCountry.getText().toString().trim().equals(""))
      return false;
    else if(etTwitter.getText().toString().trim().equals(""))
      return false;
    else
      return true;
  }
  private static String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
      result += line;

    inputStream.close();
    return result;

  }

}

