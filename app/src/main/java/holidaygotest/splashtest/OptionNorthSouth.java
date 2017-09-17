package holidaygotest.splashtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionNorthSouth extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionnorthsouth);
    }

    public void northButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, FinalShare.class);
        startActivity(intent);
    }

    public void southButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, FinalShare.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
