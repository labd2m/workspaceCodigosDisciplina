package orama.ufv.br.operacoesemandamento.presenter;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.model.SubAccounts;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;

/**
 * Created by usuario on 22/08/2017.
 */

public class SubAccountsPresenter {
    private static Map<Integer, List<SubAccounts>> subAccountsResponse = new HashMap<Integer, List<SubAccounts>>();
    public static Map<Integer, List<SubAccounts>> model(){return subAccountsResponse;}

    public static void getSubAccounts(String name, Context ctx){
        List<SubAccounts> response = OverloadPresenters.getInstance().getSubAccountses();

        subAccountsResponse.clear();
        subAccountsResponse.put(200, response);
    }
}
