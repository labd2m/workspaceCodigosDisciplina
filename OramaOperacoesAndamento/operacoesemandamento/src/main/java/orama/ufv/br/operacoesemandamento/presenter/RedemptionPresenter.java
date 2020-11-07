package orama.ufv.br.operacoesemandamento.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.model.Redemption;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;
import orama.ufv.br.operacoesemandamento.util.App;
import orama.ufv.br.operacoesemandamento.view.util.RedemptionCardsAdapter;

/**
 * Created by usuario on 24/08/2017.
 */

public class RedemptionPresenter {

    private static Map<Integer, List<Redemption>> redemptionResponse = new HashMap<Integer, List<Redemption>>();
    public static Map<Integer, List<Redemption>> model(){return redemptionResponse;}

    public static void getRedemption(String subAccountName, Context ctx){

        List<Redemption> response = OverloadPresenters.getInstance().getRedemptions();

        redemptionResponse.clear();
        redemptionResponse.put(200, response);


        if(subAccountName.equals(App.getContext().getString(R.string.spinnerMain))) {
            return;
        }

        Map<Integer, List<Redemption>> newMap = new HashMap<Integer, List<Redemption>>();
        newMap.put(200, new ArrayList<Redemption>());
        for(int i = 0; i < redemptionResponse.get(200).size(); i++) {
            if(redemptionResponse.get(200).get(i).getSubAccounts().getName().equals(subAccountName)) {
                newMap.get(200).add(redemptionResponse.get(200).get(i));
            }
        }
        redemptionResponse = newMap;

    }

    public static void cancelRedemption(int position, Activity activity, FragmentManager fragment){
        redemptionResponse.get(200).remove(position);

        Log.i("qtd","qtd: "+redemptionResponse.get(200).size());

        OverloadPresenters.getInstance().setRedemptions(redemptionResponse.get(200));
        RecyclerView.Adapter mAdapter = new RedemptionCardsAdapter(activity, fragment);
        RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view_redemption);
        mRecyclerView.setAdapter(mAdapter);

    }
}
