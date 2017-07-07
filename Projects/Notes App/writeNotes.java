package com.example.pavanboro.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

import static com.example.pavanboro.notes.MainActivity.notes;
import static com.example.pavanboro.notes.MainActivity.set;

public class writeNotes extends AppCompatActivity implements TextWatcher {

    EditText editText;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notes);

        editText = (EditText) findViewById(R.id.note);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        Log.i("noteId",String.valueOf(noteId));

        if(noteId != -1){
            // Setting the text from notes variable from MainActivity
            // Ex:  editText.setText(notes[0])

            editText.setText(notes.get(noteId));
        }

        // Any changes to editText updates it in notes
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        // Update notes
        // Ex: notes[0] = s ;
        MainActivity.notes.set(noteId,String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.pavanboro.notes", Context.MODE_PRIVATE);

        if(MainActivity.set == null){
            set = new HashSet<String>();
        } else {
            MainActivity.set.clear();
        }

        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
