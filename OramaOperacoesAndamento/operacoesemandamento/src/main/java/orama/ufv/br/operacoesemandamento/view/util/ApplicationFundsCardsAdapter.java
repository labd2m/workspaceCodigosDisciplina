package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.ApplicationPresenter;

/**
 * Created by usuario on 24/08/2017.
 */

public class ApplicationFundsCardsAdapter extends RecyclerView.Adapter<ApplicationFundsCardsAdapter.MyViewHolder> {


    private Activity mActivity;

    public  ApplicationFundsCardsAdapter(Activity activity){
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_card_model, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView tvConteudoDataAplicacao = (TextView) holder.mCardView.findViewById(R.id.tvConteudoDataResgate);
        TextView tvConteudoFundo = (TextView) holder.mCardView.findViewById(R.id.tvConteudoFundo);
        TextView tvConteudoValor = (TextView) holder.mCardView.findViewById(R.id.tvConteudoValor);
        TextView tvConteudoStatus = (TextView) holder.mCardView.findViewById(R.id.tvConteudoStatus);

        tvConteudoDataAplicacao.setText(ApplicationPresenter.model().get(200).get(position).getAplicationDate());

        tvConteudoFundo.setText(ApplicationPresenter.model().get(200).get(position).getFunds());
        tvConteudoValor.setText(ApplicationPresenter.model().get(200).get(position).getValue() + "");
        tvConteudoStatus.setText(ApplicationPresenter.model().get(200).get(position).getStatus());

        ((TextView) holder.mCardView.findViewById(R.id.tvVisualizar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsDialogUtil dialog = new DetailsDialogUtil();
                dialog.showApplicationDetails(mActivity, position);
            }
        });


        if(!ApplicationPresenter.model().get(200).get(position).isCancellable()){
            holder.mCardView.findViewById(R.id.button).setVisibility(View.GONE);
        }else{
            ((Button) holder.mCardView.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailsDialogUtil dialog = new DetailsDialogUtil();
                    dialog.cancelApplication(mActivity,position, ApplicationPresenter.model().get(200).get(position).getFunds());
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return ApplicationPresenter.model().get(200).size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public MyViewHolder(View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
        }
    }


}
