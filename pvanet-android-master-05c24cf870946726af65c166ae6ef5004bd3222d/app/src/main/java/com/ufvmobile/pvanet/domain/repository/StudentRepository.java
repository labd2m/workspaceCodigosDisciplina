package com.ufvmobile.pvanet.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.domain.model.Student;

/**
 * @author Igor Vilela
 * @since 12/11/2016
 */

public interface StudentRepository {

    void createStudent(@NonNull Student student);
    @Nullable Student retrieveStudent();
    void deleteStudent();

}
