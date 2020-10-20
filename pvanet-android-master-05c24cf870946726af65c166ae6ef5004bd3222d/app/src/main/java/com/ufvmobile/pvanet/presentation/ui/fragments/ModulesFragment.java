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
import com.ufvmobile.pvanet.domain.repository.impl.ModuleRepositoryImpl;
import com.ufvmobile.pvanet.presentation.presenters.ModulesPresenter;
import com.ufvmobile.pvanet.presentation.presenters.impl.ModulesPresenterImpl;
import com.ufvmobile.pvanet.presentation.ui.adapters.ModulesMainAdapter;
import com.ufvmobile.pvanet.presentation.ui.fragments.base.BaseFragment;
import com.ufvmobile.pvanet.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment which show a list of modules from a course and the respective tools
 */
public class ModulesFragment extends BaseFragment implements ModulesPresenter.View {

    //constants
    private static final String TAG = ModulesFragment.class.getSimpleName();

    //variables
    private ArrayList<Module> mModulesList = new ArrayList<>();
    private int mCourseId;

    //views
    @BindView(R.id.baseFetchRecyclerView) protected RecyclerView mRecyclerView;
    @BindView(R.id.baseFetchTryAgainButton) protected Button mTryAgainButton;
    @BindView(R.id.baseFetchNotificationText) protected TextView mNotificationText;
    @BindView(R.id.baseFetchProgressBar) protected ProgressBar mProgressBar;
    protected ModulesMainAdapter mAdapter;

    private ModulesPresenter mPresenter;


    /**
     *
     * @param courseId the courseId of the modules
     * @return a new instance of {@link ModulesFragment} with the courseId populated and ready to fetch
     * the modules
     */
    public static ModulesFragment newInstance(int courseId) {

        Bundle args = new Bundle();
        args.putInt(Constants.KEY_COURSE_ID, courseId);
        ModulesFragment fragment = new ModulesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourseId = getArguments().getInt(Constants.KEY_COURSE_ID);
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

        mPresenter = new ModulesPresenterImpl(ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ModuleRepositoryImpl(getActivity(), student, mCourseId));

        setupRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mModulesList.isEmpty()) {
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
        mAdapter = new ModulesMainAdapter(mModulesList, getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().
                getApplicationContext());
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
        if(mProgressBar == null) return;
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void onModulesRetrieved(List<Module> courseModules) {
        mModulesList.addAll(courseModules);
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
