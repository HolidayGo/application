package holidaygotest.splashtest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

import static android.graphics.Color.*;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: MainActivity");
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Sets the loading circle to grey
        ProgressBar loadCircle = (ProgressBar) findViewById(R.id.progressBar3);
        loadCircle.getIndeterminateDrawable().setColorFilter(LTGRAY, android.graphics.PorterDuff.Mode.MULTIPLY);

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

//        checkGPSStatus();

        Handler h = new Handler();
        h.postDelayed(gpstest, 1000);

    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }

}
