package orama.ufv.br.operacoesemandamento.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 22/08/2017.
 */

public class Account {
    private int id;
    private String user;
    private List<String> password = new ArrayList<String>();
    private List<SubAccounts> subAccountses;

//    public Account(int id, String user, List<String> password) {
//        this.id = id;
//        this.user = user;
//        this.password = password;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<SubAccounts> getSubAccountses() {
        return subAccountses;
    }

    public void setSubAccountses(List<SubAccounts> subAccountses) {
        this.subAccountses = subAccountses;
    }

}
