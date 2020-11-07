package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.WithdrawalCcPresenter;

/**
 * Created by usuario on 22/08/2017.
 */

public class WithdrawalCcCardsAdapter extends RecyclerView.Adapter<WithdrawalCcCardsAdapter.MyViewHolder>{

    private List<Object> mDataset;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }

    public WithdrawalCcCardsAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public WithdrawalCcCardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checking_account_card_model, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        ((TextView) holder.mCardView.findViewById(R.id.tituloCard)).setText(WithdrawalCcPresenter.model().get(200).get(position).getSubAccounts().getName());

        TextView tvConteudoDataRetirada = (TextView) holder.mCardView.findViewById(R.id.tvConteudoDataRetirada);
        tvConteudoDataRetirada.setText(WithdrawalCcPresenter.model().get(200).get(position).getWithdrawalDate());

        TextView tvConteudoValor = (TextView) holder.mCardView.findViewById(R.id.tvConteudoValor);
        tvConteudoValor.setText(WithdrawalCcPresenter.model().get(200).get(position).getValue() + "");

        TextView tvConteudoStatus = (TextView) holder.mCardView.findViewById(R.id.tvConteudoStatus);
        tvConteudoStatus.setText(WithdrawalCcPresenter.model().get(200).get(position).getStatus());

        if(!WithdrawalCcPresenter.model().get(200).get(position).isCancellable()){
            holder.mCardView.findViewById(R.id.button).setVisibility(View.GONE);
        }else{
            ((Button) holder.mCardView.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsDialogUtil dialog = new DetailsDialogUtil();
                    dialog.cancelWithdrawalCa(mActivity,position, WithdrawalCcPresenter.model().get(200).get(position).getTipo());
                }
            });
        }

        ((TextView) holder.mCardView.findViewById(R.id.tvVisualizar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsDialogUtil dialog = new DetailsDialogUtil();
                dialog.showDetailsWithdrawalCa(mActivity,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return WithdrawalCcPresenter.model().get(200).size();
    }
}
