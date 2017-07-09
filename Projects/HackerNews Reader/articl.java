package com.example.pavanboro.hackernews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class articl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articl);

        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        // Doesn't direct to chrome browser
        webView.setWebViewClient(new WebViewClient());


        Intent intent = getIntent();

        String url = intent.getStringExtra("articleUrl");
       // String content = intent.getStringExtra("content");

        webView.loadUrl(url);
        //webView.loadData(content, "text/html", "UTF-8");
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.

                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/



}
