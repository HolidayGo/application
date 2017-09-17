package holidaygotest.splashtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class OptionActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("app","Entered activity: OptionActivity");
        setContentView(R.layout.activity_option);
    }
}
