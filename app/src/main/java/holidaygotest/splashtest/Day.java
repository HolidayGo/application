package holidaygotest.splashtest;

/**
 * Created by Connor on 6/10/2017.
 */

public class Day
{
    public int dayNumber;              //e.g 6
    public String dayNumberString;          //string variant of day number
    public String dayDetails;          //what happens on this day
    public String dayTitle;            //e.g Day 6

    public Day(int dayNumber)
    {
        this.dayNumber = dayNumber;
        this.dayDetails = "Details for this day will be displayed here.";
        this.dayTitle = "Day "+dayNumber;
        this.dayNumberString = Integer.toString(dayNumber);
    }
}
