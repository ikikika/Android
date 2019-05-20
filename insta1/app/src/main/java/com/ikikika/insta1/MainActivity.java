package com.ikikika.insta1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.loginTextView){
            Log.i("Switch ", "was tapped");
        }
    }

    public void signUpClicked(View view){
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        if(usernameEditText.getText().toString().trim().length() == 0 || passwordEditText.getText().toString().trim().length() == 0){
            Toast.makeText(MainActivity.this, "Username and password required", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if( e == null){
                        Log.i("Signup", "Success");
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }


}
