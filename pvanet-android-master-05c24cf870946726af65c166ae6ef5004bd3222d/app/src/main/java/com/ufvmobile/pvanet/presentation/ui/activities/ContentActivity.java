package com.ufvmobile.pvanet.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.presentation.ui.activities.base.BaseActivity;
import com.ufvmobile.pvanet.presentation.ui.fragments.ContentsFragment;
import com.ufvmobile.pvanet.presentation.ui.fragments.DrawerFragment;

/**
 * Activity which hosts the {@link ContentsFragment}
 */
public class ContentActivity extends BaseActivity {

    private ActionBar mActionBar;
    private DrawerFragment mDrawerFragment;

    private static final String TAG = ContentActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        setupViews();
    }

    protected void setupViews() {

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {

            //back arrow on actionbar
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);

            //actionbar title
            String toolTitle = getIntent().getStringExtra(Constants.KEY_TOOL_NAME);
            mActionBar.setTitle(toolTitle);

        }

        setupFragment();
    }

    /**
     * initializes and show the ContentsFragment
     */
    protected void setupFragment() {

        int courseCode = getIntent().getIntExtra(Constants.KEY_COURSE_ID, -1);
        int moduleCode = getIntent().getIntExtra(Constants.KEY_MODULE_ID, -1);
        int toolCode = getIntent().getIntExtra(Constants.KEY_TOOL_ID, -1);
        ContentsFragment fragment = ContentsFragment.newInstance(courseCode, moduleCode, toolCode);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }
}
