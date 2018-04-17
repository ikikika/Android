package com.jitooi.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.jitooi.sharedpreferences", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("username", "ikikika").apply(); //first string is name of string, second string is value
        String username = sharedPreferences.getString("username", "no name"); // first is name, second string is default

        Log.i("This is username", username);
    }
}
