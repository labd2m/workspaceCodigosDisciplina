package com.ufvmobile.pvanet.presentation.presenters.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCoursesInteractor;
import com.ufvmobile.pvanet.domain.interactors.impl.GetCourseInteractorImpl;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.repository.CourseRepository;
import com.ufvmobile.pvanet.presentation.presenters.CoursesPresenter;
import com.ufvmobile.pvanet.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class CoursesPresenterImpl extends AbstractPresenter implements CoursesPresenter,
        GetCoursesInteractor.Callback {

    private CoursesPresenter.View mView;
    private GetCoursesInteractor mInteractor;
    private CourseRepository mRepository;

    public CoursesPresenterImpl(Executor executor,
                                MainThread mainThread,
                                View view,
                                CourseRepository courseRepository) {
        super(executor, mainThread);
        mView = view;
        mRepository = courseRepository;
        mInteractor = new GetCourseInteractorImpl(mExecutor, mMainThread, this, mRepository);

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
    public void onFetchSuccessful(List<Course> courseList) {

        //this is used to avoid NPE
        if(mView == null) return;

        mView.hideProgress();
        mView.onCoursesRetrieved(courseList);
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
