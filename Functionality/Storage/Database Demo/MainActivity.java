package com.example.pavanboro.databasedemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase myDatabase = openOrCreateDatabase("Users",MODE_PRIVATE,null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Pavan',19)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Sahil',21)");

            // To get the data out
            // Cursor:
            /*
             *  This temporary work area is used to store the data retrieved from the database, 
             *  and manipulate this data. A cursor can hold more than one row, but can process 
             *  only one row at a time. The set of rows the cursor holds is called the active set.
             */
            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while (c != null){
                Log.i("name: ",c.getString(nameIndex));
                Log.i("age: ", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
