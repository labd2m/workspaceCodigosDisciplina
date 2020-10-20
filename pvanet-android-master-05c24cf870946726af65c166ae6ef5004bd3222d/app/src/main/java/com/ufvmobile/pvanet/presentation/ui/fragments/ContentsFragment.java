package com.ufvmobile.pvanet.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.domain.repository.impl.TopicRepositoryImpl;
import com.ufvmobile.pvanet.presentation.presenters.TopicsPresenter;
import com.ufvmobile.pvanet.presentation.presenters.impl.TopicsPresenterImpl;
import com.ufvmobile.pvanet.presentation.ui.adapters.ContentMainAdapter;
import com.ufvmobile.pvanet.presentation.ui.fragments.base.BaseFragment;
import com.ufvmobile.pvanet.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment which show a list of topics from a tool and the respective content
 */
public class ContentsFragment extends BaseFragment implements TopicsPresenter.View {

    //constants
    private static final String TAG = ContentsFragment.class.getSimpleName();

    //variables
    private ArrayList<Topic> mTopics = new ArrayList<>();
    private int mCourseId;
    private int mModuleId;
    private int mToolId;

    //views
    @BindView(R.id.baseFetchRecyclerView) protected RecyclerView mRecyclerView;
    @BindView(R.id.baseFetchTryAgainButton) protected Button mTryAgainButton;
    @BindView(R.id.baseFetchNotificationText) protected TextView mNotificationText;
    @BindView(R.id.baseFetchProgressBar) protected ProgressBar mProgressBar;
    private ContentMainAdapter mAdapter;

    private TopicsPresenter mPresenter;


    /**
     *
     * @param courseId the course code of the topics
     * @param moduleId the module code of the topics
     * @param toolId the tool code of the topics
     * @return a new instance of {@link ContentsFragment} with the data populated and ready to fetch
     * the list of topics from a tool and the respective content
     */
    public static ContentsFragment newInstance(int courseId, int moduleId, int toolId) {

        Bundle args = new Bundle();
        args.putInt(Constants.KEY_COURSE_ID, courseId);
        args.putInt(Constants.KEY_MODULE_ID, moduleId);
        args.putInt(Constants.KEY_TOOL_ID, toolId);
        ContentsFragment fragment = new ContentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourseId = getArguments().getInt(Constants.KEY_COURSE_ID);
        mModuleId = getArguments().getInt(Constants.KEY_MODULE_ID);
        mToolId = getArguments().getInt(Constants.KEY_TOOL_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_fetch, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Student student = ((MyApplication) getContext().getApplicationContext()).getRepoFactory()
                .getStudentRepo().retrieveStudent();

        mPresenter = new TopicsPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new TopicRepositoryImpl(getActivity(), student, mCourseId,
                        mModuleId, mToolId));

        setupRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTopics.isEmpty()) {
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
        super.onStop();

        mPresenter.stop();
    }

    @Override
    public void onDestroyView() {

        mPresenter.destroy();
        super.onDestroyView();
    }

    private void setupRecyclerView() {
        mAdapter = new ContentMainAdapter(mTopics, getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.baseFetchTryAgainButton)
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
    public void onTopicsRetrieved(List<Topic> courseTopics) {
        mTopics.addAll(courseTopics);
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
