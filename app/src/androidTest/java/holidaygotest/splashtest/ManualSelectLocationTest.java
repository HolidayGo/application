package holidaygotest.splashtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ManualSelectLocationTest
{

    @Rule
    public ActivityTestRule<ManualSelectLocation> targetActivity = new ActivityTestRule<>(ManualSelectLocation.class);

    ArrayList<String> correctArray = new ArrayList<>();

    @Test
    public void testCompiledCorrectly()
    {
        String correctCountry = "New Zealand";

        targetActivity.getActivity().compileCityList(correctCountry);

        ArrayList<String> result = targetActivity.getActivity().cityList;

        compileCorrectList();       //get the correct list

        assertTrue(correctArray.equals(result));
    }

    @Test
    public void testIncorrectCountrySelection()
    {
        String correctCountry = "Norway";
        String defaultCountry = "Select Country";       //user hasnt selected any country

        targetActivity.getActivity().compileCityList(correctCountry);
        ArrayList<String> result = targetActivity.getActivity().cityList;

        compileCorrectList();       //get the correct list

        assertFalse(correctArray.equals(result));
        assertFalse(correctArray.equals(result));
    }

    @Test
    public void testIncorrectCountrySelectionDefault()
    {
        String defaultCountry = "Select Country";       //user hasnt selected any country

        targetActivity.getActivity().compileCityList(defaultCountry);
        ArrayList<String> result = targetActivity.getActivity().cityList;

        compileCorrectList();       //get the correct list

        assertFalse(correctArray.equals(result));
    }

    public void compileCorrectList()
    {
        correctArray.add("Select City");
        correctArray.add("Auckland");
        correctArray.add("Hamilton");
        correctArray.add("Palmerston North");
        correctArray.add("Wellington");
        correctArray.add("Christchurch");
        correctArray.add("Invercargill");
    }
}