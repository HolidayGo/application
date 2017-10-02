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
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionCalendar");
        setContentView(R.layout.activity_optioncalendar);

        //Setting values
        calender = (CalendarView)findViewById(R.id.viewDates);
        textview1= (TextView)findViewById(R.id.textView1);
        textview2 = (TextView)findViewById(R.id.textView2);



         //Calls function to get selected date
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub

                changeText(counter, year, month, dayOfMonth);

                if(counter == 2)
                {
                    counter = 1;
                }
                else
                {
                    ++counter;
                }
            }
        });

    }


    //Sets text to selected dates
    public void changeText(int count, int year, int month, int dayOfMonth)
    {

        if(count == 1)

        {
            textview1.setText("Start Date is : " + dayOfMonth + " / " + (month + 1) + " / " + year);
        }

        else
        {
            textview2.setText("End Date is : " + dayOfMonth + " / " + (month + 1) + " / " + year);
        }
    }


    public void confirm(View view)
    {
        //if no end date is selected, prompts user
       final  Intent intent = new Intent(this, AppOptions.class);
        if (new String("End Date not Selected").equals(new String(textview2.getText().toString())))
        {
            Log.d("app","No date selected");
            AlertDialog show = new AlertDialog.Builder(this)
                    .setMessage("Please select two dates!")
                    .setCancelable(false)
                    .setPositiveButton("OK", null)
                    .show();
        }
        //if two dates are selected, prompts user to confirm
        else
        {
        Log.d("app","Confirm button pressed");
        AlertDialog show = new AlertDialog.Builder(this)
                .setMessage("Confirm Dates: \n"+textview1.getText()+"\n"+textview2.getText())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //CODE GOES HERE - Needs to pass over dates
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //Sets text back to original
                        textview1.setText("Start Date not Selected");
                        textview2.setText("End Date not Selected");
                    }
                })
                .show();
        }
        //NOTE: Requires code to block the user from selecting an end date before the start!


    }

    //Prompts user if wants to discard changes on back button press
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


    // Prompts user if wants to discard changes on back press
    @Override
    public void onBackPressed()
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
}
