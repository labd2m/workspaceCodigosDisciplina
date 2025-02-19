package com.ufvmobile.pvanet.domain.repository;

import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.domain.model.Course;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public interface CourseRepository {

    @Nullable List<Course> getStudentCourses();

}
