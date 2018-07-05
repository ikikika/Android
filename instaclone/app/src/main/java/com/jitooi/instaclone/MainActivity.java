package com.jitooi.instaclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;

import com.parse.ParseAnalytics;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

    Boolean signUpModeActive = true;
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    ImageView logoImageView;
    RelativeLayout bgRelLayout;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if( i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            signUpClicked(view);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.loginTextView ){

            Button signUpButton = findViewById(R.id.signUpButton);

            if( signUpModeActive ){
                signUpModeActive = false;
                signUpButton.setText("Login");
                loginTextView.setText("Sign Up");
            } else {
                signUpModeActive = true;
                signUpButton.setText("Sign Up");
                loginTextView.setText("Log in");
            }

        } else if( view.getId() == R.id.logoImageView || view.getId() == R.id.bgRelLayout ){

            //not logintextview, maybe background or logo
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void signUpClicked(View view){

        if( usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("") ){
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
        } else {
            if( signUpModeActive ) {
                //sign up
                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //good
                            Toast.makeText(MainActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                //log in
                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if( user != null ){
                            Toast.makeText(MainActivity.this, "login ok", Toast.LENGTH_SHORT).show();
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instaclone");

        loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        logoImageView = findViewById(R.id.logoImageView);
        bgRelLayout = findViewById(R.id.bgRelLayout);

        logoImageView.setOnClickListener(this);
        bgRelLayout.setOnClickListener(this);

        passwordEditText.setOnKeyListener(this);

        if( ParseUser.getCurrentUser().getUsername() != null ){
            showUserList();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }



}
