package com.ufvmobile.pvanet.domain.interactors.impl;

import com.ufvmobile.pvanet.domain.executor.Executor;
import com.ufvmobile.pvanet.domain.executor.MainThread;
import com.ufvmobile.pvanet.domain.interactors.GetModulesInteractor;
import com.ufvmobile.pvanet.domain.interactors.base.AbstractInteractor;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.repository.ModuleRepository;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class GetModulesInteractorImpl extends AbstractInteractor implements GetModulesInteractor {

    private Callback mCallback;
    private ModuleRepository mModuleRepository;

    public GetModulesInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    Callback callback,
                                    ModuleRepository moduleRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mModuleRepository = moduleRepository;
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

    private void postModules(final List<Module> moduleList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFetchSuccessful(moduleList);
            }
        });
    }

    @Override
    public void run() {
        List<Module> moduleList = mModuleRepository.getModules();

        //if someone cancel the interactor, not should be done
        if(mIsCanceled) return;

        //error retrieving the list
        if(moduleList == null) {
            notifyError();
            return;
        }

        //response is empty
        if(moduleList.isEmpty()) {
            notifyEmptyList();
            return;
        }

        //response successful
        postModules(moduleList);
    }

    @Override
    public void stop() {
        cancel();
    }
}
