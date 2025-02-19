package com.ufvmobile.pvanet.domain.repository.factory;

import android.content.Context;

import com.ufvmobile.pvanet.domain.repository.StudentRepository;
import com.ufvmobile.pvanet.domain.repository.impl.StudentRepositoryImpl;

/**
 * @author Igor Vilela
 * @since 12/11/2016
 */

public class RepoFactory {

    Context mContext;

    public RepoFactory(Context context) {
        mContext = context;

    }

    public StudentRepository getStudentRepo() {
        return new StudentRepositoryImpl(mContext);
    }

}
