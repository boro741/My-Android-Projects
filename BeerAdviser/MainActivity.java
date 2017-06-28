package com.example.pavanboro.findbeer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickBeerFindBeer(View view){
        //Get reference to TextView
        TextView brands = (TextView) findViewById(R.id.brands);

        //Get a reference to the Spinner
        Spinner color = (Spinner) findViewById(R.id.color);

        //Get the Selected item in the Spinner
        String beerType = String.valueOf(color.getSelectedItem());

        //Get recommendation from the BeerExpert class
        List<String> brandsList = expert.getBrands(beerType);

        StringBuilder brandsFormatted = new StringBuilder();
        for(String brand: brandsList){
            brandsFormatted.append(brand).append('\n');
        }


        //Display the selected item
        brands.setText(brandsFormatted);
    }
}
