package com.ufvmobile.pvanet.domain.interactors;

import com.ufvmobile.pvanet.domain.interactors.base.Interactor;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.model.Topic;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public interface GetTopicsInteractor extends Interactor {

    interface Callback {
        void onFetchSuccessful(List<Topic> topicsList);
        void onFetchFailed(String failMessage);
        void onEmptyList();
    }
}
