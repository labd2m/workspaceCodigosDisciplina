package com.ufvmobile.pvanet.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ufvmobile.pvanet.MyApplication;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.StudentRepository;
import com.ufvmobile.pvanet.domain.repository.impl.StudentRepositoryImpl;
import com.ufvmobile.pvanet.presentation.ui.activities.base.BaseActivity;
import com.ufvmobile.pvanet.presentation.ui.fragments.CoursesFragment;
import com.ufvmobile.pvanet.presentation.ui.fragments.DrawerFragment;
import com.ufvmobile.pvanet.presentation.ui.fragments.NewsFragment;
import com.ufvmobile.pvanet.presentation.ui.fragments.ScheduleFragment;

/**
 * Main Acitivty of the PVANet. The reason why the courses are fetched here and not in the
 * respective fragment is that all the others requests needs a course id.
 * @author Igor Vilela Damasceno
 * @since
 */

public class MainActivity extends BaseActivity
        implements DrawerFragment.FragmentDrawerListener {

    //log
    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerFragment mDrawerFragment;
    private StudentRepository mStudentRepository;

    //variables
    private int mFragmentsOnStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pvanet_main);

        mStudentRepository = ((MyApplication) this.getApplicationContext()).
                getRepoFactory().getStudentRepo();
        Student student = mStudentRepository.retrieveStudent();

        if (student == null) {
            launchLoginActivity();
        }

        setupDrawer();
    }

    /**
     * Initializes the activity views.
     */
    private void setupDrawer() {

        //navigation drawer
        mDrawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        mDrawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setDrawerListener(this);

        showFragment(0);
    }

    /**
     * Select the fragment to be shown based on the navigation drawer order
     * @param position the position in the navigation drawer
     */
    private void showFragment(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = CoursesFragment.newInstance();
                title = getString(R.string.title_courses);
                break;
            case 1:
                fragment = new ScheduleFragment();
                title = getString(R.string.title_schedule);
                break;
            case 2:
                fragment = new NewsFragment();
                title = getString(R.string.title_news);
                break;
            case 3:
                mStudentRepository.deleteStudent();
                launchLoginActivity();
                finish();
                break;
            default:
                break;
        }

        if (fragment != null) {
            changeTopFragment(fragment);

            // set the toolbar title
            if(getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }
    }

    /**
     * change the fragment that is on top of the stack
     * @param fragment the fragment that will be put on top of the stack
     */
    private void changeTopFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();

    }

    private void launchLoginActivity() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        showFragment(position);
    }

}
