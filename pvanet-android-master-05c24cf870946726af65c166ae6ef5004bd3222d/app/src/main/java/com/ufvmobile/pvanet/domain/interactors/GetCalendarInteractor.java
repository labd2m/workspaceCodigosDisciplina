package com.ufvmobile.pvanet.domain.interactors;

import com.ufvmobile.pvanet.domain.interactors.base.Interactor;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public interface GetCalendarInteractor extends Interactor {

    interface Callback {
        void onFetchSuccessful(List<PvanetCalendar> calendarList);
        void onFetchFailed(String failMessage);
        void onEmptyList();
    }
}
