package com.ufvmobile.pvanet.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Vilela
 * @since 14/09/2016
 */
public class PvanetCalendar implements Serializable {

    @SerializedName("schedule")
    @Expose
    private ArrayList<Schedule> mSchedule = new ArrayList<Schedule>();

    @SerializedName("data")
    @Expose
    private String mDate;

    private int mYear;
    private int mMonth;
    private int mDay;

    public PvanetCalendar() {

    }

    public PvanetCalendar(int day, int month, int year) {
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PvanetCalendar that = (PvanetCalendar) o;

        return mDay == that.getDay() && mMonth == that.getMonth() && mYear == that.getYear();
    }


    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public ArrayList<Schedule> getSchedule() {
        return mSchedule;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

}
