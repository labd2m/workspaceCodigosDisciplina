package com.ufvmobile.pvanet.presentation.presenters.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCalendarInteractor;
import com.ufvmobile.pvanet.domain.interactors.impl.GetCalendarInteractorImpl;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.repository.CalendarRepository;
import com.ufvmobile.pvanet.presentation.presenters.CalendarPresenter;
import com.ufvmobile.pvanet.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CalendarPresenterImpl extends AbstractPresenter implements CalendarPresenter,
        GetCalendarInteractor.Callback {

    private View mView;
    private GetCalendarInteractor mInteractor;
    private CalendarRepository mRepository;

    public CalendarPresenterImpl(Executor executor,
                                 MainThread mainThread,
                                 View view,
                                 CalendarRepository calendarRepository) {
        super(executor, mainThread);
        mView = view;
        mRepository = calendarRepository;
        mInteractor = new GetCalendarInteractorImpl(mExecutor, mMainThread, this, mRepository);

    }


    /**Presenter methods*/

    @Override
    public void resume() {
        mView.showProgress();
        mInteractor.execute();

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        mInteractor.stop();

        //indicates that the View was destroyed
        mView = null;
    }

    /*@Override
    public void onError(String message) {

    }*/

    /**Callback methods*/
    @Override
    public void onFetchSuccessful(List<PvanetCalendar> calendarList) {

        //this is useful to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.onCalendarRetrieved(calendarList);
    }

    @Override
    public void onFetchFailed(String failMessage) {

        //this is useful to avoid NPE
        if(mView == null) return;

        //TODO: change to real error
        mView.hideProgress();
        mView.showError("stub");
    }

    @Override
    public void onEmptyList() {

        //this is useful to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.displayEmptyListMessage();
    }
}
