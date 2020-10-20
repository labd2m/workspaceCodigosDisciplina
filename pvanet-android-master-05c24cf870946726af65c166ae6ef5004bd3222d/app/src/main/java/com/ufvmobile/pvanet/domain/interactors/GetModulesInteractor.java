package com.ufvmobile.pvanet.domain.interactors;

import com.ufvmobile.pvanet.domain.interactors.base.Interactor;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.Module;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public interface GetModulesInteractor extends Interactor {

    interface Callback {
        void onFetchSuccessful(List<Module> modulesList);
        void onFetchFailed(String failMessage);
        void onEmptyList();
    }
}
