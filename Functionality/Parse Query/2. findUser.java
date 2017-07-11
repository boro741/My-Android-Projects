ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.whereEqualTo("username","boro");
    query.setLimit(1);

    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> objects, ParseException e) {
            if (e == null){
                Log.i("findInBackground", "Retrieved "+objects.size() + " objects");

                if (objects.size() > 0){
                    for (ParseObject object : objects){
                        Log.i("findInBackgroundResults",Integer.toString(object.getInt("score")));
                    }
                }
            }
        }
    });