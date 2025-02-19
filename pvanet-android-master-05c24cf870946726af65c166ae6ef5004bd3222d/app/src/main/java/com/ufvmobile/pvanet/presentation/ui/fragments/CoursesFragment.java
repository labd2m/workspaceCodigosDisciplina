package com.ufvmobile.pvanet.presentation.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.MyApplication;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.executor.impl.ThreadExecutor;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.factory.RepoFactory;
import com.ufvmobile.pvanet.domain.repository.impl.CourseRepositoryImpl;
import com.ufvmobile.pvanet.presentation.presenters.CoursesPresenter;
import com.ufvmobile.pvanet.presentation.presenters.impl.CoursesPresenterImpl;
import com.ufvmobile.pvanet.presentation.ui.activities.ModulesActivity;
import com.ufvmobile.pvanet.presentation.ui.adapters.CoursesAdapter;
import com.ufvmobile.pvanet.presentation.ui.fragments.base.BaseFragment;
import com.ufvmobile.pvanet.threading.MainThreadImpl;
import com.ufvmobile.pvanet.utils.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that show a list of courses which the student is currently registered.
 */
public class CoursesFragment extends BaseFragment implements CoursesPresenter.View {

    //constants
    private final static String INSTANCE_KEY = "CourseList";

    //variables
    private ArrayList<Course> mCoursesList = new ArrayList<>();

    //Views
    @BindView(R.id.courses_rv) protected RecyclerView mRecyclerView;
    @BindView(R.id.courses_tryAgain_btn) protected Button mTryAgainButton;
    @BindView(R.id.courses_notification_txt) TextView mNotificationText;
    @BindView(R.id.courses_pb) ProgressBar mProgressBar;
    private CoursesAdapter mAdapter;

    //presenter
    CoursesPresenter mPresenter;

    public CoursesFragment() {
    }

    public static CoursesFragment newInstance() {
        CoursesFragment fragment = new CoursesFragment();
        return fragment;
    }

    /**
     *
     * @param courseList list of courses which the student is currently registered.
     * @return a new instance of {@link CoursesFragment} with the list populated
     */
    public static CoursesFragment newInstance(ArrayList<Course> courseList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(INSTANCE_KEY, courseList);
        CoursesFragment fragment = new CoursesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Student student = ((MyApplication) getContext().getApplicationContext()).getRepoFactory()
                .getStudentRepo().retrieveStudent();

        mPresenter = new CoursesPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new CourseRepositoryImpl(getActivity(), student));

        setupRecyclerView();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(mCoursesList.isEmpty()) {
            mPresenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mPresenter.pause();
    }

    @Override
    public void onStop() {

        mPresenter.stop();
        super.onStop();

    }

    @Override
    public void onDestroyView() {

        mPresenter.destroy();
        super.onDestroyView();
    }

    private void setupRecyclerView() {
        mAdapter = new CoursesAdapter(mCoursesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        //Toast.makeText(getActivity(),"Cliquei em: " + position, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), ModulesActivity.class);
                        int courseCode = mCoursesList.get(position).getId();
                        String courseName = mCoursesList.get(position).getCourseName();
                        it.putExtra(Constants.KEY_COURSE_NAME, courseName);
                        it.putExtra(Constants.KEY_COURSE_ID, courseCode);
                        startActivity(it);
                    }
                }
        );
    }

    /**
     * used to try to fetch again. The intention of this method is to be used when a
     * "TryAgainButton" is clicked.
     */

    @OnClick(R.id.courses_tryAgain_btn)
    public void tryFetchAgain() {
        mPresenter.resume();
    }

    /* Interface Methods */

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mNotificationText.setVisibility(View.GONE);
        mTryAgainButton.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void onCoursesRetrieved(List<Course> studentCourses) {
        mCoursesList.addAll(studentCourses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        mTryAgainButton.setVisibility(View.VISIBLE);
        mNotificationText.setText(R.string.fail_connection);
        mNotificationText.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayEmptyListMessage() {

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        mNotificationText.setLayoutParams(lp);
        mNotificationText.setText(R.string.no_content);
        mNotificationText.setVisibility(View.VISIBLE);
    }
}