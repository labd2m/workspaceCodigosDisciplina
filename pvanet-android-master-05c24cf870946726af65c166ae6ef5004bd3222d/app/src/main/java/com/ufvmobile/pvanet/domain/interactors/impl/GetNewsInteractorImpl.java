package com.ufvmobile.pvanet.domain.interactors.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetNewsInteractor;
import com.ufvmobile.pvanet.domain.interactors.base.AbstractInteractor;
import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.repository.NewsRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class GetNewsInteractorImpl extends AbstractInteractor implements GetNewsInteractor {

    private Callback mCallback;
    private NewsRepository mNewsRepository;

    public GetNewsInteractorImpl(Executor threadExecutor,
                                 MainThread mainThread,
                                 Callback callback,
                                 NewsRepository newsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mNewsRepository = newsRepository;
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

    private void postNews(final List<News> newsList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchSuccessful(newsList);
            }
        });
    }

    @Override
    public void run() {
        List<News> newsList = mNewsRepository.getNews();

        //if someone cancel the interactor, not should be done
        if(mIsCanceled) return;

        //error retrieving the list
        if(newsList == null) {
            notifyError();
            return;
        }

        //response is empty
        if(newsList.isEmpty()) {
            notifyEmptyList();
            return;
        }

        //response successful
        postNews(newsList);
    }

    @Override
    public void stop() {
        cancel();
    }
}
