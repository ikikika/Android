package com.jitooi.multipleactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
=======
    public void goToNext( View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("age", 28);
        startActivity(intent);
    }

>>>>>>> 948fd7e... pass values from one activity to another using intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
