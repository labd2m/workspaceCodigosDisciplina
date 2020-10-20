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

    //---EndPoints com RxJava e Retrofit
    @GET("user-profile/partial-contact-information")
    Observable<user> getPartialContactInformationRx(@Query("username") String username);

    @FormUrlEncoded
    @POST("authentication/password-recovery/")
    Observable<passwordRecovery> getPasswordRecoveryRx(@Field("username") String username, @Field("birth_date") String birth_date);

    @FormUrlEncoded
    @POST("authentication/password-recovery-notification/")
    Observable<passwordRecoveryNotification> getPasswordRecoveryNotificationRx(@Field("recovery_notification_authorization") String recovery_notification_authorization, @Field("notification_channel") String notification_channel);

    @FormUrlEncoded
    @POST("authentication/password-recovery-authorization/")
    Observable<passwordRecoveryAuthorization> getPasswordRecoveryAuthorizationRx(@Field("username") String username, @Field("recovery_code") String recovery_code);

    @FormUrlEncoded
    @POST("authentication/password-recovery-password-reset/")
    Observable<passwordRecoveryPasswordReset> getPasswordRecoveryPasswordResetRx(@Field("new_password") String new_password, @Field("recovery_authorization") String recovery_authorization, @Field("new_password_confirmation") String new_password_confirmation);


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
