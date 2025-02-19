package com.ufvmobile.pvanet.domain.interactors.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCalendarInteractor;
import com.ufvmobile.pvanet.domain.interactors.base.AbstractInteractor;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.repository.CalendarRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class GetCalendarInteractorImpl extends AbstractInteractor implements GetCalendarInteractor {

    private Callback mCallback;
    private CalendarRepository mCalendarRepository;

    public GetCalendarInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                     Callback callback,
                                     CalendarRepository calendarRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mCalendarRepository = calendarRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchFailed("");
            }
        });
    }

    private void notifyEmptyList() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onEmptyList();
            }
        });
    }

    private void postCalendar(final List<PvanetCalendar> calendarList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchSuccessful(calendarList);
            }
        });
    }

    @Override
    public void run() {
        List<PvanetCalendar> calendarList = mCalendarRepository.getCalendar();

        //if someone cancel the interactor, not should be done
        if(mIsCanceled) return;

        //error retrieving the list
        if(calendarList == null) {
            notifyError();
            return;
        }

        //response is empty
        if(calendarList.isEmpty()) {
            notifyEmptyList();
            return;
        }

        //response successful
        postCalendar(calendarList);
    }

    @Override
    public void stop() {
        cancel();
    }
}
