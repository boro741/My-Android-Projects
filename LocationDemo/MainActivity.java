package com.example.pavanboro.locationdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
   
        provider = locationManager.getBestProvider(new Criteria(), false);
        
        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null){
            Log.i("Location info", "Location Achieved");
        } else{
            Log.i("Location info", "No Location");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // @location parameters:
        // (provider, seconds, meters, context
        locationManager.requestLocationUpdates(provider,400,1,this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }


    // Everytime it is called when user location is updated
    @Override
    public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        Log.i("Location info: lat",lat.toString());
        Log.i("Location info: lng ",lng.toString());


    }

    // When user location available after  being unvailable for a while
    // Like Resume
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    //Used to know when GPS location is available
    @Override
    public void onProviderEnabled(String provider) {

    }

    //Used to know when GPS location is unavailable
    @Override
    public void onProviderDisabled(String provider) {

    }


    public  void updateLocation(View view){
        Location location = locationManager.getLastKnownLocation(provider);

        onLocationChanged(location);
    }
}
