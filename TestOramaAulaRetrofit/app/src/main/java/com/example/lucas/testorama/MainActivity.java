package com.example.lucas.testorama;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.testorama.model.passwordRecovery;
import com.example.lucas.testorama.model.user;
import com.example.lucas.testorama.network.ApiClient;
import com.example.lucas.testorama.network.OramaEndpointsAPI;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {

    String TAG = "teste_retrofit";
    TextView label;
    EditText edtUser;
    EditText edtNasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.label);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtNasc = (EditText) findViewById(R.id.edtNasc);
    }
    public void profileClick(View view) {
        //faz chamada retrofit
        getUserProfile();
    }

    public void passwordClick(View view) {
        //faz chamada retrofit
        getPasswordRecovery();
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

    //---METODOS QUE USAM RETROFIT PARA FAZER REQUISIÇÕES AO SERVIDOR

    public void getPasswordRecovery(){
        OramaEndpointsAPI apiService = ApiClient.getClient().
                create(OramaEndpointsAPI.class);

        Call<passwordRecovery> callPasswordRecovery = apiService.getPasswordRecovery(edtUser.getText().toString(),edtNasc.getText().toString());
        callPasswordRecovery.enqueue(new Callback<passwordRecovery>() {
            @Override
            public void onResponse(Call<passwordRecovery> call, Response<passwordRecovery> response) {

                if (response.isSuccessful()) {

                    passwordRecovery pr = response.body();
                    Log.d(TAG, "Sucesso 200: " + DadosPasswordRecovery(pr) + "\nCódigo: " + response.code());

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error =  jObjError.getString("validation_message");
                        error += "\n" + jObjError.getString("username");

                        Log.d(TAG, "Erro 400: " + error);
                        label.setText(error);
                    } catch (Exception e) {
                        //TODO: tratar a exceção de entrar aqui quando o códido http_response_code(400);
                        Log.d(TAG, "Erro 400: " + e.getMessage());
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
                    Log.d(TAG, "Sucesso 200: " + DadosUsuario(usuario) + "\nCódigo: " + response.code() );

                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error =  jObjError.getString("is_approved");

                        Log.d(TAG, "Erro 400: " + error);
                    } catch (Exception e) {
                        Log.d(TAG, "Erro 400: " + e.getMessage());
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
