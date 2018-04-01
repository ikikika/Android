package com.jitooi.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertTemperature( View view ){

        //Log.i("info", "Button pressed");

        EditText editText = (EditText) findViewById(R.id.editText);
        //Log.i("info", editText.getText().toString());

        String tempInC = editText.getText().toString();
        double tempInCDouble = Double.parseDouble(tempInC);

        double tempInF = ( tempInCDouble * 1.8 ) + 32;

        String tempInFString = String.format("%.2f", tempInF);

        //Log.i("Temp in F", tempInFString);

        Toast.makeText(this, tempInC + "deg C is "+tempInFString+" deg F.", Toast.LENGTH_LONG).show();




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
