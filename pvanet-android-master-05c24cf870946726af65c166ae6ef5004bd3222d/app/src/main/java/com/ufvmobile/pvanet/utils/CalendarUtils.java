package com.ufvmobile.pvanet.utils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Utilities for Calendar
 */
public class CalendarUtils {

    /**
     * @param date {@linkplain Date} to pull date information from
     * @return a new Calendar instance with the date set to the provided date. Time set to zero.
     */
    public static Calendar getInstance(@Nullable Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        copyDateTo(calendar, calendar);
        return calendar;
    }

    /**
     * @return a new Calendar instance with the date set to today. Time set to zero.
     */
    @NonNull
    public static Calendar getInstance() {
        Calendar calendar = Calendar.getInstance();
        copyDateTo(calendar, calendar);
        return calendar;
    }

    /**
     * Set the provided calendar to the first day of the month. Also clears all time information.
     *
     * @param calendar {@linkplain Calendar} to modify to be at the first fay of the month
     */
    public static void setToFirstDay(Calendar calendar) {
        int year = getYear(calendar);
        int month = getMonth(calendar);
        calendar.clear();
        calendar.set(year, month, 1);
    }

    /**
     * Copy <i>only</i> date information to a new calendar.
     *
     * @param from calendar to copy from
     * @param to   calendar to copy to
     */
    public static void copyDateTo(Calendar from, Calendar to) {
        int year = getYear(from);
        int month = getMonth(from);
        int day = getDay(from);
        to.clear();
        to.set(year, month, day);
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(YEAR);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(MONTH);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(DATE);
    }

    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(DAY_OF_WEEK);
    }

    /**
     * Converte strigns no formato "2016-11-23T00:00:00-02:00"
     * @param date
     * @return
     */
    public static Calendar convertDateToCalendar(String date) {

        String newDate = date.intern();

        //removing the timezone from the date
        newDate = newDate.substring(0, newDate.length() - 6);

        //removing the "T" tag from the date
        newDate = newDate.replace("T", " ");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        //try to parse the new String to a calendar object
        try {
            // all done
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(newDate));
            return cal;
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * Convers strings dates that is in the format: yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static Calendar convertDateToCalendar2(String date) {
        String newDate = date.intern();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        //try to parse the new String to a calendar object
        try {
            // all done
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(newDate));
            return cal;
        } catch (ParseException e) {
            return null;
        }
    }
}