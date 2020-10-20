package com.example.lucas.testorama.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 05/07/2017.
 */

public class passwordRecovery implements Serializable {
    private List<String> username = new ArrayList<String>();
    private String validation_field;
    private String validation_type;
    private String validation_message;
    private String recovery_notification_authorization;

    public passwordRecovery() {
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
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

    public String getRecovery_notification_authorization() {
        return recovery_notification_authorization;
    }

    public void setRecovery_notification_authorization(String recovery_notification_authorization) {
        this.recovery_notification_authorization = recovery_notification_authorization;
    }
}
