package com.ufvmobile.pvanet.domain.interactors.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetTopicsInteractor;
import com.ufvmobile.pvanet.domain.interactors.base.AbstractInteractor;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.domain.repository.TopicRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class GetTopicsInteractorImpl extends AbstractInteractor implements GetTopicsInteractor {

    private Callback mCallback;
    private TopicRepository mTopicsRepository;

    public GetTopicsInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                   Callback callback,
                                   TopicRepository topicsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mTopicsRepository = topicsRepository;
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

    private void postModules(final List<Topic> topicList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchSuccessful(topicList);
            }
        });
    }

    @Override
    public void run() {
        List<Topic> topicList = mTopicsRepository.getTopics();

        //if someone cancel the interactor, not should be done
        if(mIsCanceled) return;

        //error retrieving the list
        if(topicList == null) {
            notifyError();
            return;
        }

        //response is empty
        if(topicList.isEmpty()) {
            notifyEmptyList();
            return;
        }

        //response successful
        postModules(topicList);
    }

    @Override
    public void stop() {
        cancel();
    }
}
