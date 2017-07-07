package com.example.pavanboro.multiplefragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        /********** Fragments ********/
        FirstFrament firstFrament = new FirstFrament();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.firstLayout, firstFrament,firstFrament.getTag())
                .commit();


        SecondFragment secondFragment = new SecondFragment();
        manager.beginTransaction()
                .replace(R.id.secondLayout, secondFragment,secondFragment.getTag())
                .commit();
    }
}
