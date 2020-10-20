package com.example.lucas.testorama.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 05/07/2017.
 */

public class user implements Serializable {
    private String first_name;
    private boolean is_approved;
    private String partial_email;
    private String partial_mobile_phone;
    private List<String> support_phone_list = new ArrayList<String>();  //array
    private int support_type;

    public user() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public boolean is_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public String getPartial_email() {
        return partial_email;
    }

    public void setPartial_email(String partial_email) {
        this.partial_email = partial_email;
    }

    public String getPartial_mobile_phone() {
        return partial_mobile_phone;
    }

    public void setPartial_mobile_phone(String partial_mobile_phone) {
        this.partial_mobile_phone = partial_mobile_phone;
    }

    public List<String> getSupport_phone_list() {
        return support_phone_list;
    }

    public void setSupport_phone_list(List<String> support_phone_list) {
        this.support_phone_list = support_phone_list;
    }

    public int getSupport_type() {
        return support_type;
    }

    public void setSupport_type(int support_type) {
        this.support_type = support_type;
    }
}
