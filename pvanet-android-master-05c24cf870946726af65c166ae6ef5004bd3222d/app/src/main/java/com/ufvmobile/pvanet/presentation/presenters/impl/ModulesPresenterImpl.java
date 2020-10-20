package com.ufvmobile.pvanet.presentation.presenters.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCoursesInteractor;
import com.ufvmobile.pvanet.domain.interactors.GetModulesInteractor;
import com.ufvmobile.pvanet.domain.interactors.impl.GetModulesInteractorImpl;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.repository.ModuleRepository;
import com.ufvmobile.pvanet.presentation.presenters.ModulesPresenter;
import com.ufvmobile.pvanet.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ModulesPresenterImpl extends AbstractPresenter implements ModulesPresenter,
        GetModulesInteractor.Callback {

    private View mView;
    private GetModulesInteractor mInteractor;
    private ModuleRepository mRepository;

    public ModulesPresenterImpl(Executor executor,
                                MainThread mainThread,
                                View view,
                                ModuleRepository moduleRepository) {
        super(executor, mainThread);
        mView = view;
        mRepository = moduleRepository;
        mInteractor = new GetModulesInteractorImpl(mExecutor, mMainThread, this, mRepository);

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

    @Override
    public void onFetchSuccessful(List<Module> modulesList) {

        //this is used to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.onModulesRetrieved(modulesList);
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
