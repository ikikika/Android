package com.jitooi.multipleactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public void goBack( View view){
        //this code will generate a new screen everytime, instead of bring us back
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
        //use this code to bring us back
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int age = intent.getIntExtra("age", 0);

        Toast.makeText(this, Integer.toString(age), Toast.LENGTH_SHORT).show();
    }
}
