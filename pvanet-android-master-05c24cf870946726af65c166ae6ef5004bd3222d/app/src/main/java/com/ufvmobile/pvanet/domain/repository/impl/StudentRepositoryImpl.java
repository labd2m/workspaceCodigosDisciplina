package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.StudentRepository;

/**
 * @author Igor Vilela
 * @since 12/11/2016
 */

public class StudentRepositoryImpl implements StudentRepository {

    private String TAG = StudentRepositoryImpl.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences mPref;

    // Editor for Shared preferences
    private SharedPreferences.Editor mEditor;

    private Context mContext;

    //Shared Preferences Keys
    private static final String KEY_STUDENT_MAT = "studMat";
    private static final String KEY_STUDENT_PASS = "studPass";

    // preferences mode
    int PRIVATE_MODE = 0;

    public StudentRepositoryImpl(Context context) {
        mContext = context;
        mPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        mEditor = mPref.edit();
    }

    @Override
    public void createStudent(@NonNull Student student) {

        mEditor.putString(KEY_STUDENT_MAT, student.getSapiensRegistrationNumber());
        mEditor.putString(KEY_STUDENT_PASS, student.getPassword());
        mEditor.commit();

        Log.d(TAG, "User is stored in shared preferences. " + student.getName() + ", " + student.getEmail());
    }

    @Nullable
    @Override
    public Student retrieveStudent() {
        if (mPref.getString(KEY_STUDENT_MAT, null) != null) {
            String mat, pass;
            mat = mPref.getString(KEY_STUDENT_MAT, null);
            pass = mPref.getString(KEY_STUDENT_PASS, null);

            Student user = new Student();
            user.setPassword(pass);
            user.setMatriculaSapiens(mat);
            return user;
        }
        return null;
    }

    @Override
    public void deleteStudent() {
        mEditor.clear();
        mEditor.commit();
    }
}
