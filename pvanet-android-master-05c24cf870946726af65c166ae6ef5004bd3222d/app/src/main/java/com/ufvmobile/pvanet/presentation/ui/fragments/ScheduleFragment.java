package com.ufvmobile.pvanet.presentation.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.MyApplication;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.executor.impl.ThreadExecutor;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.impl.CalendarRepositoryImpl;
import com.ufvmobile.pvanet.domain.repository.impl.CalendarRepositoryStub;
import com.ufvmobile.pvanet.presentation.presenters.CalendarPresenter;
import com.ufvmobile.pvanet.presentation.presenters.impl.CalendarPresenterImpl;
import com.ufvmobile.pvanet.presentation.ui.decorators.EventDecorator;
import com.ufvmobile.pvanet.presentation.ui.decorators.TodayDecorator;
import com.ufvmobile.pvanet.presentation.ui.dialogs.PvanetCalendarDialog;
import com.ufvmobile.pvanet.presentation.ui.fragments.base.BaseFragment;
import com.ufvmobile.pvanet.threading.MainThreadImpl;
import com.ufvmobile.pvanet.utils.CalendarUtils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Igor Vilela
 * @since 14/09/2016
 */
public class ScheduleFragment extends BaseFragment implements CalendarPresenter.View,
        OnDateSelectedListener {

    public static final String LOG_TAG = ScheduleFragment.class.getSimpleName();

    //constants
    private final static String INSTANCE_KEY = "calendarList";

    //variables
    private ArrayList<PvanetCalendar> mCalendarList = new ArrayList<>();
    private ArrayList<CalendarDay> mMaterialCalendarDates = new ArrayList<>();

    @BindView(R.id.calendarView) MaterialCalendarView mMaterialCalendarView;
    TodayDecorator mTodayDecorator;

    AlertDialog mDialog;

    //presenter
    CalendarPresenter mPresenter;

    public ScheduleFragment() {
    }

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Student student = ((MyApplication) getContext().getApplicationContext()).getRepoFactory()
                .getStudentRepo().retrieveStudent();

        mPresenter = new CalendarPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new CalendarRepositoryImpl(getActivity(), student));

        mTodayDecorator = new TodayDecorator(R.color.material_indigo900);

        mMaterialCalendarView.setOnDateChangedListener(this);
        decorate();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mCalendarList.isEmpty()) {
            mPresenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mPresenter.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

      mPresenter.stop();
    }

    @Override
    public void onDestroyView() {

        mPresenter.destroy();
        super.onDestroyView();
    }

    private void decorate() {
        mMaterialCalendarView.removeDecorators();
        mMaterialCalendarView.addDecorator(new EventDecorator(Color.RED, mMaterialCalendarDates));
        mMaterialCalendarView.addDecorator(mTodayDecorator);
    }


    @Override
    public void displayEmptyListMessage() {
        Toast.makeText(getActivity(), "Você não possui dados para a agenda", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCalendarRetrieved(List<PvanetCalendar> studentCalendar) {
        mCalendarList.clear();
        mCalendarList.addAll(studentCalendar);

        mMaterialCalendarDates.clear();

        for(PvanetCalendar date : mCalendarList) {
            CalendarDay day = CalendarDay.from(date.getYear(), date.getMonth(), date.getDay());
            mMaterialCalendarDates.add(day);
        }

        decorate();

        Toast.makeText(getActivity(), "Dados encontrados", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showProgress() {
        Toast.makeText(getActivity(), "Buscando dados da agenda...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), "Não foi possivel encontrar dados da agenda...",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Log.d(LOG_TAG, "Date: " + date.toString());
        Log.d(LOG_TAG, "Selected: " + selected);

        Calendar cal = date.getCalendar();
        PvanetCalendar pvanetCalendar = new PvanetCalendar(
                CalendarUtils.getDay(cal),
                CalendarUtils.getMonth(cal),
                CalendarUtils.getYear(cal));

        int idx = mCalendarList.indexOf(pvanetCalendar);

        if(idx >= 0) {

            Locale brasil = new Locale("pt", "BR"); //Retorna do país e a língua
            DateFormat f2 = DateFormat.getDateInstance(DateFormat.DATE_FIELD, brasil);
            String title = f2.format(date.getCalendar().getTime());

            pvanetCalendar = mCalendarList.get(idx);

            //avoiding two 'fast' clicks open more than one dialog
            if(mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }

            mDialog = new PvanetCalendarDialog.Builder(getActivity(), pvanetCalendar)
                    .setTitle(title)
                    .create();

            mDialog.show();
        }

    }
}
