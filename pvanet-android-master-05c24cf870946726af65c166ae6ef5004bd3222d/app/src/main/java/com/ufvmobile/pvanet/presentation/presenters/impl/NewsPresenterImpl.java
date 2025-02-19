package com.ufvmobile.pvanet.presentation.presenters.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCalendarInteractor;
import com.ufvmobile.pvanet.domain.interactors.GetNewsInteractor;
import com.ufvmobile.pvanet.domain.interactors.impl.GetCalendarInteractorImpl;
import com.ufvmobile.pvanet.domain.interactors.impl.GetNewsInteractorImpl;
import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.repository.CalendarRepository;
import com.ufvmobile.pvanet.domain.repository.NewsRepository;
import com.ufvmobile.pvanet.presentation.presenters.NewsPresenter;
import com.ufvmobile.pvanet.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class NewsPresenterImpl extends AbstractPresenter implements NewsPresenter,
        GetNewsInteractor.Callback {

    private View mView;
    private GetNewsInteractor mInteractor;
    private NewsRepository mRepository;

    public NewsPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             NewsRepository newsRepository) {
        super(executor, mainThread);
        mView = view;
        mRepository = newsRepository;
        mInteractor = new GetNewsInteractorImpl(mExecutor, mMainThread, this, mRepository);

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
    public void onFetchSuccessful(List<News> newsList) {

        //this is used to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.onNewsRetrieved(newsList);
    }

    @Override
    public void onFetchFailed(String failMessage) {

        //this is used to avoid NPE
        if(mView == null) return;

        //TODO: change to real error
        mView.hideProgress();
        mView.showError("stub");
    }

    @Override
    public void onEmptyList() {

        //this is used to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.displayEmptyListMessage();
    }
}
