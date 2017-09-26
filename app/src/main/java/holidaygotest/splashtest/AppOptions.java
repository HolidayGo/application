package holidaygotest.splashtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormat;
import android.icu.util.Calendar;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static holidaygotest.splashtest.R.id.parent;

public class AppOptions extends AppCompatActivity
{

    public EditText budgetBox;
    Spinner groupNumberSpinner;
    private static final String[] numberList = {"Select Group Size", "1", "2", "3", "4", "5", "6"};
    String selectedGroupNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app", "Entered activity: AppOptions");
        setContentView(R.layout.activity_appoptions);

        //Textbox settings
        budgetBox = (EditText) findViewById(R.id.editBudget);

        //initialise spinner
        groupNumber();

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

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable editable)
            {
                budgetBox.removeTextChangedListener(this);
                String input = editable.toString();

                if (input.contains(","))
                {
                    input = input.replaceAll(",", "");  //clear any existing commas created previously
                }

                String formattedString = formatInput(input);

                budgetBox.addTextChangedListener(this);

                if (!input.equals(""))          //incase input field is empty to prevent NumberFormattException
                {
                    UserData.userBudget = Integer.parseInt(input);     //store selected budget as an int
                }
                UserData.userBudgetString = formattedString;


            }


        });

        Log.d("editbox", budgetBox.getText().toString());


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String formatInput(String input)
    {
        Long formattedValue = null;
        String formattedString = null;

        try
        {
            if (input.contains(","))
            {
                input = input.replaceAll(",", "");  //clear any existing commas created previously
            }

            if (Integer.parseInt(input) > 1000000)      //value inputed is over 1 million
            {
                input = input.substring(0, input.length() - 1);     //remove last inputed character

                alertMaxValue();        //start alert notifying user that the value cant be over $1M
            }

            formattedValue = Long.parseLong(input);        //convert user input into a long

            try
            {
                DecimalFormat formatter = new DecimalFormat("#,###,###");
                formattedString = formatter.format(formattedValue);
            }
            catch (NumberFormatException e)
            {
                formattedString = "";       //reset edittext to empty
            }


            budgetBox.setText(formattedString); //update values in box to inclure commas
            //change marker to be at the end of the text
            budgetBox.setSelection(budgetBox.getText().length());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return formattedString;
    }

    public void alertMaxValue()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AppOptions.this);
        Log.d("app", "Max value exceeded");
        dialog.setMessage("Rich person eh? This application will only accept a budget below $1,000,000");
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


    public void groupNumber()
    {
        groupNumberSpinner = (Spinner) findViewById(R.id.groupnumberspinner);

        ArrayAdapter<String> dataAdapterGroupNumber = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numberList)
        {
            // Disable all items except title (second item in spinner)
            @Override
            public boolean isEnabled(int position)
            {
                if (position == 0)
                {
                    return false;
                } else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = (TextView) view;
                if (position == 0)
                {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                } else
                {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        dataAdapterGroupNumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedGroupNumber = (String) parent.getItemAtPosition(position);

                UserData.userGroupSize = selectedGroupNumber;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        // attaching data adapter to spinner
        groupNumberSpinner.setAdapter(dataAdapterGroupNumber);
    }


    public void nextButton(View view)
    {
        //CODE GOES HERE
        Log.d("app", "Next button pressed...budget is: " + UserData.userBudgetString + " and " + UserData.userBudget);

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
