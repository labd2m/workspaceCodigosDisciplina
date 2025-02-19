package com.example.lucas.testorama.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 06/07/2017.
 */

public class passwordRecoveryPasswordReset implements Serializable{
    private List<String> recovery_authorization = new ArrayList<String>(); 	        //array de um valor
    private String validation_field;
    private String validation_type;
    private String validation_message;
    private boolean success;
    private List<String> new_password = new ArrayList<String>();;	                //array de um valor
    private List<String> new_password_confirmation = new ArrayList<String>();;		//array de um valor


    public passwordRecoveryPasswordReset() {
    }

    public List<String> getRecovery_authorization() {
        return recovery_authorization;
    }

    public void setRecovery_authorization(List<String> recovery_authorization) {
        this.recovery_authorization = recovery_authorization;
    }

    public String getValidation_field() {
        return validation_field;
    }

    public void setValidation_field(String validation_field) {
        this.validation_field = validation_field;
    }

    public String getValidation_type() {
        return validation_type;
    }

    public void setValidation_type(String validation_type) {
        this.validation_type = validation_type;
    }

    public String getValidation_message() {
        return validation_message;
    }

    public void setValidation_message(String validation_message) {
        this.validation_message = validation_message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getNew_password() {
        return new_password;
    }

    public void setNew_password(List<String> new_password) {
        this.new_password = new_password;
    }

    public List<String> getNew_password_confirmation() {
        return new_password_confirmation;
    }

    public void setNew_password_confirmation(List<String> new_password_confirmation) {
        this.new_password_confirmation = new_password_confirmation;
    }
}
