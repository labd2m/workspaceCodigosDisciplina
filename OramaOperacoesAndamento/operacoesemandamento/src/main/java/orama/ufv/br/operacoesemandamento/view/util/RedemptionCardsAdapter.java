package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.RedemptionPresenter;

/**
 * Created by usuario on 24/08/2017.
 */

public class RedemptionCardsAdapter extends RecyclerView.Adapter<RedemptionCardsAdapter.MyViewHolder> {


    private Activity mActivity;
    private FragmentManager mFragment;

    public RedemptionCardsAdapter(Activity activity, FragmentManager fragmentManager){
        mActivity = activity;
        mFragment = fragmentManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.redemption_card_model, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView tvTitle = (TextView) holder.mCardView.findViewById(R.id.tituloCard);
        TextView tvRedemptionDateContent = (TextView) holder.mCardView.findViewById(R.id.tvConteudoDataResgate);
        TextView tvValueContent = (TextView) holder.mCardView.findViewById(R.id.tvConteudoValor);
        TextView tvStatusContent = (TextView) holder.mCardView.findViewById(R.id.tvConteudoStatus);
        TextView tvFund = (TextView) holder.mCardView.findViewById(R.id.tvFundo);
        tvTitle.setText(RedemptionPresenter.model().get(200).get(position).getSubAccounts().getName());
        tvRedemptionDateContent.setText(RedemptionPresenter.model().get(200).get(position).getRedemptionDate());
        tvValueContent.setText(RedemptionPresenter.model().get(200).get(position).getValue() + "");
        tvStatusContent.setText(RedemptionPresenter.model().get(200).get(position).getStatus());

        ((TextView) holder.mCardView.findViewById(R.id.tvVisualizar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsDialogUtil dialog = new DetailsDialogUtil();
                dialog.showRedemptionDetails(mActivity, position);
            }
        });

        if(!RedemptionPresenter.model().get(200).get(position).isCancellable()){
            holder.mCardView.findViewById(R.id.button).setVisibility(View.GONE);
        }else{
            ((Button) holder.mCardView.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsDialogUtil dialog = new DetailsDialogUtil();
                    dialog.cancelRedemption(mActivity,position,mFragment);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return RedemptionPresenter.model().get(200).size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }


}
