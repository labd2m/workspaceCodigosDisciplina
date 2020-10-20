package com.example.lucas.testorama.network;

import com.example.lucas.testorama.model.passwordRecovery;
import com.example.lucas.testorama.model.passwordRecoveryAuthorization;
import com.example.lucas.testorama.model.passwordRecoveryNotification;
import com.example.lucas.testorama.model.passwordRecoveryPasswordReset;
import com.example.lucas.testorama.model.user;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lucas on 05/07/2017.
 */

public interface OramaEndpointsAPI {

    //---Endpoints usando apenas Retrofit
    @GET("user-profile/partial-contact-information?username=12227095709")
    Call<user> getUserProfile();

    @GET("user-profile/partial-contact-information")
    Call<user> getUserProfileParametro( @Query("username") String username);

    @FormUrlEncoded
    @POST("authentication/password-recovery/")
    Call<passwordRecovery> getPasswordRecovery(@Field("username") String username, @Field("birth_date") String birth_date);
    //TODO: verificar porque requisições POST estão chegando nulas no servidor. Solução: colocar barra no final do endpoint

}
