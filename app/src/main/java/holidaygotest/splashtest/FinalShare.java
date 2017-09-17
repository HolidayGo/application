package holidaygotest.splashtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FinalShare extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);
    }
}
