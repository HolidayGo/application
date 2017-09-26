package holidaygotest.splashtest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.widget.Spinner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class AppOptionsTest
{
    Spinner spinner;

    @Rule
    public ActivityTestRule<AppOptions> targetActivity = new ActivityTestRule<>(AppOptions.class);

    @Before
    public void setUp()
    {
        spinner = (Spinner)targetActivity.getActivity().findViewById(R.id.groupnumberspinner);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Test
    public void testFormatCorrectly()
    {
        String input = "1435285";

        String expectedResult = "1,435,285";

        String result = targetActivity.getActivity().formatInput(input);

        assertEquals(expectedResult, result);
    }
}