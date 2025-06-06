package com.example.lucas.testorama.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class person implements Serializable {
    private String name;
    private String email;
    private List<address> addresses = new ArrayList<address>();
    private String telefone;

    public person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<address> addresses) {
        this.addresses = addresses;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
