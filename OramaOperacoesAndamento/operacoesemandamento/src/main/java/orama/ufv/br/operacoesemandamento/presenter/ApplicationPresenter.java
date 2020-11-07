package orama.ufv.br.operacoesemandamento.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.model.ApplicationFunds;
import orama.ufv.br.operacoesemandamento.presenter.Util.OverloadPresenters;
import orama.ufv.br.operacoesemandamento.util.App;
import orama.ufv.br.operacoesemandamento.view.util.ApplicationFundsCardsAdapter;

/**
 * Created by usuario on 24/08/2017.
 */

public class ApplicationPresenter {
    private static Map<Integer, List<ApplicationFunds>> applicationResponse = new HashMap<Integer, List<ApplicationFunds>>();
    public static Map<Integer, List<ApplicationFunds>> model(){return applicationResponse;}

    public static void getApplicationFunds(String subAccountName, Context ctx){

        List<ApplicationFunds> response = OverloadPresenters.getInstance().getApplicationFundses();

        applicationResponse.clear();
        applicationResponse.put(200, response);

        if(subAccountName.equals(App.getContext().getString(R.string.spinnerMain))) {
            return;
        }

        Map<Integer, List<ApplicationFunds>> newMap = new HashMap<Integer, List<ApplicationFunds>>();
        newMap.put(200, new ArrayList<ApplicationFunds>());
        for(int i = 0; i < applicationResponse.get(200).size(); i++) {
            if(applicationResponse.get(200).get(i).getSubAccounts().getName().equals(subAccountName)) {
                newMap.get(200).add(applicationResponse.get(200).get(i));
            }
        }
        applicationResponse = newMap;

    }

    public static void cancelApplication(int position, Activity activity){
        applicationResponse.get(200).remove(position);

        Log.i("qtd","qtd: "+applicationResponse.get(200).size());

        OverloadPresenters.getInstance().setApplicationFundses(applicationResponse.get(200));
        RecyclerView.Adapter mAdapter = new ApplicationFundsCardsAdapter(activity);
        RecyclerView mRecyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view_application);
        mRecyclerView.setAdapter(mAdapter);

    }

}
