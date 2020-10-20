package com.example.lucas.testorama.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 06/07/2017.
 */

public class passwordRecoveryNotification implements Serializable {
    private List<String> recovery_notification_authorization = new ArrayList<String>(); //array um valor
    private List<String> notification_channel = new ArrayList<String>();		        //array um valor
    private String validation_field;
    private String validation_type;
    private String validation_message;
    private boolean success;

    public passwordRecoveryNotification() {
    }

    public List<String> getRecovery_notification_authorization() {
        return recovery_notification_authorization;
    }

    public void setRecovery_notification_authorization(List<String> recovery_notification_authorization) {
        this.recovery_notification_authorization = recovery_notification_authorization;
    }

    public List<String> getNotification_channel() {
        return notification_channel;
    }

    public void setNotification_channel(List<String> notification_channel) {
        this.notification_channel = notification_channel;
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
}
