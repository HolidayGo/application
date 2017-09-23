package holidaygotest.splashtest;

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.location.Geocoder;
import android.content.Context;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class UseLocation extends AppCompatActivity
{

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
                }, 10);
            }
        } else
        {
            //get the last location from the GPS
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

                        //update location if changed
                        setLocation(location);
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
                setLocation(location);
            }
        }

        Intent intent = new Intent(this, MainMenu.class);
        Log.d("app", "Yes button pressed");
        startActivity(intent);
    }

    private void setLocation(Location location)
    {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        String addr = convertToAddress(location);

        MainMenu.userLocation = (addr);

        Log.d("app", "Current location (lat/long): " + latitude + " " + longitude);
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
            }
        }

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

    private String convertToAddress(Location location)
    {
        String city = null;
        String country = null;

        //truncate coordinates to 3 decimal places
        DecimalFormat df = new DecimalFormat("#.###");
        Double latitude = Double.parseDouble(df.format(location.getLatitude()));
        Double longitude = Double.parseDouble(df.format(location.getLongitude()));

        //Get address base on location
        Geocoder geocoder;

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try
        {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            country = addresses.get(0).getCountryName();
            city = addresses.get(0).getLocality();

            Log.d("app", "Current location string: " +city+", "+ country);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return city+", "+ country;      //e.g. "Auckland, New Zealand"
    }


    public void noButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, ManualSelectLocation.class);
        Log.d("app", "No button pressed");
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
