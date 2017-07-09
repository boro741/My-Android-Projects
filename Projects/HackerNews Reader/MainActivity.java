package com.example.pavanboro.hackernews;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Map<Integer, String> articleTitles = new HashMap<Integer, String>();
    Map<Integer, String> articleURLs = new HashMap<Integer, String>();
    ArrayList<Integer> articleIds = new ArrayList<Integer>();

    SQLiteDatabase articleDB;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> content = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getApplicationContext(),articl.class);
               intent.putExtra("articleUrl",urls.get(position));
               /*intent.putExtra("content",content.get(position));*/
               startActivity(intent);
           }
       });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), articl.class);
                *//*i.putExtra("articleUrl", urls.get(position));
                i.putExtra("content", content.get(position));*//*

                Log.i("articleURLPositoin:",urls.get(position));
                Log.i("articleContenta:",content.get(position));

                startActivity(intent);

            }
        });*/


        articleDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articleDB.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleId INTEGER, url VARCHAR, title VARCHAR, content VARCHAR)");


        updateListView();

        DownloadTask task = new DownloadTask();

        try {

            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        } catch (Exception e) {
            e.printStackTrace();
        }


       /*
            THIS SNIPPET Code Didn't work

       while (c != null) {

            titles.add(c.getString(titleIndex));

            Log.i("articleId", Integer.toString(c.getInt(articleIdIndex)));
            Log.i("articleUrl", c.getString(urlIndex));
            Log.i("articleTitle", c.getString(titleIndex));

            c.moveToNext();
        }*/

       /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),articl.class);
                intent.putExtra("articleId",position);
                startActivity(intent);
            }
        });*/

       updateListView();

    }


    public void updateListView(){

        try {
            Log.i("updateListView","DONE");
            Cursor c = articleDB.rawQuery("SELECT * FROM articles", null);

            int contentIndex = c.getColumnIndex("content");
            int urlIndex = c.getColumnIndex("url");
            int titleIndex = c.getColumnIndex("title");

             //c.moveToFirst();

            titles.clear();
            urls.clear();



            //THIS SNIPPET code NOT WORKING

            if (c != null) {
                if (c.moveToFirst()) {
                    do {

                        titles.add(c.getString(titleIndex));
                        urls.add(c.getString(urlIndex));
                    } while (c.moveToNext());
                }
            }



            arrayAdapter.notifyDataSetChanged();

        } catch (Exception e){

            Log.i("Exception","You have a EXCEPTION");
            e.printStackTrace();
        }



    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }


                JSONArray jsonArray = new JSONArray(result);

                articleDB.execSQL("DELETE FROM articles");

                for (int i = 0; i < 20; i++) {

                    String articleId = jsonArray.getString(i);

                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");

                    urlConnection = (HttpURLConnection) url.openConnection();

                    in = urlConnection.getInputStream();

                    reader = new InputStreamReader(in);

                    data = reader.read();

                    String articleInfo = "";

                    while (data != -1) {

                        char current = (char) data;

                        articleInfo += current;

                        data = reader.read();

                    }

                    JSONObject jsonObject = new JSONObject(articleInfo);


                    String articleTitle = jsonObject.getString("title");
                    String articleURL = jsonObject.getString("url");

                    String articleContent = "";


                    articleIds.add(Integer.valueOf(articleId));
                    articleTitles.put(Integer.valueOf(articleId), articleTitle);
                    articleURLs.put(Integer.valueOf(articleId), articleURL);


                    Log.i("articleID", String.valueOf(Integer.valueOf(articleId)));
                    Log.i("articleTitle",articleTitle);
                    Log.i("articleURL",articleURL);



                    // To deal with malacious code like articleURL which we don't know what it might contain like ' .
                    // We use the following:
                    // Also called PREPARED STATEMENTS

                    String sql = "INSERT INTO articles (articleId, url, title, content) VALUES (?, ?, ?, ?)";

                    SQLiteStatement statement = articleDB.compileStatement(sql);

                    statement.bindString(1, articleId);
                    statement.bindString(2, articleURL);
                    statement.bindString(3, articleTitle);
                    statement.bindString(4, articleContent);

                    statement.execute();

                    Log.i("articeI: ",String.valueOf(i));

                }
            } catch (Exception e) {
                Log.i("artileException","EXCEPTION CALLED");
                e.printStackTrace();
              }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();

        }
    }



}
