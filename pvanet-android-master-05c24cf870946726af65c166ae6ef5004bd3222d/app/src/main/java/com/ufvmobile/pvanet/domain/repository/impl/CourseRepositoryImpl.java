package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.BuildConfig;
import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.CourseRepository;
import com.ufvmobile.pvanet.network.ApiClient;
import com.ufvmobile.pvanet.network.PvanetApiEndPointInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class CourseRepositoryImpl implements CourseRepository {

    List<Course> mCourses = null;
    private Context mContext;
    private Student  mStudent;
    private String mStudentPassword;

    public CourseRepositoryImpl(Context context, Student student) {
        mContext = context;
        mStudent = student;
    }

    @Nullable
    @Override
    public List<Course> getStudentCourses() {
        mCourses = fetchFromNetwork();
        return mCourses;
    }

   private List<Course> fetchFromNetwork() {

       PvanetApiEndPointInterface api = ApiClient.getClient().create(PvanetApiEndPointInterface.class);
       Call<ArrayList<Course>> call = api.getCourses(mStudent.getSapiensRegistrationNumber(),
               BuildConfig.PVANET_API_TOKEN,
               mStudent.getPassword());
       try {
           ArrayList<Course> courses = call.execute().body();
           return courses;
       } catch (IOException e) {
           return null;
       }
    }
}
