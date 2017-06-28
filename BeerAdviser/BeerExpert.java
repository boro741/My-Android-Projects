package com.example.pavanboro.findbeer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavanboro on 27/06/2017 AD.
 */

public class BeerExpert {

    // Returns list
    List<String> getBrands(String color){

        List<String> brands = new ArrayList<>();

        if(color.equals("amber")){

            brands.add("Jack Amber");
            brands.add("Red Moose");

        } else{

            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");

        }

        return brands;
    }
}
