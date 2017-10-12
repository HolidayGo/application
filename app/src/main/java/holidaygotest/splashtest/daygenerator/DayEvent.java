package holidaygotest.splashtest.daygenerator;

//Specific activity that the user will do on a given day

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayEvent
{
    public String name;     //name of the activity
    public String description;      //description of the activity
    public String timeStartString;

    public double hours;       //duration of the activity in hours
    public double cost;     //how much the activity costs to do
    public String location;     //location of the activity
    public int groupSize;

    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");


    public DayEvent(String name, String description, double hours, double cost, String location, int groupSize, Date timeStart)
    {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.cost = cost;
        this.location = location;
        this.groupSize = groupSize;

        this.timeStartString = convertTime(timeStart);
    }

    private String convertTime(Date timeStart)
    {
        String timeOfDay = "am";
        String timeString;

        Calendar cal = Calendar.getInstance();
        cal.setTime(timeStart);

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour >= 12)
        {
            timeOfDay = "pm";
        }

        timeString = dateFormat.format(timeStart);

        return timeString+timeOfDay;

    }

    /*
    private String convertTime(Date timeStart)
    {
        DecimalFormat df = new DecimalFormat("00");
        //int time =
        int time = 2330;
        int hours;
        int minutes;
        String timeOfDay = "am";

        minutes = time % 100;
        hours = (time - minutes) / 100;

        if (hours >= 12)        //set time to pm if the time is in the afternoon
        {
            timeOfDay = "pm";
        }

        if (hours > 12)
        {
            hours -= 12;        //12-hour clock so there (e.g cannot be a 13th hour)
        }


        String result = hours+":"+df.format(minutes)+timeOfDay;

        return result;
    }*/
}
