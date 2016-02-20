package com.phunware.localpoint.api.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * Created by vinodkumar on 1/6/16.
 */
public class TimeUtils {

    static LocalTime localTime = LocalTime.now();
    static LocalDate localDate = LocalDate.now();


    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
    static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");


    static String time = localTime.format(timeFormat);
    static String date = localDate.format(dateFormat);


    //need to return date and time in the format Ex: 2016-01-07 14:22
    public static String setStartDate() {
        // if current time is divisible by 15, we want to schedule the campaign 15 mins from current time. Else, campaign starts at current time.
        if (localTime.getMinute() % 15 == 0) {
            localTime = localTime.plusMinutes(15);
            time=localTime.format(timeFormat);
            return (date + time);
        } else {
            return (date+ time);
        }
    }

    // need to return date and time in the format Ex: 2016-01-07 14:22. since its endDate we want it to end 1 day after it was created
    public static String setEndDate() {
         localDate=localDate.plusDays(1);
         date=localDate.format(dateFormat);
         return (date + time);
    }
}
