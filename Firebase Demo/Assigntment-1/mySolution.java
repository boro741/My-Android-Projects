/*
    Create an EditText
    When a user clicks on Button 
    Send the data to the firebase
*/



package com.example.pavanboro.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button mFirebaseBtn;
    private EditText mtext;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mFirebaseBtn = (Button) findViewById(R.id.firebase_btn);
        mtext = (EditText) findViewById(R.id.sendText);

        final String text = mtext.getText().toString();

        // It mean pointing to root of database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Basics of storing data to DataBase
                /*
                 * 1. Create Child in root object
                 * 2. Assign some value to the child object
                 */
                final String text = mtext.getText().toString();

                mDatabase.child("Name").setValue(text);

            }
        });
    }
}
