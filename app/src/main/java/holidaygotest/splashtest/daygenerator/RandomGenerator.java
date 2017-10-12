package holidaygotest.splashtest.daygenerator;

import java.util.Date;
import java.util.Random;

/*
    This class creates the random elements of the application
 */
public class RandomGenerator
{
    private static Random rand = new Random();
    private static final int MIN_MINUTES = 30;             //30 minutes

    /*
    public static int generateTime(double startTime, double endTime)
    {
        int output;

        double timeDifference = endTime - startTime;

        long wholeValue;
        double decimal = 0;

        if (!((timeDifference % 1) == 0))     //check if its a whole value
        {
            wholeValue = (long) timeDifference;             //get the whole part of the value e.g 6.7 becomes 6
            decimal = timeDifference - wholeValue;
        } else
        {
            wholeValue = (long) timeDifference;
        }

        //get minutes
        long totalTime = (wholeValue * 60) + (long) (decimal * 60);


        long range = totalTime / MIN_MINUTES;

        output = (rand.nextInt((int) range) * MIN_MINUTES);


        return output;
    }*/

    public static int getMinutes(int duration)
    {
        int range = duration / MIN_MINUTES;
        int result = (rand.nextInt(range) * MIN_MINUTES);
        return result;
    }

    public static Date makeTimeJump(long startTime, int duration) //startTime 7:00, endTime 10:30
    {
        Date clock = new Date();
        clock.setTime(startTime);

        clock.setTime(clock.getTime() + duration * 60000);  //add the random number of minutes to the start time

        return clock;
    }
}
