package orama.ufv.br.operacoesemandamento.presenter;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.model.Account;
import orama.ufv.br.operacoesemandamento.model.SubAccounts;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;

/**
 * Created by usuario on 22/08/2017.
 */

public class AccountPresenter {


    private static Map<Integer, Account> accountResponse = new HashMap<Integer, Account>();

    public static Map<Integer, Account> model(){ return accountResponse;}

    public static void getAccountMoc(int id, Context ctx) {

        Account response = OverloadPresenters.getInstance().getAccount();

        accountResponse.clear();
        accountResponse.put(200, response);
    }


}
