package holidaygotest.splashtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AppOptions extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoptions);

        //Textbox settings
        EditText budgetBox = (EditText) findViewById(R.id.editBudget);
        budgetBox.setBackgroundColor(Color.LTGRAY);
    }


    public void budgetOptions(View view)
    {
        EditText budgetBox = (EditText) findViewById(R.id.editBudget);
        budgetBox.setText(" NZD");

    }

    public void nextButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, OptionNorthSouth.class);
        startActivity(intent);
    }

    public void calendarButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, OptionCalendar.class);
        startActivity(intent);
    }

    public void randomButton(View view)
    {
        new AlertDialog.Builder(this)
                .setMessage("This will generate a completely random holiday, based on the information provided above.\n\nAre you sure you want to do this?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //CODE GOES HERE
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
