package com.jitooi.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void start( View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView sumTextView = findViewById(R.id.sumTextView);

        goButton = findViewById(R.id.goButton);

        Random rand = new Random();

        int a = rand.nextInt( 21 );
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        int locationOfCorrectAnswer = rand.nextInt(4);

        for( int i = 0; i < 4; i++){
            if( i == locationOfCorrectAnswer ){
                answers.add( a+b );
            } else {
                int wrongAnswer = rand.nextInt( 41 );
                while( wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt( 41 );
                }
                answers.add( wrongAnswer );
            }

        }

    }
}
