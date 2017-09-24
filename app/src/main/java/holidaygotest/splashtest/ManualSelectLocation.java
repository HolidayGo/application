package holidaygotest.splashtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.*;

public class ManualSelectLocation extends AppCompatActivity
{
    ArrayList<String> countryList = new ArrayList<>();  //string list of countries
    ArrayList<String> cityList = new ArrayList<>();     //string list of nz countries

    String selectedCountry = null;
    String selectedCity = null;

    Spinner spCountry;
    Spinner spCity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_select_location);

        compileCountryList();
        compileCityList(null);

        spCountry = (Spinner) findViewById(R.id.countryspinner);
        spCity = (Spinner) findViewById(R.id.cityspinner);
        spCity.setEnabled(false);   //disable until a country is selected


        final ArrayAdapter<String> dataAdapterCity = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityList);

        ArrayAdapter<String> dataAdapterCountry = createCountryList();

        // Drop down layout style - list view with radio button
        dataAdapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //get selected city
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //get selected country
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = (String) parent.getItemAtPosition(position);

                    spCity.setEnabled(true);
                    compileCityList(selectedCountry);
                    spCity.setAdapter(dataAdapterCity);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // attaching data adapter to spinner
        spCountry.setAdapter(dataAdapterCountry);
        spCity.setAdapter(dataAdapterCity);

    }

    private ArrayAdapter<String> createCountryList()
    {
        ArrayAdapter<String> dataAdapterCountry = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList)
        {
            // Disable all items except New Zealand (second item in spinner)
            @Override
            public boolean isEnabled(int position){
                if (position > 1 || position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position > 1) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        return dataAdapterCountry;
    }

    private void compileCityList(String selection)
    {
        if(selection == "New Zealand")
        {
            cityList.add("Auckland");
            cityList.add("Hamilton");
            cityList.add("Palmerston North");
            cityList.add("Wellington");
            cityList.add("Christchurch");
            cityList.add("Invercargill");
        }
        else
        {
            if (!cityList.contains("Select City"))  //first time
            {
                cityList.add("Select City");
            }
        }
    }

    private void compileCountryList()
    {
        Locale[] locales = Locale.getAvailableLocales();    //get list of all countries

        for (Locale locale : locales)
        {
            String aCountry = locale.getDisplayCountry();   //get string value of current country being iterated
            if (aCountry.trim().length() > 0 && !countryList.contains(aCountry))    //if not the last item and country doesnt already exist on the list
            {
                countryList.add(aCountry);  //add country to the selected list
            }
        }

        Collections.sort(countryList);  //sort list alphabetically

        //move nz to the top of the list
        countryList.remove("New Zealand");
        countryList.add(0, "Select Country");
        countryList.add(1, "New Zealand");
    }


    public void nextButton(View view)
    {
        Log.d("app", "Next button pressed...");

        if(selectedCity != null && selectedCountry != null)     //user selected a location
        {
            MainMenu.userLocation = selectedCity+", "+ selectedCountry;

            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

    }

    public void countryspinner(View view)
    {

    }

    public void citySpinner(View view)
    {

    }
}
