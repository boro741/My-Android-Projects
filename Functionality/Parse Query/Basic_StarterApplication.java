/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("0e03f6e3f28a120bf3cf6dd38368345ebb6f5aaf")
            .clientKey("9b7664846081530929f2bdefcfd0bec809d10e54")
            .server("http://ec2-13-59-233-92.us-east-2.compute.amazonaws.com:80/parse/")
            .build()
    );

      /*ParseObject score = new ParseObject("Score");
      score.put("username","boro");
      score.put("score",90);

      score.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {

              if(e == null){
                  Log.i("SaveInBackground","Successful");
              } else {
                  Log.i("SaveInBackground","Failed. Error: " + e.toString());
              }

          }
      });*/

     /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.getInBackground("yILSpWzSFe", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {

              if(e == null && object != null){

                  object.put("score",200);
                  object.saveInBackground();

                  Log.i("ObjectValue",object.getString("username"));
                  Log.i("ObjectValue", Integer.toString(object.getInt("score")));
              }

          }
      });*/


     // Challenge:
     // Create Tweet Class, username tweet, save on Parse, then query it, and update the tweet content


     /*ParseObject tweet = new ParseObject("Tweet");
     tweet.put("username","boro741");
     tweet.put("tweet","Hey there!");

     tweet.saveInBackground(new SaveCallback() {
         @Override
         public void done(ParseException e) {
             if (e == null){
                 Log.i("save","Successfull");
             } else {
                 Log.i("save","Failed");
             }
         }
     });*/


    ParseQuery query = ParseQuery.getQuery("Tweet");
    query.getInBackground("i5EBoYQ9F5", new GetCallback<ParseObject>() {

        @Override
        public void done(ParseObject object, ParseException e) {
            if(e == null && object != null) {
                Log.i("ObjectValue",object.getString("username"));
                Log.i("ObjectValue",object.getString("tweet"));
            }
        }
    });





    ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
