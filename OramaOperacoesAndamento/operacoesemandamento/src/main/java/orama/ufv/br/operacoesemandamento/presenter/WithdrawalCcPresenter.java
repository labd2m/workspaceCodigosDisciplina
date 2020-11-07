package orama.ufv.br.operacoesemandamento.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.model.WithdrawalCc;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;
import orama.ufv.br.operacoesemandamento.util.App;
import orama.ufv.br.operacoesemandamento.view.CheckingAccountWithdrawalActivity;
import orama.ufv.br.operacoesemandamento.view.util.WithdrawalCcCardsAdapter;

/**
 * Created by usuario on 22/08/2017.
 */

public class WithdrawalCcPresenter {

    private static Map<Integer, List<WithdrawalCc>> withdrawalCcResponse = new HashMap<Integer, List<WithdrawalCc>>();
    public static Map<Integer, List<WithdrawalCc>> model(){return withdrawalCcResponse;}

    public static void getWithdrawalCc(String subAccountName, Context ctx){

        List<WithdrawalCc> response = OverloadPresenters.getInstance().getWithdrawalCcs();

        withdrawalCcResponse.clear();
        withdrawalCcResponse.put(200, response);


        if(subAccountName.equals(App.getContext().getString(R.string.spinnerMain))) {
            return;
        }

        Map<Integer, List<WithdrawalCc>> newMap = new HashMap<Integer, List<WithdrawalCc>>();
        newMap.put(200, new ArrayList<WithdrawalCc>());
        for(int i = 0; i < withdrawalCcResponse.get(200).size(); i++) {
            if(withdrawalCcResponse.get(200).get(i).getSubAccounts().getName().equals(subAccountName)) {
                newMap.get(200).add(withdrawalCcResponse.get(200).get(i));
            }
        }
        withdrawalCcResponse = newMap;

    }

    public static void calcelWithdrawal(int position, Activity activity){

        withdrawalCcResponse.get(200).remove(position);

        Log.i("qtd","qtd: "+withdrawalCcResponse.get(200).size());

        OverloadPresenters.getInstance().setWithdrawalCcs(withdrawalCcResponse.get(200));
        RecyclerView.Adapter mAdapter = new WithdrawalCcCardsAdapter(activity);
        RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view_RetiradaCc);
        mRecyclerView.setAdapter(mAdapter);

    }

}
