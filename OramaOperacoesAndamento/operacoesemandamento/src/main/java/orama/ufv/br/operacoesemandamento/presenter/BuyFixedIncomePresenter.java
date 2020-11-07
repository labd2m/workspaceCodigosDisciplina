package orama.ufv.br.operacoesemandamento.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.model.BuyFixedIncome;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;
import orama.ufv.br.operacoesemandamento.util.App;
import orama.ufv.br.operacoesemandamento.view.util.BuyFixedIncomeCardsAdapter;

/**
 * Created by usuario on 22/08/2017.
 */

public class BuyFixedIncomePresenter {

    private static Map<Integer, List<BuyFixedIncome>> buyFixedIncomeResponse = new HashMap<Integer, List<BuyFixedIncome>>();
    public static Map<Integer, List<BuyFixedIncome>> model(){return buyFixedIncomeResponse;}

    public static void getBuyFixedIncome(String subAccountName, Context ctx){

        List<BuyFixedIncome> response = OverloadPresenters.getInstance().getBuyFixedIncomes();

        buyFixedIncomeResponse.clear();
        buyFixedIncomeResponse.put(200, response);


        if(subAccountName.equals(App.getContext().getString(R.string.spinnerMain))) {
            return;
        }

        Map<Integer, List<BuyFixedIncome>> newMap = new HashMap<Integer, List<BuyFixedIncome>>();
        newMap.put(200, new ArrayList<BuyFixedIncome>());
        for(int i = 0; i < buyFixedIncomeResponse.get(200).size(); i++) {
            if(buyFixedIncomeResponse.get(200).get(i).getSubAccounts().getName().equals(subAccountName)) {
                newMap.get(200).add(buyFixedIncomeResponse.get(200).get(i));
            }
        }
        buyFixedIncomeResponse = newMap;

    }


    public static void calcelBuyFixedIncome(int position, Activity activity){
        buyFixedIncomeResponse.get(200).remove(position);
        OverloadPresenters.getInstance().setBuyFixedIncomes(buyFixedIncomeResponse.get(200));
        RecyclerView.Adapter mAdapter = new BuyFixedIncomeCardsAdapter(activity);
        RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view1);
        mRecyclerView.setAdapter(mAdapter);
    }

}
