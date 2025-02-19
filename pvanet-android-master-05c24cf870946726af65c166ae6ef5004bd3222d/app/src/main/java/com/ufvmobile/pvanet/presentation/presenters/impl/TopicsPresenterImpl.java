package com.ufvmobile.pvanet.presentation.presenters.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetTopicsInteractor;
import com.ufvmobile.pvanet.domain.interactors.impl.GetTopicsInteractorImpl;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.domain.repository.CourseRepository;
import com.ufvmobile.pvanet.domain.repository.TopicRepository;
import com.ufvmobile.pvanet.presentation.presenters.TopicsPresenter;
import com.ufvmobile.pvanet.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class TopicsPresenterImpl extends AbstractPresenter implements TopicsPresenter,
        GetTopicsInteractor.Callback {

    private View mView;
    private GetTopicsInteractor mInteractor;
    private TopicRepository mRepository;

    public TopicsPresenterImpl(Executor executor,
                               MainThread mainThread,
                               View view,
                               TopicRepository topicRepository) {
        super(executor, mainThread);
        mView = view;
        mRepository = topicRepository;
        mInteractor = new GetTopicsInteractorImpl(mExecutor, mMainThread, this, mRepository);

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
    public void onFetchSuccessful(List<Topic> topicsList) {

        //this is used to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.onTopicsRetrieved(topicsList);
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
