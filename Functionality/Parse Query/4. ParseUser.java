

/*

    IMPORTANT:
    ----------
    Make sure to remove this
    ParseUser.enableAutomaticUser();

*/



// Create a user
    /*ParseUser user = new ParseUser();

    user.setUsername("boro741");
    user.setPassword("samsung9866");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          Log.i("SignUp","Successful");
        } else {
          Log.i("SignUp","Failed");
        }
      }
    });*/


// User log in
   /* ParseUser.logInInBackground("boro741", "samsung9866", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if(user != null){
          Log.i("Login","Successful");
        } else {
          Log.i("Login","Failed" + e.toString());
        }
      }
    });*/



// User Log out
    ParseUser.logOut();


    // To check whether user is logged in
    if(ParseUser.getCurrentUser() != null){
      Log.i("currentUser","User logged in "+ParseUser.getCurrentUser().getUsername());
    } else {
      Log.i("currentUser","User not logged in");
    }