package com.example.lucas.testorama.model;

import java.io.Serializable;

/**
 * Created by Lucas on 06/07/2017.
 */

public class passwordRecoveryAuthorization implements Serializable {
    private String recovery_authorization;
    private String validation_field;
    private String validation_type;
    private String validation_message;

    public passwordRecoveryAuthorization() {
    }

    public String getRecovery_authorization() {
        return recovery_authorization;
    }

    public void setRecovery_authorization(String recovery_authorization) {
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
}
