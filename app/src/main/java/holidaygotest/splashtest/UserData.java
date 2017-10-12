package holidaygotest.splashtest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import holidaygotest.splashtest.daygenerator.Day;

public class UserData
{
    static String userLocation;
    public static int userBudget;               //e.g 35245
    public static String userBudgetString;      //e.g 35,245
    public static String userGroupSize;

    public static List<Day> listOfDays;
    public static HashMap<String, Day> dayMap;


    public static void createDayList(int days)
    {
        listOfDays = new ArrayList<>();
        dayMap = new HashMap<>();

        for(int i = 0; i < days; ++i)
        {
            Day day = newDay(i+1);
            listOfDays.add(i, day);
            dayMap.put(Integer.toString(i+1), day);
        }
    }

    private static Day newDay(int number)
    {
        return new Day(number);
    }

}
