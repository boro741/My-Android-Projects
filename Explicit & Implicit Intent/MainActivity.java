package com.example.pavanboro.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public  void onSendMessage(View view){

        EditText messageView = (EditText)findViewById(R.id.message);
        String messageText = messageView.getText().toString();


        /********* EXPLICIT INTENT **********************/
        Intent intent = new Intent(this, ReceiveMessageActivity.class);
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE,messageText);

        startActivity(intent);





        /********* IMPLICIT INTENT **********************/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE,messageText);

        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.c
    }
}
