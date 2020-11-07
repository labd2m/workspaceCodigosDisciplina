package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.BuyFixedIncomePresenter;

/**
 * Created by usuario on 22/08/2017.
 */

public class BuyFixedIncomeCardsAdapter extends RecyclerView.Adapter<BuyFixedIncomeCardsAdapter.MyViewHolder>{

    private List<Object> mDataset;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public BuyFixedIncomeCardsAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public BuyFixedIncomeCardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixed_income_card_model, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        ((TextView) holder.mCardView.findViewById(R.id.tituloCard)).setText(BuyFixedIncomePresenter.model().get(200).get(position).getTitle());

        TextView tvConteudoDataAplicacao = (TextView) holder.mCardView.findViewById(R.id.tvConteudoDataResgate);
        tvConteudoDataAplicacao.setText(BuyFixedIncomePresenter.model().get(200).get(position).getBuyDate());

        TextView tvConteudoTitulo = (TextView) holder.mCardView.findViewById(R.id.tvConteudoTitulo);
        tvConteudoTitulo.setText(BuyFixedIncomePresenter.model().get(200).get(position).getTitle());

        TextView tvConteudoValor = (TextView) holder.mCardView.findViewById(R.id.tvConteudoValor);
        tvConteudoValor.setText(BuyFixedIncomePresenter.model().get(200).get(position).getValue() + "");

        TextView tvBuyDate = (TextView) holder.mCardView.findViewById(R.id.tvBuyDate);
        tvBuyDate.setText(BuyFixedIncomePresenter.model().get(200).get(position).getRequestDate());

        TextView tvConteudoStatus = (TextView) holder.mCardView.findViewById(R.id.tvConteudoStatus);
        tvConteudoStatus.setText(BuyFixedIncomePresenter.model().get(200).get(position).getStatus());



        if(!BuyFixedIncomePresenter.model().get(200).get(position).isCancellable()) {
            holder.mCardView.findViewById(R.id.button).setVisibility(View.GONE);
        } else {
            holder.mCardView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsDialogUtil dialog = new DetailsDialogUtil();
                    dialog.cancelBuyFixedIncome(mActivity,position, BuyFixedIncomePresenter.model().get(200).get(position).getTitle());
                }
            });
        }
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Object currentValue = mDataset.get(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return BuyFixedIncomePresenter.model().get(200).size();
    }
}
