package holidaygotest.splashtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FinalShare extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: FinalShare");
        setContentView(R.layout.activity_final_share);
    }

    public void shareFB(View view)
    {
        //CODE GOES HERE
    }

    public void shareTW(View view)
    {
        //CODE GOES HERE
    }

    public void shareGP(View view)
    {
        //CODE GOES HERE
    }

    public void mainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenu.class);
        Log.d("app","Main menu button pressed...");

        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
