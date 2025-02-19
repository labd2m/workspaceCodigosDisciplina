package com.ufvmobile.pvanet.presentation.presenters;

import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.presentation.presenters.base.BasePresenter;
import com.ufvmobile.pvanet.presentation.ui.BaseView;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 14/09/2016
 */
public interface CalendarPresenter extends BasePresenter {

    interface View extends BaseView {

        /**
         * Called when needs to display an empty list feedback for the user
         */
        void displayEmptyListMessage();

        /**
         * called when fetches with success.
         * @param studentCalendar
         */
        void onCalendarRetrieved(List<PvanetCalendar> studentCalendar);
    }
}
