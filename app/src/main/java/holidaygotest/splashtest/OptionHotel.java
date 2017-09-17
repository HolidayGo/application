package holidaygotest.splashtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class OptionHotel extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionHotel");
        setContentView(R.layout.activity_option_hotel);
    }
}
