package com.example.lucas.testorama;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lucas.testorama.model.passwordRecovery;
import com.example.lucas.testorama.model.passwordRecoveryAuthorization;
import com.example.lucas.testorama.model.passwordRecoveryNotification;
import com.example.lucas.testorama.model.passwordRecoveryPasswordReset;
import com.example.lucas.testorama.model.user;
import com.example.lucas.testorama.network.ApiClient;
import com.example.lucas.testorama.network.ErrorHandler;
import com.example.lucas.testorama.network.OramaEndpointsAPI;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    String TAG = "teste_retrofit";
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.label);

        //faz chamada retrofit
        //getUserProfile();
        //getPasswordRecovery();

        //FAZ CHAMADAS RETROFIT + RXJAVA
        //getUserProfileRx();
        //getPasswordRecoveryRx();
        getPasswordRecoveryNotificationRx();
        //getPasswordRecoveryAuthorizationRx();
        //getPasswordRecoveryPasswordResetRx();

    }

    //---MÉTODOS AUXILIARES PARA APRESENTAR RESPOSTAS DO SERVIDOR
    public String DadosUsuario(user u){
        String txt = "";

        txt += "First_name: " + u.getFirst_name() + "\n";
        txt += "is_approved: " + u.is_approved() + "\n";
        txt += "Partial_email: " + u.getPartial_email() + "\n";
        txt += "Partial_mobile_phone: " + u.getPartial_mobile_phone() + "\n";
        txt += "Support_phone_list: ";

        for (String tel : u.getSupport_phone_list()){
            txt += tel + ", ";
        }
        txt += "\n";

        txt += "Support_type: " + u.getSupport_type();

        label.setText(txt);
        return txt;
    }

    public String DadosPasswordRecovery(passwordRecovery pass){
        String txt = "";

        txt += "Validation_field: " + pass.getValidation_field() + "\n";
        txt += "Validation_message: " + pass.getValidation_message() + "\n";
        txt += "Validation_type: " + pass.getValidation_type() + "\n";
        txt += "Recovery_notification_authorization(): " + pass.getRecovery_notification_authorization() + "\n";
        txt += "Username: ";

        if(pass.getUsername() != null) {
            for (String name : pass.getUsername()) {
                txt += name + ", ";
            }
        }else{
            txt += "null";
        }

        label.setText(txt);
        return txt;
    }

    public String DadosPasswordRecoveryNotification(passwordRecoveryNotification pass){
        String txt = "";

        txt += "Validation_field: " + pass.getValidation_field() + "\n";
        txt += "Validation_message: " + pass.getValidation_message() + "\n";
        txt += "Validation_type: " + pass.getValidation_type() + "\n";
        txt += "Recovery_notification_authorization(): ";
        if(pass.getRecovery_notification_authorization() != null) {
            for (String notification : pass.getRecovery_notification_authorization()) {
                txt += notification + ", ";
            }
        }else{
            txt += "null";
        }
        txt += "\nNotification_channel: ";
        if(pass.getNotification_channel() != null) {
            for (String channel : pass.getNotification_channel()) {
                txt += channel + ", ";
            }
        }else{
            txt += "null";
        }
        txt += "\nSuccess: " + pass.isSuccess();

        label.setText(txt);
        return txt;
    }

    public String DadosPasswordRecoveryAuthorization(passwordRecoveryAuthorization pass){
        String txt = "";

        txt += "Validation_field: " + pass.getValidation_field() + "\n";
        txt += "Validation_message: " + pass.getValidation_message() + "\n";
        txt += "Validation_type: " + pass.getValidation_type() + "\n";
        txt += "Recovery_authorization(): " + pass.getRecovery_authorization();

        label.setText(txt);
        return txt;
    }

    public String DadosPasswordRecoveryPasswordReset(passwordRecoveryPasswordReset pass){
        String txt = "";

        txt += "Validation_field: " + pass.getValidation_field() + "\n";
        txt += "Validation_message: " + pass.getValidation_message() + "\n";
        txt += "Validation_type: " + pass.getValidation_type() + "\n";
        txt += "Success: " + pass.isSuccess() + "\n";

        txt += "Recovery_authorization(): ";
        if(pass.getRecovery_authorization() != null) {
            for (String authorization : pass.getRecovery_authorization()) {
                txt += authorization + ", ";
            }
        }else{
            txt += "null";
        }

        txt += "\nNew_Password(): ";
        if(pass.getNew_password() != null) {
            for (String np : pass.getNew_password()) {
                txt += np + ", ";
            }
        }else{
            txt += "null";
        }

        txt += "\nNew_Password_Confirmation(): ";
        if(pass.getNew_password_confirmation() != null) {
            for (String npc : pass.getNew_password_confirmation()) {
                txt += npc + ", ";
            }
        }else{
            txt += "null";
        }

        label.setText(txt);
        return txt;
    }

    //TODO: esses métodos talvez possam ser colocados nos presenters! pensar a respeito.
    // Talvez nem precise separar em classes diferentes!

    //---CÓDIGOS USANDO RETROFIT E RXJAVA PARA REQUISIÇÕES ASSINCRONAS AO SERVIDOR
    public void getUserProfileRx(){
        OramaEndpointsAPI apiService = ApiClient.getClientRx().
                create(OramaEndpointsAPI.class);

        apiService.getPartialContactInformationRx("12227095709")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<user>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        //user error = ErrorHandler.getUserError(e);
                        user error = (user) ErrorHandler.getError(e, user.class);
                        if(error != null)
                            Log.d(TAG, "Erro: " + DadosUsuario(error));
                    }

                    @Override
                    public final void onNext(user response) {
                        Log.d(TAG, "Sucesso: " + DadosUsuario(response));
                    }
                });
    }

    public void getPasswordRecoveryRx(){
        OramaEndpointsAPI apiService = ApiClient.getClientRx().
                create(OramaEndpointsAPI.class);

        apiService.getPasswordRecoveryRx("", "1986-11-12")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<passwordRecovery>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        //passwordRecovery error = ErrorHandler.getPasswordRecoveryError(e);
                        passwordRecovery error = (passwordRecovery) ErrorHandler.getError(e, passwordRecovery.class);
                        if(error != null)
                            Log.d(TAG, "Erro: " + DadosPasswordRecovery(error));
                    }

                    @Override
                    public final void onNext(passwordRecovery response) {
                        Log.d(TAG, "Sucesso: " + DadosPasswordRecovery(response));
                    }
                });
    }

    public void getPasswordRecoveryNotificationRx(){
        OramaEndpointsAPI apiService = ApiClient.getClientRx().
                create(OramaEndpointsAPI.class);

        apiService.getPasswordRecoveryNotificationRx("27ccdbe61b61249ba81e64604e358ef0da0347bb", "2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<passwordRecoveryNotification>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        passwordRecoveryNotification error = (passwordRecoveryNotification) ErrorHandler.getError(e, passwordRecoveryNotification.class);
                        if(error != null)
                            Log.d(TAG, "Erro: " + DadosPasswordRecoveryNotification(error));
                    }

                    @Override
                    public final void onNext(passwordRecoveryNotification response) {
                        Log.d(TAG, "Sucesso: " + DadosPasswordRecoveryNotification(response));
                    }
                });
    }

    public void getPasswordRecoveryAuthorizationRx(){
        OramaEndpointsAPI apiService = ApiClient.getClientRx().
                create(OramaEndpointsAPI.class);

        apiService.getPasswordRecoveryAuthorizationRx("", "0482")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<passwordRecoveryAuthorization>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        passwordRecoveryAuthorization error = (passwordRecoveryAuthorization) ErrorHandler.getError(e, passwordRecoveryAuthorization.class);
                        if(error != null)
                            Log.d(TAG, "Erro: " + DadosPasswordRecoveryAuthorization(error));
                    }

                    @Override
                    public final void onNext(passwordRecoveryAuthorization response) {
                        Log.d(TAG, "Sucesso: " + DadosPasswordRecoveryAuthorization(response));
                    }
                });
    }

    public void getPasswordRecoveryPasswordResetRx(){
        OramaEndpointsAPI apiService = ApiClient.getClientRx().
                create(OramaEndpointsAPI.class);

        apiService.getPasswordRecoveryPasswordResetRx("","","")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<passwordRecoveryPasswordReset>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        passwordRecoveryPasswordReset error = (passwordRecoveryPasswordReset) ErrorHandler.getError(e, passwordRecoveryPasswordReset.class);
                        if(error != null)
                            Log.d(TAG, "Erro: " + DadosPasswordRecoveryPasswordReset(error));
                    }

                    @Override
                    public final void onNext(passwordRecoveryPasswordReset response) {
                        Log.d(TAG, "Sucesso: " + DadosPasswordRecoveryPasswordReset(response));
                    }
                });
    }


    //---METODOS QUE USAM APENAS RETROFIT PARA FAZER REQUISIÇÕES AO SERVIDOR

    public void getPasswordRecovery(){
        OramaEndpointsAPI apiService = ApiClient.getClient().
                create(OramaEndpointsAPI.class);

        Call<passwordRecovery> callPasswordRecovery = apiService.getPasswordRecovery("","1986-11-12");
        callPasswordRecovery.enqueue(new Callback<passwordRecovery>() {
            @Override
            public void onResponse(Call<passwordRecovery> call, Response<passwordRecovery> response) {

                if (response.isSuccessful()) {

                    passwordRecovery pr = response.body();

                    Log.d(TAG, "Sucesso: " + DadosPasswordRecovery(pr) + "\nCódigo: " + response.code());


                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error =  jObjError.getString("message");

                        Log.d(TAG, "Sem resposta1: " + error);
                    } catch (Exception e) {
                        //TODO: tratar a exceção de entrar aqui quando o códido http_response_code(400);
                        Log.d(TAG, "Sem resposta2: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<passwordRecovery> call, Throwable t) {
                Log.d(TAG, "Login fail 2");
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getUserProfile(){
        OramaEndpointsAPI apiService = ApiClient.getClient().
                create(OramaEndpointsAPI.class);

        //Call<user> callUserProfile = apiService.getUserProfile();
        Call<user> callUserProfile = apiService.getUserProfileParametro("12227095709");
        callUserProfile.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                if (response.isSuccessful()) {

                    user usuario = response.body();

                    /*StudentRepository repo = new StudentRepositoryImpl(LoginActivity.this);
                    repo.createStudent(student);*/

                    Log.d(TAG, "Sucesso: " + DadosUsuario(usuario) + "\nCódigo: " + response.code() );


                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error =  jObjError.getString("message");

                        Log.d(TAG, "Sem resposta1: " + error);
                    } catch (Exception e) {
                        Log.d(TAG, "Sem resposta2: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

                Log.d(TAG, "Login fail 2");

                Log.e(TAG, t.toString());
            }
        });
    }
}
