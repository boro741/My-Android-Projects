/* 
    If the score is greater than 200 then 
    add 48
*/

ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.whereGreaterThan("score",200);

      query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if (e == null && objects != null){

                  for (ParseObject object:objects){
                      object.put("score", object.getInt("score") + 48);
                      object.saveInBackground();
                      Log.i("username", object.getString("username") + ": " +Integer.toString(object.getInt("score")));
                  }
              }
          }
      });