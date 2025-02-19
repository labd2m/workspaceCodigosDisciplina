package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.BuildConfig;
import com.ufvmobile.pvanet.domain.model.Content;
import com.ufvmobile.pvanet.domain.model.Course;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.domain.repository.TopicRepository;
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
public class TopicRepositoryImpl implements TopicRepository {

    List<Topic> mTopics = null;
    private Context mContext;
    private Student mStudent;
    private int mCourseId;
    private int mModuleId;
    private int mToolId;

    public TopicRepositoryImpl(Context context, Student student, int courseId,
                               int moduleId, int toolId) {
        mContext = context;
        mStudent = student;
        mCourseId = courseId;
        mModuleId = moduleId;
        mToolId = toolId;
    }

    @Nullable
    @Override
    public List<Topic> getTopics() {
        mTopics = fetchFromNetwork();
        return mTopics;
    }


    private List<Topic> fetchFromNetwork() {

        PvanetApiEndPointInterface api = ApiClient.getClient().create(PvanetApiEndPointInterface.class);
        Call<ArrayList<Topic>> call = api
                .getTopics(mStudent.getSapiensRegistrationNumber(),
                           mCourseId,
                           mModuleId,
                           mToolId,
                           BuildConfig.PVANET_API_TOKEN,
                           mStudent.getPassword());
        try {
            mTopics = call.execute().body();
            if(mTopics == null || mTopics.isEmpty()) {
                return mTopics;
            }

            //fetchs the content
            for (int i = 0; i < mTopics.size(); i++) {
                Call<ArrayList<Content>> callContent = api.
                        getContent(mStudent.getSapiensRegistrationNumber(),
                                mCourseId,
                                mModuleId,
                                mToolId,
                                mTopics.get(i).getId(),
                                BuildConfig.PVANET_API_TOKEN,
                                mStudent.getPassword());
                mTopics.get(i).setContentList(callContent.execute().body());

            }

            //remove the Topics that doesn't have content
            for(Iterator<Topic> iterator = mTopics.iterator(); iterator.hasNext();) {
                Topic topic = iterator.next();
                if(topic.getContentList().isEmpty()) {
                    iterator.remove();
                }
            }

            return mTopics;
        } catch (IOException e) {
            return null;
        }
    }
}
