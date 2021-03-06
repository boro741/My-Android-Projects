/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  Boolean signUpModeActive = true;
  TextView changeSignUpModeView;

  @Override
  public void onClick(View view) {
    if(view.getId() == R.id.changeSignUpModeView){

      Button signupButton = (Button) findViewById(R.id.signupButton);

      if (signUpModeActive){

        signUpModeActive = false;

        signupButton.setText("Log in");
        changeSignUpModeView.setText("Or, Signup");
      } else {
        signUpModeActive = true;

        signupButton.setText("Sign Up");
        changeSignUpModeView.setText("Or, Login");

      }

    }
  }

  public  void signUp(View view){

    EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);



    // if (usernameEditText.getText().toString() == "" || passwordEditText.getText().toString() == "")
    /*
    *  In Java if u want to compare one string with another use matches.
    * */

    if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){

      Toast.makeText(this, "A username and password are required.",Toast.LENGTH_SHORT).show();

    } else {

      if (signUpModeActive){
        ParseUser user = new ParseUser();

        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("SignUp", "Successful");
          } else {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
        }
      });

      } else {

          ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
              if (user != null){

                Log.i("SignUp", "Login successful");

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

    changeSignUpModeView = (TextView) findViewById(R.id.changeSignUpModeView);

    changeSignUpModeView.setOnClickListener(this);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }





}