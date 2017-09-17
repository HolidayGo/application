package holidaygotest.splashtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class OptionLocation extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionLocation");
        setContentView(R.layout.activity_option_location);
    }
}
