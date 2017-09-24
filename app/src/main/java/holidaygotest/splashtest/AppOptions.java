package holidaygotest.splashtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AppOptions extends AppCompatActivity
{

    EditText budgetBox;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app", "Entered activity: AppOptions");
        setContentView(R.layout.activity_appoptions);

        //Textbox settings
        budgetBox = (EditText) findViewById(R.id.editBudget);

        budgetBox.setBackgroundColor(Color.LTGRAY);
    }


    public void budgetOptions(View view)
    {
        //first time selecting EditText box
        if (budgetBox.getText().toString().equals("Enter Budget"))
        {
            budgetBox.setText("");

        }

        budgetBox.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (budgetBox.getText().toString().equals("Enter Budget"))
                {
                    budgetBox.setText("$");
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                budgetBox.removeTextChangedListener(this);

                try
                {
                    String input = editable.toString();
                    int number;
                    if (input.contains(","))
                    {
                        input = input.replaceAll(",", "");
                    }
                    number = Integer.parseInt(input);      //convert to decimal
                    DecimalFormat formatter = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                    {
                        formatter = new DecimalFormat("#,###,###");
                        String formattedString = formatter.format(number);
                        budgetBox.setText("$"+formattedString);
                    }


                    budgetBox.setSelection(budgetBox.getText().length());
                    // to place the cursor at the end of text
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                budgetBox.addTextChangedListener(this);

                /*String input = budgetBox.getText().toString();

                Log.d("editbox", input);

                if (budgetBox.getText().toString().equals(""))
                {
                    budgetBox.setText("Enter Budget");
                }
                if(editable.equals("0"))
                {
                    budgetBox.setText("$"+input);
                }*/

            }

        });

        Log.d("editbox", budgetBox.getText().toString());


    }


    public void nextButton(View view)
    {
        //CODE GOES HERE
        Log.d("app", "Next button pressed...");

        Intent intent = new Intent(this, OptionNorthSouth.class);
        startActivity(intent);
    }

    public void calendarButton(View view)
    {
        //CODE GOES HERE
        Log.d("app", "Calendar button pressed...");

        Intent intent = new Intent(this, OptionCalendar.class);
        startActivity(intent);
    }

    public void randomButton(View view)
    {
        Log.d("app", "Random button pressed...");

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
}
