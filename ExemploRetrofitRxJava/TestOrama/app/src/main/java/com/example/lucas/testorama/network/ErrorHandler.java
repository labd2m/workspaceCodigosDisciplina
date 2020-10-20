package com.example.lucas.testorama.network;

import android.util.Log;

import com.example.lucas.testorama.model.passwordRecovery;
import com.example.lucas.testorama.model.user;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Lucas on 09/07/2017.
 */

public class ErrorHandler {
    // método genérico para obtenção de erro!
    public static Serializable getError(Throwable e, Class modelClassName){
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Response response = exception.response();

            Converter<ResponseBody, Serializable> converter = ApiClient.getClientRx()
                    .responseBodyConverter(modelClassName, new Annotation[0]);
            try {
                Serializable error = converter.convert(response.errorBody());
                return error;
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return null;
    }

    //NÃO SERÁ USADO NO PROJETO ORAMA
    public static passwordRecovery getPasswordRecoveryError(Throwable e){
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Response response = exception.response();

            Converter<ResponseBody, passwordRecovery> converter = ApiClient.getClientRx()
                    .responseBodyConverter(passwordRecovery.class, new Annotation[0]);
            try {
                passwordRecovery error = converter.convert(response.errorBody());
                return error;
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static user getUserError(Throwable e){
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Response response = exception.response();

            Converter<ResponseBody, user> converter = ApiClient.getClientRx()
                    .responseBodyConverter(user.class, new Annotation[0]);
            try {
                user error = converter.convert(response.errorBody());
                return error;
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
