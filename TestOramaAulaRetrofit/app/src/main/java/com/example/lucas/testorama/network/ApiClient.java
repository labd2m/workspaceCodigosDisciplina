package com.example.lucas.testorama.network;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas on 05/07/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://10.0.2.2/api_orama/"; //TODO: mudar url base para local projeto
    private static Retrofit retrofit = null;

    public static Retrofit getClientRx() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getJsonApiInterceptor(HttpLoggingInterceptor.Logger formatter){
        HttpLoggingInterceptor logging;

        if(formatter != null) {
            logging = new HttpLoggingInterceptor(formatter);
        }else {
            logging = new HttpLoggingInterceptor();
        }

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return client;
    }

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getJsonApiInterceptor(new PrettyJsonLogger())) // interceptar JSON no LogCat
                    //.client(getJsonApiInterceptor(null)) // interceptar JSON no LogCat - sem formatação
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
