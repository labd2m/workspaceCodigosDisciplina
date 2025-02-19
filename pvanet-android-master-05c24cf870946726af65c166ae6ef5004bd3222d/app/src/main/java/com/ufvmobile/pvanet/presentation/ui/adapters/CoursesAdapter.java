package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Course;

import java.util.ArrayList;


/**
 * Adapter used to show a list of {@link Course}
 */
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder>  {

    private ArrayList<Course> mCoursesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView courseTitle;

        public MyViewHolder(View view) {
            super(view);
            courseTitle = (TextView) view.findViewById(R.id.coursesTitle);

        }
    }


    public CoursesAdapter(ArrayList<Course> coursesList) {
        this.mCoursesList = coursesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_courses, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course course = mCoursesList.get(position);
        String title = course.getCourseAcronym().concat(" - " ).concat(course.getCourseName());
        holder.courseTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return mCoursesList.size();
    }
}