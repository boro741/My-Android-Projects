@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.

                locationManager.removeUpdates(this);
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
