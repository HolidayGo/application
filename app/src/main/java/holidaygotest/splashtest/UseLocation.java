package holidaygotest.splashtest;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class UseLocation extends AppCompatActivity
{
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


    }

    public void yesButton(View view)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1);
            }

        }
        else
        {
            // get the last location from the GPS.
            final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            final Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            //if the last location cannot be found
            if (location == null)
            {
                final LocationListener locationListener = new LocationListener()
                {
                    @Override
                    public void onLocationChanged(final Location location)
                    {

                        // getting location of user
                        final double latitude = location.getLatitude();
                        final double longitude = location.getLongitude();
                        setLocation(latitude, longitude);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras)
                    {
                    }

                    @Override
                    public void onProviderEnabled(String provider)
                    {
                    }

                    @Override
                    public void onProviderDisabled(String provider)
                    {
                    }
                };

                //location updates every 30 seconds in 1km radius with the the network and GPS provider
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 1000, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 1000, locationListener);
            } else
            {
                //print the found location to the main menu screen
                final double latitude = location.getLatitude();
                final double longitude = location.getLongitude();

                setLocation(latitude, longitude);
            }
        }


        Intent intent = new Intent(this, MainMenu.class);
        Log.d("app", "Yes button pressed");
        startActivity(intent);
    }

    private void setLocation(double latitude, double longitude)
    {
        MainMenu.userLocation = (latitude + " " + longitude);

        Log.d("app", "Current location: "+latitude+" "+longitude);
    }

    private void configureLocation()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]
                        {
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        }, 10);
                return;
            }
        }

        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    configureLocation();
                }
        }
    }

    public void noButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, MainMenu.class);
        Log.d("app", "No button pressed");
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
