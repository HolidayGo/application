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
