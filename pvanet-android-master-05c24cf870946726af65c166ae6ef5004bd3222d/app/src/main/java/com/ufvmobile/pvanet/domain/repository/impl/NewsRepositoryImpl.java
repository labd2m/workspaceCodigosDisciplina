package com.ufvmobile.pvanet.domain.repository.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.ufvmobile.pvanet.domain.model.News;
import com.ufvmobile.pvanet.domain.model.Student;
import com.ufvmobile.pvanet.domain.repository.NewsRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Vilela
 * @since 16/08/2016
 */
public class NewsRepositoryImpl implements NewsRepository {

    List<News> mNewsList = null;
    private Context mContext;
    private Student mStudent;
    private String mStudentPassword;

    public NewsRepositoryImpl(Context context, Student student) {
        mContext = context;
        mStudent = student;
    }

    @Nullable
    public List<News> getNews() {
        mNewsList = fetchFromNetwork();
        return mNewsList;
    }

    private List<News> fetchFromNetwork() {
        return stubNews();
    }

    private List<News> stubNews() {

        final String NewsJson = "[{\n" +
                "\t\"codigo\": 4232883,\n" +
                "\t\"dataFim\": \"2016-12-21T00:00:00-02:00\",\n" +
                "\t\"dataInicio\": \"2016-08-29T00:00:00-03:00\",\n" +
                "\t\"exibir\": \"S\",\n" +
                "\t\"noticia\": \"A monitoria será oferecida nos seguintes horários: 2ª 16 horas (apenas em véspera de provas) 3ª 16 horas 4ª 14 horas(apenas em véspera de provas) 5ª 14 horas 6ª 14 horas\",\n" +
                "\t\"resumo\": \"\",\n" +
                "\t\"titulo\": \"Horário da Monitoria\"\n" +
                "}, {\n" +
                "\t\"codigo\": 4232883,\n" +
                "\t\"dataFim\": \"2016-12-21T00:00:00-02:00\",\n" +
                "\t\"dataInicio\": \"2016-08-29T00:00:00-03:00\",\n" +
                "\t\"exibir\": \"S\",\n" +
                "\t\"noticia\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n" +
                "\t\"resumo\": \"At vero eos et accusamus et iusto odio \",\n" +
                "\t\"titulo\": \"Lorem ipsum\"\n" +
                "}, {\n" +
                "\t\"codigo\": 4232883,\n" +
                "\t\"dataFim\": \"2016-12-21T00:00:00-02:00\",\n" +
                "\t\"dataInicio\": \"2016-08-29T00:00:00-03:00\",\n" +
                "\t\"exibir\": \"S\",\n" +
                "\t\"noticia\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n" +
                "\t\"resumo\": \"At vero eos et accusamus et iusto odio \",\n" +
                "\t\"titulo\": \"Lorem ipsum\"\n" +
                "}, {\n" +
                "\t\"codigo\": 4232883,\n" +
                "\t\"dataFim\": \"2016-12-21T00:00:00-02:00\",\n" +
                "\t\"dataInicio\": \"2016-08-29T00:00:00-03:00\",\n" +
                "\t\"exibir\": \"S\",\n" +
                "\t\"noticia\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n" +
                "\t\"resumo\": \"At vero eos et accusamus et iusto odio. At vero eos et accusamus et iusto odio\",\n" +
                "\t\"titulo\": \"Lorem ipsum\"\n" +
                "}]";

        try {

            ArrayList<News> newsList;

            Type collectionType = new TypeToken<ArrayList<News>>(){}.getType();
            newsList = new Gson().fromJson(NewsJson, collectionType);

            return newsList;
        } catch(JsonParseException e) {
            return null;
        }
    }
}
