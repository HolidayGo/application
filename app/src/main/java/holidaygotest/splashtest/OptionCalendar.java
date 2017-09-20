package holidaygotest.splashtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class OptionCalendar extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionCalendar");
        setContentView(R.layout.activity_optioncalendar);
    }

    public void confirm(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, AppOptions.class);
        Log.d("app","Confirm button pressed");
        startActivity(intent);
    }

    public void back(View view)
    {
        final Intent intent = new Intent(this, AppOptions.class);
        Log.d("app","Back button pressed");
        AlertDialog show = new AlertDialog.Builder(this)
                .setMessage("Discard changes?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
