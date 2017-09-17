package holidaygotest.splashtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UseLocation extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Log.d("app","Entered activity: UseLocation");

    }

    public void yesButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, AppOptions.class);
        Log.d("app","Yes button pressed");
        startActivity(intent);
    }

    public void noButton(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, AppOptions.class);
        Log.d("app","No button pressed");
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
