package com.ufvmobile.pvanet.presentation.ui.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.model.Schedule;
import com.ufvmobile.pvanet.presentation.ui.adapters.AssigmentsAdapter;
import com.ufvmobile.pvanet.presentation.ui.adapters.AgendaAdapter;

/**
 * @author Igor Vilela
 * @since 26/09/2016
 */

public class PvanetCalendarDialog extends AlertDialog {

    protected PvanetCalendarDialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder extends AlertDialog.Builder {

        private Context mContext;

        private RecyclerView mAgendaRv;
        private RecyclerView mAssignmentsRv;
        private TextView mScheduleTitle;
        private TextView mAssignmentsTitle;
        private AgendaAdapter mAgendaAdapter;
        private AssigmentsAdapter mAssignmentsAdapter;
        private PvanetCalendar mPvanetCalendar;

        public Builder(@NonNull Context context, PvanetCalendar calendar) {
            super(context);
            mPvanetCalendar = calendar;
            mContext = context;
            construct(mContext);
        }

        public Builder(@NonNull Context context, @StyleRes int style) {
            super(context, style);
            construct(context);
        }

        @Override
        public AlertDialog create() {

            //escondendo titulos que não fazem sentido, ou seja, não possuem conteudos
            boolean hideAssigments = true;
            boolean hideAgenda = true;

            for(Schedule schedule : mPvanetCalendar.getSchedule()) {
                if(!schedule.getAssigments().isEmpty()) hideAssigments = false;
                if(!schedule.getAgenda().isEmpty()) hideAgenda = false;
            }

            if(hideAssigments) {
                mAssignmentsTitle.setVisibility(View.GONE);
            }

            if(hideAgenda) {
                mScheduleTitle.setVisibility(View.GONE);
            }

            return super.create();
        }

        private void construct(Context context) {
            setPositiveButton("OK", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            View v = LayoutInflater.from(context).inflate(R.layout.calendar_info_content,
                    null);

            mAgendaRv = (RecyclerView) v.findViewById(R.id.schedule_rv);
            mAssignmentsRv = (RecyclerView) v.findViewById(R.id.assigments_rv);
            mAssignmentsTitle = (TextView) v.findViewById(R.id.assigments_txt);
            mScheduleTitle = (TextView) v.findViewById(R.id.schedule_txt);

            setView(v);

            setupLists(context);
        }

        private void setupLists(Context context) {

            mAgendaAdapter = new AgendaAdapter(mContext, mPvanetCalendar.getSchedule());
            mAgendaRv.setHasFixedSize(true);
            mAgendaRv.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
            mAgendaRv.setItemAnimator(new DefaultItemAnimator());
            mAgendaRv.setAdapter(mAgendaAdapter);

            mAssignmentsAdapter = new AssigmentsAdapter(mContext, mPvanetCalendar.getSchedule());
            mAssignmentsRv.setHasFixedSize(true);
            mAssignmentsRv.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
            mAssignmentsRv.setItemAnimator(new DefaultItemAnimator());
            mAssignmentsRv.setAdapter(mAssignmentsAdapter);

        }
    }
}
