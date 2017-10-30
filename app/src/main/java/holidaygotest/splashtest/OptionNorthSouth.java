package holidaygotest.splashtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import holidaygotest.splashtest.daygenerator.DayDetailActivity;
import holidaygotest.splashtest.daygenerator.DayListActivity;

public class OptionNorthSouth extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionNorthSouth");
        setContentView(R.layout.activity_optionnorthsouth);
    }

    public void northButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, DayListActivity.class);
        Log.d("app","North island button pressed");
        startActivity(intent);
    }

    public void southButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, DayListActivity.class);
        Log.d("app","South island button pressed");
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
