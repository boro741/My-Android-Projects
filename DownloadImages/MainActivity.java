package com.example.pavanboro.downloadingwebcontent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*NOTE: 
 * Add <uses-permission android:name="android.permission.INTERNET"/>
 * in AndroidManifest file
 */
public class MainActivity extends AppCompatActivity {
    ImageView downloadedImg;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImg = (ImageView) findViewById(R.id.imageView1);
    }
    
    
    /* onClick downloadImage button */
    public void downloadImage(View view){
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try{
            String img_url = "https://avatars2.githubusercontent.com/u/16509376?v=3&u=a7e414108929fcdc477af54331a5a27f039f710b&s=400";
            myImage = task.execute(img_url).get();
            downloadedImg.setImageBitmap(myImage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try{
                /*Steps to connect to internet*/
                /*
                 * 1. Open
                 * 2. Connect
                 * 3. Store
                */
                
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);

                return  myBitmap;
            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }

   
}
