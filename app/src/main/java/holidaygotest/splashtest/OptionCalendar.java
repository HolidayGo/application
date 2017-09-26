package holidaygotest.splashtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OptionCalendar extends Activity
{
    CalendarView calender;
    TextView textview1;
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionCalendar");
        setContentView(R.layout.activity_optioncalendar);
        calender = (CalendarView)findViewById(R.id.viewDates);
        textview1= (TextView)findViewById(R.id.textView1);
        textview2 = (TextView)findViewById(R.id.textView2);

            calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    // TODO Auto-generated method stub

                    textview1.setText("Start Date is : " + dayOfMonth + " / " + (month + 1) + " / " + year);
                }
            });

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
