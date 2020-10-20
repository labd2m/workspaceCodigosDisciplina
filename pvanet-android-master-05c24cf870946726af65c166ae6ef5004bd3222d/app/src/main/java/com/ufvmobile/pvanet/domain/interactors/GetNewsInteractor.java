package com.ufvmobile.pvanet.domain.interactors;

import com.ufvmobile.pvanet.domain.interactors.base.Interactor;
import com.ufvmobile.pvanet.domain.model.News;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public interface GetNewsInteractor extends Interactor {

    interface Callback {
        void onFetchSuccessful(List<News> newsList);
        void onFetchFailed(String failMessage);
        void onEmptyList();
    }
}
