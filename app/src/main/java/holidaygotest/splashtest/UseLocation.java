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
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //if location is turned OFF, display popup telling user to turn it on
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            diagBox();
        }
        //if location is turn ON, get GPS location
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
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
                //update location
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


                //location updates every 30 seconds in 1km radius with the the network and GPS provider
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 1000, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 1000, locationListener);

                //get the last location from the GPS
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                //print the found location to the main menu screen
                setLocation(location);
            }

        }

        Intent intent = new Intent(this, MainMenu.class);
        Log.d("app", "Yes button pressed");
        startActivity(intent);
    }

    private void diagBox()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(UseLocation.this);

        dialog.setMessage("Location settings disabled\n\n" + "Location settings are turned off. Please turn on Locations to use this application. "
                + "Or manually select your location");
        dialog.setPositiveButton("Enable GPS", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));   //go to settings and turn on location

            }
        });
        dialog.setNegativeButton("Manually select location", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switchToManualSelectLocationActivity();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();

    }

    public void switchToManualSelectLocationActivity()
    {
        Intent intent = new Intent(this, ManualSelectLocation.class);
        Log.d("app", "No button pressed");
        startActivity(intent);
    }


    private void setLocation(Location location)
    {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        String addr = convertToAddress(location);

        UserData.userLocation = (addr);

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

    public String convertToAddress(Location location)
    {
        String city = null;
        String country = null;

        String result = null;

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

            if (addresses.size() > 0) //location found
            {
                country = addresses.get(0).getCountryName();
                city = addresses.get(0).getLocality();

                Log.d("app", "Current location string: " + city + ", " + country);
                result = city + ", " + country;      //e.g. "Auckland, New Zealand"
            } else    //unable to find location
            {
                result = "Unknown";
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
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
