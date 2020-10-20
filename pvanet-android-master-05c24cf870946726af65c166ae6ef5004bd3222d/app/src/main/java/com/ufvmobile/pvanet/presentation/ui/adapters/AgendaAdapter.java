package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.PvanetCalendar;
import com.ufvmobile.pvanet.domain.model.Schedule;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Adapter used to show a list of {@link PvanetCalendar}
 */
public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.MyViewHolder>  {

    private Context mContext;
    private ArrayList<Schedule> mScheduleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.calendar_course_title) public TextView courseNameTv;
        @BindView(R.id.ll_calendar_info_root) public LinearLayout container;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public AgendaAdapter(Context context, ArrayList<Schedule> calendar) {
        this.mContext = context;
        this.mScheduleList = calendar;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_info_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Schedule schedule = mScheduleList.get(position);

        if(schedule.getAgenda().isEmpty()) {
            holder.courseNameTv.setVisibility(View.GONE);
            return;
        }

        String courseName = schedule.getCourseName();
        holder.courseNameTv.setText(courseName);

        for(String content : schedule.getAgenda()) {
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            content = "- ".concat(content);
            tv.setText(content);
            tv.setPadding(0, 4, 0, 0);
            holder.container.addView(tv);
        }
    }

    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }
}