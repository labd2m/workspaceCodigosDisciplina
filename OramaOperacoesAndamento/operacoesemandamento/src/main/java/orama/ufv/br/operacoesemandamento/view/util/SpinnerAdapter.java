package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.model.SubAccounts;
import orama.ufv.br.operacoesemandamento.presenter.SubAccountsPresenter;

/**
 * Created by Fábio on 13/08/2017.
 */

public class SpinnerAdapter extends BaseAdapter {

    // private SubAccountsPresenter letData;
    private List<String> letData;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapter(Activity activity) {
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        letData = new ArrayList<String>();
        letData.add("Todas");
        for(int i = 0; i < SubAccountsPresenter.model().get(200).size(); i++) {
            letData.add(SubAccountsPresenter.model().get(200).get(i).getName());
        }
    }

    @Override
    public int getCount() {
        return SubAccountsPresenter.model().get(200).size()+1;
    }

    @Override
    public Object getItem(int position) {
        return letData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            view = inflater.inflate(R.layout.spinner_item, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(letData.get(position));
        return view;
    }


}
