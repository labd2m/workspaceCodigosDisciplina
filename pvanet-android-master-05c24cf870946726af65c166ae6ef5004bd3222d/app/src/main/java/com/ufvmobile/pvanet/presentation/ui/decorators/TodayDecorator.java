package com.ufvmobile.pvanet.presentation.ui.decorators;

import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Igor Vilela
 * @since 08/09/2016
 */
public class TodayDecorator implements DayViewDecorator {

    private final int color;

    public TodayDecorator(int color) {
        this.color = color;
        //this.dates = new HashSet<>(dates);
    }

    private boolean isToday(CalendarDay day) {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) == day.getYear()
                && cal.get(Calendar.MONTH) == day.getMonth()
                && cal.get(Calendar.DAY_OF_MONTH) == day.getDay();

    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return isToday(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(color));
    }
}