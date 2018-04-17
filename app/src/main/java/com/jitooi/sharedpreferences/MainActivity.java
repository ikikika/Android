package com.jitooi.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import static com.jitooi.sharedpreferences.ObjectSerializer.serialize;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.jitooi.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("ashley");
        friends.add("bucky");
        friends.add("Claudia");

        try {
            sharedPreferences.edit().putString( "friendsList", ObjectSerializer.serialize(friends) ).apply();

            Log.i("serizlized friends list", ObjectSerializer.serialize(friends) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize( sharedPreferences.getString("friendsList", ObjectSerializer.serialize(new ArrayList<String>())) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("new friends", newFriends.toString() );
        /*
        //run once, after that if comment out this line, value will still be saves in shared preferences
        sharedPreferences.edit().putString("username", "ikikika").apply(); //first string is name of string, second string is value
        String username = sharedPreferences.getString("username", "no name"); // first is name, second string is default

        Log.i("This is username", username);
        */
    }
}
