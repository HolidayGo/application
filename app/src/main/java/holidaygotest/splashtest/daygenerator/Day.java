package holidaygotest.splashtest.daygenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Day
{
    public int dayNumber;              //e.g 6
    public String dayNumberString;          //string variant of day number
    public String dayDetails;          //what happens on this day
    public String dayTitle;            //e.g Day 6

    public ArrayList<DayEvent> today;         //array of activities done on this day
    public Random rand;

    public Day(int dayNumber)
    {
        rand = new Random();        //random generator for numbers
        this.dayNumber = dayNumber;
        /*this.dayDetails = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce ante dui, aliquam sed lectus eu, sollicitudin sodales ante. Cras vitae pharetra mi. Curabitur magna purus, consectetur vel vehicula nec, efficitur non ex. Vivamus ac consequat nunc. Nullam congue fermentum suscipit. Sed lobortis luctus erat vel porta. Cras rhoncus, orci sed blandit cursus, velit quam viverra urna, nec convallis quam eros malesuada massa. Curabitur ut sagittis tellus. Fusce nec nisi et sapien pharetra suscipit a ac velit. Mauris at neque eros. Vivamus nec nisl at velit feugiat aliquet. Suspendisse potenti.\n" +
                "\n" +
                "Sed a pulvinar odio. Proin sodales urna accumsan nibh maximus blandit. Aenean molestie tortor nec molestie pretium. Nunc nec euismod turpis. Etiam vel nisl placerat, scelerisque dolor in, porttitor arcu. Nulla dapibus posuere ultrices. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi dui erat, sodales et orci sed, laoreet finibus felis. Integer commodo tristique faucibus. Sed sodales ipsum a ligula lobortis condimentum.\n" +
                "\n" +
                "Proin at mauris vitae tortor varius sagittis sed in quam. Nulla metus ex, ullamcorper finibus tellus ac, placerat tempus ligula. Donec eleifend nisi et tristique aliquam. Vestibulum in quam id tellus rutrum elementum eget a mauris. Praesent ac viverra diam. Integer quis justo metus. Maecenas sed cursus mauris. Nullam suscipit eros ut orci aliquam molestie in at leo. Nunc sed nunc libero. In sagittis porta aliquet. In varius aliquet sapien, id egestas nulla aliquet a. Fusce laoreet massa vitae rhoncus luctus.\n" +
                "\n" +
                "Nam maximus risus non nisi auctor, eu dignissim lorem fringilla. Fusce fringilla non est non ultrices. Etiam vitae leo faucibus, mollis arcu sit amet, aliquam erat. Maecenas et nibh libero. Sed bibendum et mauris vitae maximus. Maecenas auctor sapien enim, eget commodo justo sagittis vel. Etiam sagittis euismod massa, non pretium ligula rutrum sit amet. Sed vestibulum ac turpis at imperdiet. Donec ultricies augue quis tortor pretium iaculis.\n" +
                "\n" +
                "Cras sed arcu et ipsum commodo ultricies quis sit amet nulla. Cras vitae enim ac eros consectetur efficitur vel et purus. Sed in efficitur elit. Integer non ante ex. Nullam nec est vel leo efficitur sollicitudin id quis ligula. Nunc vulputate, felis sit amet accumsan vehicula, lorem turpis venenatis risus, vel luctus dolor leo ac sapien. Aenean non odio nec ligula ultricies dignissim nec a orci. Phasellus lobortis faucibus ex. Fusce quis sem neque. Phasellus nec imperdiet risus. Vivamus ultrices quis sem at finibus. Sed a imperdiet dolor, ac scelerisque dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras tortor libero, imperdiet euismod lacus et, dignissim elementum erat.";
        */

        this.dayTitle = "Day "+dayNumber;
        this.dayNumberString = Integer.toString(dayNumber);

        this.today = new ArrayList<>();

        mockDay();
        convertToText();

        String summary = "";
        for(DayEvent activity : today)
        {
            summary += "Name: " + activity.name;
            summary += "\nStart Time: " + activity.timeStartString;
            summary += "\nDuration: " + activity.hours + " hours";

            summary += "\n\nDescription: " + activity.description;
            summary += "\nLocation: " + activity.location;
            summary += "\nGroup Size: " + activity.groupSize;

            summary +="\n-------------------------\n";

        }
        this.dayDetails = summary;
    }

    public void createActivities()
    {
            //INSERT CODE HERE THAT GETS AN ACTIVITY FROM THE DATABASE
    }

    //convert the generated activities into text form
    public void convertToText()
    {

    }

    public void mockDay()
    {
        double hoursLeft = 24;          //24 hours in a day
        int duration;                   //how long each activity will last (random)

        char alphabet = 'A';
        boolean firstActivity = true;

        //day variables
        String name;
        String description;
        double hours;
        double cost;
        String location;
        int groupSize;
        Date startTime = new Date();
        Date endTime = new Date();      //end of the first activity/start of the next

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try
        {
            startTime = dateFormat.parse("07:00");        //day starts at 7:00am (minimum)
            //create random time to start the day between 7:00am and 10:30am
            duration = RandomGenerator.getMinutes(210);     //generate a random number between 0-210 (intervals of 30) to get minutes after 7am
            startTime = RandomGenerator.makeTimeJump(startTime.getTime(), duration);         //wake up between 7am and 10:30am

            hoursLeft -= (duration / 60d) + 7;       //7 since the base time is 7am (7 hours already gone)
        }
        catch (ParseException e)
        {
            System.err.println("Error parsing date.");
        }

        //create activities until no time left in the day
        while (hoursLeft > 0)
        {
            if(firstActivity)     //first activity is always to wake up
            {
                duration = 60;      //the application always gives the user(s) 1 hour to get ready for the day
                endTime = RandomGenerator.makeTimeJump(startTime.getTime(), duration);         //the time this activity ends (1 hour after starting)
                hours = duration / 60d;       //hours spent doing this activity (will always be 1)
                cost = 0;     //sleeping is free
                name = "Wake up!";
                description = "The time you will wake up";
                location = "hotel";
                groupSize = 1;

                hoursLeft -= hours;
                firstActivity = false;
            }

            //the day officially begins
            else
            {
                duration = 0;

                if(hoursLeft > 3)
                {
                    while (duration == 0)
                    {
                        if(hoursLeft <= 5)      //prevent overflow with time
                        {
                            duration = RandomGenerator.getMinutes((int) (60 * hoursLeft));
                        }
                        else
                        {
                            duration = RandomGenerator.getMinutes(300);     //activity lasts for up to 5 hours
                        }
                    }
                    startTime = endTime;

                    endTime = RandomGenerator.makeTimeJump(startTime.getTime(), duration);         //wake up between 7am and 10:30am

                    hours = duration / 60d;     //hours spent doing this activity

                    cost = 50;          //estimated activity cost will be $50
                    name = "Activity "+alphabet;
                    description = "Description of activity "+alphabet+" will be displayed here!";
                    location = "Somewhere Activity "+alphabet+" belongs.";
                    groupSize = 2;

                    hoursLeft -= hours;        //adjust the number of hours left in the day
                    ++alphabet;
                }
                else    //user will go to bed between 9pm and 12am
                {
                    cost = 0;       //sleeping is free
                    name = "Sleep";
                    description = "The time you will go to sleep";
                    location = "hotel";
                    groupSize = 1;

                    hours = hoursLeft;
                    hoursLeft = 0;      //none left so a new day begins/created
                    startTime = endTime;        //start time is when the last activity has finished
                }

            }

            today.add(new DayEvent(name, description, hours, cost, location, groupSize, startTime));
        }
    }
}
