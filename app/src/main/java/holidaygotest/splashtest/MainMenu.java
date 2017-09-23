package holidaygotest.splashtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenu extends Activity
{
    public TextView textLocation;
    public static String userLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: MainMenu");
        setContentView(R.layout.activity_mainmenu);

        textLocation = findViewById(R.id.textLocation);

        textLocation.setText("Current Location: "+userLocation);

        //Button options
        Button versionButton = (Button) findViewById(R.id.mainversion);
        versionButton.setText("Version 1.0");
        versionButton.setTextColor(Color.parseColor("#F0F8FF"));
        versionButton.setTypeface(versionButton.getTypeface(), Typeface.ITALIC);
        versionButton.setTextSize(12);

    }

    public void versionButton(View view)
    {
        //CODE GOES HERE
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainMenu.this);
        Log.d("app","Version button pressed");
        dialog.setMessage("About HolidayGo Version 1.0\n\n" + "This is the first current build of HolidayGo, this build may contain bugs and may crash - use at your own risk!");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    public void loadButton(View view)
    {
        //CODE GOES HERE
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainMenu.this);
        Log.d("app","Load button pressed");
        dialog.setMessage("Load: \n\n(Nothing to see here!)");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    public void goToLocation(View view)
    {
        Log.d("app","Entering the UseLocation activity...");
        Intent intent = new Intent(this, AppOptions.class);
        startActivity(intent);
    }

    //Minamises app if back is pressed
    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }

}
