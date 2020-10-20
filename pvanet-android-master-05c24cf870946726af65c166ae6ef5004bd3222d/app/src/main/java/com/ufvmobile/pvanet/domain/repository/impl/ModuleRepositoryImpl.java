package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.BuildConfig;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.model.Tool;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.domain.repository.ModuleRepository;
import com.ufvmobile.pvanet.network.ApiClient;
import com.ufvmobile.pvanet.network.PvanetApiEndPointInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class ModuleRepositoryImpl implements ModuleRepository {

    List<Module> mModules = null;
    private Context mContext;
    private Student mStudent;
    private int mCourseId;

    public ModuleRepositoryImpl(Context context, Student student, int courseId) {
        mContext = context;
        mStudent = student;
        mCourseId = courseId;
    }

    @Nullable
    @Override
    public List<Module> getModules() {
        mModules = fetchFromNetwork();
        return mModules;
    }

    private List<Module> fetchFromNetwork() {

        PvanetApiEndPointInterface api = ApiClient.getClient().create(PvanetApiEndPointInterface.class);
        Call<ArrayList<Module>> call = api.getModules(mStudent.getSapiensRegistrationNumber(),
                mCourseId,
                BuildConfig.PVANET_API_TOKEN,
                mStudent.getPassword());
        try {
            mModules = call.execute().body();
            if(mModules == null || mModules.isEmpty()) {
                return mModules;
            }

            //fetchs the tools
            for (int i = 0; i < mModules.size(); i++) {
                Call<ArrayList<Tool>> callTool = api.
                        getTools(mStudent.getSapiensRegistrationNumber(),
                                mCourseId,
                                mModules.get(i).getId(),
                                BuildConfig.PVANET_API_TOKEN,
                                mStudent.getPassword());
                mModules.get(i).setModuleContentList(callTool.execute().body());

            }

            //remove the Modules that doesn't have content
            for(Iterator<Module> iterator = mModules.iterator(); iterator.hasNext();) {
                 Module module = iterator.next();
                if(module.getModuleContentList().isEmpty()) {
                    iterator.remove();
                }
            }

            return mModules;
        } catch (IOException e) {
            return null;
        }
    }
}
