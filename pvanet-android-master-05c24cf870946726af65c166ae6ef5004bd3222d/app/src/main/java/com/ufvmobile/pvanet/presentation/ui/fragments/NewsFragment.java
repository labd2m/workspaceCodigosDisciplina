package com.ufvmobile.pvanet.presentation.ui.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.MyApplication;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.executor.impl.ThreadExecutor;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.impl.NewsRepositoryImpl;
import com.ufvmobile.pvanet.presentation.presenters.NewsPresenter;
import com.ufvmobile.pvanet.presentation.presenters.impl.NewsPresenterImpl;
import com.ufvmobile.pvanet.presentation.ui.adapters.NewsAdapter;
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
public class NewsFragment extends BaseFragment implements NewsPresenter.View {

    //constants
    private final static String INSTANCE_KEY = "CourseList";

    //variables
    private ArrayList<News> mNewsList = new ArrayList<>();

    //Views
    @BindView(R.id.news_rv) protected RecyclerView mRecyclerView;
    @BindView(R.id.news_tryAgain_btn) protected Button mTryAgainButton;
    @BindView(R.id.news_notification_txt) TextView mNotificationText;
    @BindView(R.id.news_pb) ProgressBar mProgressBar;

    private AlertDialog mDialog;
    private NewsAdapter mAdapter;

    //presenter
    NewsPresenter mPresenter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Student student = ((MyApplication) getContext().getApplicationContext()).getRepoFactory()
                .getStudentRepo().retrieveStudent();

        mPresenter = new NewsPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new NewsRepositoryImpl(getActivity(), student));

        setupRecyclerView();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(mNewsList.isEmpty()) {
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
        mAdapter = new NewsAdapter(mNewsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        News news = mNewsList.get(position);

                        //avoiding two 'fast' clicks open more than one dialog
                        if(mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }

                        mDialog = new AlertDialog.Builder(getActivity())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setTitle(news.getTitle())
                                .setMessage(news.getNews())
                                .create();

                        mDialog.show();
                    }
                }
        );
    }

    /**
     * used to try to fetch again. The intention of this method is to be used when a
     * "TryAgainButton" is clicked.
     */

    @OnClick(R.id.news_tryAgain_btn)
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
    public void onNewsRetrieved(List<News> studentCourses) {
        mNewsList.clear();
        mNewsList.addAll(studentCourses);
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
        mNotificationText.setText(R.string.no_content);
        mNotificationText.setVisibility(View.VISIBLE);
    }
}