package com.ufvmobile.pvanet.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class used to create an instance for a client of the API using the Retrofit library.
 * The deserialization of objects is handled by Gson.
 * More Info at <a href="http://square.github.io/retrofit/">http://square.github.io/retrofit/</a>
 * @author : Igor Vilela Damasceno
 * @since :
 */
public class ApiClient {

    public static final String BASE_URL = "https://mobile.dti.ufv.br/rest/pvanet/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
