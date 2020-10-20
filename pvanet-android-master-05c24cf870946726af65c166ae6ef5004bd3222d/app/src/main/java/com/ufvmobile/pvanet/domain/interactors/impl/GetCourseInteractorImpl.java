package com.ufvmobile.pvanet.domain.interactors.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetCoursesInteractor;
import com.ufvmobile.pvanet.domain.interactors.base.AbstractInteractor;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.repository.CourseRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class GetCourseInteractorImpl extends AbstractInteractor implements GetCoursesInteractor {

    private GetCourseInteractorImpl.Callback mCallback;
    private CourseRepository mCourseRepository;

    public GetCourseInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   GetCourseInteractorImpl.Callback callback,
                                   CourseRepository courseRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mCourseRepository = courseRepository;
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

    private void postCourses(final List<Course> courseList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchSuccessful(courseList);
            }
        });
    }

    @Override
    public void run() {
        List<Course> coursesList = mCourseRepository.getStudentCourses();

        //if someone cancel the interactor, not should be done
        if(mIsCanceled) return;

        //error retrieving the list
        if(coursesList == null) {
            notifyError();
            return;
        }

        //response is empty
        if(coursesList.isEmpty()) {
            notifyEmptyList();
            return;
        }

        //response successful
        postCourses(coursesList);
    }

    @Override
    public void stop() {
        cancel();
    }
}
