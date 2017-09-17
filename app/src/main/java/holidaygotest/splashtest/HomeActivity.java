package holidaygotest.splashtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class HomeActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

    }

    //Skip button
    public void goToMainMenu(View view)
    {

        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //Facebook
    public void goToMainMenuFB(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //Twitter
    public void goToMainMenuTW(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //Google+
    public void goToMainMenuGP(View view)
    {
        //CODE GOES HERE
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    //Make back button close app code link:https://stackoverflow.com/questions/29364292/how-can-i-put-are-you-sure-you-want-to-exit-when-i-press-back-button-android
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
