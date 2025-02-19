package com.ufvmobile.pvanet;

import android.app.Application;

import com.ufvmobile.pvanet.domain.repository.factory.RepoFactory;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class MyApplication extends Application {

    private RepoFactory mRepoFactory;

    @Override
    public void onCreate() {
        super.onCreate();

        // initiate Timber
        Timber.plant(new DebugTree());
    }

    public RepoFactory getRepoFactory() {

        if(mRepoFactory == null) {
            mRepoFactory = new RepoFactory(MyApplication.this);
        }

        return mRepoFactory;

    }
}
