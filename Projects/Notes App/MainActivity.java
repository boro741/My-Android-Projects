package com.example.pavanboro.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();;
    static ArrayAdapter arrayAdapter;
    static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.pavanboro.notes", Context.MODE_PRIVATE);

        set = sharedPreferences.getStringSet("notes",null);

        notes.clear();

        if(set != null){

            notes.addAll(set);

        } else {
            notes.add("Example Page");

            set = new HashSet<String>();

            set.addAll(notes);
            sharedPreferences.edit().putStringSet("notes",set).apply();
        }


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),writeNotes.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                notes.remove(position);

                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.example.pavanboro.notes", Context.MODE_PRIVATE);

                                if(set == null){
                                    set = new HashSet<String>();
                                } else {
                                    set.clear();
                                }

                                set.addAll(notes);
                                arrayAdapter.notifyDataSetChanged();

                                 sharedPreferences.edit().remove("notes").apply();
                                sharedPreferences.edit().putStringSet("notes",set).apply();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            
            // ********* BUG *********************
            // Adds empty note when '+' clicked
            
            
            notes.add("");

            SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.pavanboro.notes", Context.MODE_PRIVATE);

            if(set == null){
                set = new HashSet<String>();
            } else {
                set.clear();
            }

            set.addAll(notes);
            arrayAdapter.notifyDataSetChanged();

            sharedPreferences.edit().remove("notes").apply();
            sharedPreferences.edit().putStringSet("notes",set).apply();

            Intent intent = new Intent(getApplicationContext(),writeNotes.class);
            intent.putExtra("noteId",notes.size() - 1);
            startActivity(intent);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
