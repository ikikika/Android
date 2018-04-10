package com.jitooi.android_json_demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String>{

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
<<<<<<< HEAD
=======

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

            } catch (Exception e){
                e.printStackTrace();
            }

        }
>>>>>>> 0b350ba... accessing items in json object
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
