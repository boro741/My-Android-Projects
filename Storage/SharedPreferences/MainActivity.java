package com.example.pavanboro.sharedpreference;

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

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.pavanboro.sharedpreference", Context.MODE_PRIVATE);

        // Storing to sharedpreferenc
        sharedPreferences.edit().putString("username","pavanboro").apply();

        // Access Variable
        String username = sharedPreferences.getString("username","");

        Log.i("username",username); 
    }
}
