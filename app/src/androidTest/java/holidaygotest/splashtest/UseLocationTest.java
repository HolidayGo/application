package holidaygotest.splashtest;

import android.location.Location;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UseLocationTest
{
    @Rule
    public ActivityTestRule<UseLocation> targetActivity = new ActivityTestRule<>(UseLocation.class);


    @Test
    public void handleCoordinatesGivenValid()
    {
        //create fake location
        Location dummyLocation = new Location("");
        dummyLocation.setLatitude(-36.8485d);
        dummyLocation.setLongitude(174.7633d);

        //the actual location converted to text
        String expectedResult = "Auckland, New Zealand";

        String result = targetActivity.getActivity().convertToAddress(dummyLocation);

        //compare results
        assertEquals(expectedResult, result);
    }

    @Test
    public void handleCoordinatesGivenInvalid()
    {
        //create fake location
        Location dummyLocation = new Location("");
       // dummyLocation.setLatitude(0d);
       // dummyLocation.setLongitude(0d);

        //coordinates arent recognised in the database so it outputs the string 'unknown'
        String expectedResult = "Unknown";

        String result = targetActivity.getActivity().convertToAddress(dummyLocation);

        //compare results
        assertEquals(expectedResult, result);
    }

    @Test
    public void handleCoordinatesGivenNull()
    {
        //create fake location
        Location dummyLocation = new Location("");

        //no coordinates are known in the Location class (null)

        //coordinates arent recognised in the database so it outputs the string 'unknown'
        String expectedResult = "Unknown";

        String result = targetActivity.getActivity().convertToAddress(dummyLocation);

        //compare results
        assertEquals(expectedResult, result);
    }
}

