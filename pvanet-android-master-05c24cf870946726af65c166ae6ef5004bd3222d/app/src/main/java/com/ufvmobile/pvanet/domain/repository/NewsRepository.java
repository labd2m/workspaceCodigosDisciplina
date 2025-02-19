package com.ufvmobile.pvanet.domain.repository;

import android.support.annotation.Nullable;

import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.model.Topic;

import java.util.List;

/**
 * @author Igor Vilela
 * @since 17/08/2016
 */
public interface NewsRepository {

    @Nullable
    List<News> getNews();

}
