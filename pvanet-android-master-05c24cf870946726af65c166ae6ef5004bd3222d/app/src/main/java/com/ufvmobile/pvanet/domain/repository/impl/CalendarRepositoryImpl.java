package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ufvmobile.pvanet.BuildConfig;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.CalendarRepository;
import com.ufvmobile.pvanet.network.ApiClient;
import com.ufvmobile.pvanet.network.PvanetApiEndPointInterface;
import com.ufvmobile.pvanet.utils.CalendarUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;

/**
 * @author Igor Vilela
 * @since 21/11/2016
 */
public class CalendarRepositoryImpl implements CalendarRepository {

    String TAG = CalendarRepositoryImpl.class.getSimpleName();
    List<PvanetCalendar> mCalendar = null;
    private Context mContext;
    private Student mStudent;

    public CalendarRepositoryImpl(Context context, Student student) {
        mContext = context;
        mStudent = student;
    }

    @Nullable
    @Override
    public List<PvanetCalendar> getCalendar() {
        mCalendar = fetchFromNetwork();
        addCalendarValues();
        return mCalendar;
    }

    private void addCalendarValues() {

        for(PvanetCalendar pvanetCal : mCalendar ) {

            String date = pvanetCal.getDate();
            Calendar cal = CalendarUtils.convertDateToCalendar2(date);
            if(cal == null) {
                Log.d(TAG, "falha ao converter data para Calendar");
                continue;
            }
            /*adding the calendar values into the schema. This is necessary because the
              Material Calendar View library uses those values*/
            pvanetCal.setDay(CalendarUtils.getDay(cal));
            pvanetCal.setMonth(CalendarUtils.getMonth(cal));
            pvanetCal.setYear(CalendarUtils.getYear(cal));
        }
    }

    private List<PvanetCalendar> fetchFromNetwork() {
        PvanetApiEndPointInterface api = ApiClient.getClient().create(PvanetApiEndPointInterface.class);
        Call<ArrayList<PvanetCalendar>> call = api.getCalendar(mStudent.getSapiensRegistrationNumber(),
                BuildConfig.PVANET_API_TOKEN,
                mStudent.getPassword());
        try {
            ArrayList<PvanetCalendar> calendar = call.execute().body();
            return calendar;
        } catch (IOException e) {
            return null;
        }
    }
}
