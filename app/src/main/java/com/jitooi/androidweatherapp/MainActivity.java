package com.jitooi.androidweatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView resultTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextview = findViewById(R.id.resultTextview);
    }

    public void getWeather( View view){
        DownloadTask task = new DownloadTask();
        task.execute("http://openweathermap.org/data/2.5/weather?q="+ editText.getText().toString() +"&appid=b6907d289e10d714a6e88b30761fae22");

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while ( data != -1 ){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        // write some code when doInBackground is finished,
        // this should not touch anything from UI
        // String s is what the result from doInBackground
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Log.i("JSON", s);
            try{
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather Content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                String message = "";

                for (int i=0; i<arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    //Log.i("main", jsonPart.getString("main"));
                    //Log.i("description", jsonPart.getString("description"));

                    if( !main.equals("") && !description.equals("")  ){
                        message += main + ": " + description + "\r\n";
                    }
                }

                if( !message.equals("") ){
                    resultTextview.setText(message);
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
