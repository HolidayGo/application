package holidaygotest.splashtest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import static android.graphics.Color.*;

public class MainActivity extends Activity
{

    private void checkGPSStatus()
    {
        LocationManager locationManager = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (locationManager == null)
        {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        try
        {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch (Exception ex)
        {
        }
        try
        {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        catch (Exception ex)
        {
        }
        if (!gps_enabled && !network_enabled)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("GPS not enabled");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.show();

        } else//testing
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("GPS not enabled");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ProgressBar v = (ProgressBar) findViewById(R.id.progressBar3);
        v.getIndeterminateDrawable().setColorFilter(LTGRAY, android.graphics.PorterDuff.Mode.MULTIPLY);
        //getActionBar().setTitle("Test");
        //setTitle("My new title");
        //getActionBar().setIcon(R.drawable.icon2);

        Runnable gpstest = new Runnable()
        {
            @Override
            public void run()
            {

                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        };

        checkGPSStatus();

        Handler h = new Handler();
        h.postDelayed(gpstest, 20000);

    }

    @Override
    public void onBackPressed()
    {
        //makes back button do nothing
    }

}
