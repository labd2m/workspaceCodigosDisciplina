package orama.ufv.br.operacoesemandamento.view.util;

import android.app.Activity;
import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.ApplicationPresenter;
import orama.ufv.br.operacoesemandamento.presenter.BuyFixedIncomePresenter;
import orama.ufv.br.operacoesemandamento.presenter.RedemptionPresenter;
import orama.ufv.br.operacoesemandamento.presenter.WithdrawalCcPresenter;
import orama.ufv.br.operacoesemandamento.view.ApplicationFragment;
import orama.ufv.br.operacoesemandamento.view.FundsOperationsActivity;
import orama.ufv.br.operacoesemandamento.view.RedemptionFragment;

/**
 * Created by usuario on 25/08/2017.
 */

public class DetailsDialogUtil {

    public void showDetailsWithdrawalCa(Activity activity, int position){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.details_dialog_ca_withdrawal);

        TextView content1 = (TextView) dialog.findViewById(R.id.tvContent1);
        TextView content2 = (TextView) dialog.findViewById(R.id.tvContent2);
        TextView content3 = (TextView) dialog.findViewById(R.id.tvContent3);
        TextView content4 = (TextView) dialog.findViewById(R.id.tvContent4);
        TextView content5 = (TextView) dialog.findViewById(R.id.tvContent5);
        TextView content6 = (TextView) dialog.findViewById(R.id.tvContent6);
        Button bt = (Button) dialog.findViewById(R.id.buttonDialog);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        content1.setText(WithdrawalCcPresenter.model().get(200).get(position).getBank());
        content2.setText(WithdrawalCcPresenter.model().get(200).get(position).getAgency());
        content3.setText(WithdrawalCcPresenter.model().get(200).get(position).getAccount().getUser());
        content4.setText(WithdrawalCcPresenter.model().get(200).get(position).getTipo());
        if (WithdrawalCcPresenter.model().get(200).get(position).isJointAccount()) content5.setText("Sim");
        else content5.setText("Não");
        content6.setText(WithdrawalCcPresenter.model().get(200).get(position).getRequestDate());
        dialog.show();
    }

    public void cancelWithdrawalCa(final Activity activity, final int position, String conteudo){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cancel_dialog_withdrawal_ca);

        TextView tv = (TextView) dialog.findViewById(R.id.tvCancelar);
        tv.setText(R.string.withdrawalCancelText);

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithdrawalCcPresenter.calcelWithdrawal(position, activity);
                if (WithdrawalCcPresenter.model().get(200).size() == 0) {
                    activity.findViewById(R.id.imgNotFound).setVisibility(View.VISIBLE);
                    activity.findViewById(R.id.tituloCard).setVisibility(View.VISIBLE);
                }
                else {
                    activity.findViewById(R.id.imgNotFound).setVisibility(View.GONE);
                    activity.findViewById(R.id.tituloCard).setVisibility(View.GONE);
                }
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void cancelBuyFixedIncome(final Activity activity, final int position, String conteudo){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cancel_dialog_buy_fixed_income);

        TextView tv = (TextView) dialog.findViewById(R.id.tvCancelar);
        tv.setText(R.string.purchaseCancelText);

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyFixedIncomePresenter.calcelBuyFixedIncome(position, activity);
                if (BuyFixedIncomePresenter.model().get(200).size() == 0) {
                    activity.findViewById(R.id.imgNotFound).setVisibility(View.VISIBLE);
                    activity.findViewById(R.id.tituloCard).setVisibility(View.VISIBLE);
                }
                else {
                    activity.findViewById(R.id.imgNotFound).setVisibility(View.GONE);
                    activity.findViewById(R.id.tituloCard).setVisibility(View.GONE);
                }
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void cancelRedemption(final Activity activity, final int position, final FragmentManager fragment){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cancel_dialog_redemption);

        TextView tv = (TextView) dialog.findViewById(R.id.tvCancelar);
        tv.setText(R.string.redemptionCancelText);

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedemptionPresenter.cancelRedemption(position, activity, fragment);
                FundsOperationsActivity.refresh();
                dialog.cancel();

            }
        });

        dialog.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();
    }

    public void showRedemptionDetails(Activity activity, int position){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.details_dialog_redemption);


        TextView label1 = (TextView) dialog.findViewById(R.id.tvLabel1);
        TextView label2 = (TextView) dialog.findViewById(R.id.tvLabel2);

        TextView content1 = (TextView) dialog.findViewById(R.id.tvContent1);
        TextView content2 = (TextView) dialog.findViewById(R.id.tvContent2);
        TextView content3 = (TextView) dialog.findViewById(R.id.tvContent3);
        TextView content4 = (TextView) dialog.findViewById(R.id.tvContent6);
        Button bt = (Button) dialog.findViewById(R.id.buttonDialog);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        label1.setText(activity.getString(R.string.redemptionConvertionLabel1)+'\n'+activity.getString(R.string.redemptionConvertionLabel2));
        label2.setText(activity.getString(R.string.redemptionPaymentLabel1)+'\n'+activity.getString(R.string.redemptionPaymentLabel2));

        content1.setText(getDisplayDate(RedemptionPresenter.model().get(200).get(position).getConversionDate(),activity));
        content2.setText(getDisplayDate(RedemptionPresenter.model().get(200).get(position).getPaymentInSubAccount(),activity));
        content3.setText(RedemptionPresenter.model().get(200).get(position).getAnticipated());
        content4.setText(RedemptionPresenter.model().get(200).get(position).getRequestDate());
        dialog.show();
    }

    public void cancelApplication(final Activity activity, final int position, String conteudo){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.cancel_dialog_application);

        TextView tv = (TextView) dialog.findViewById(R.id.tvCancelar);
        tv.setText(R.string.applicationCancelText);

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationPresenter.cancelApplication(position, activity);
                FundsOperationsActivity.refresh();
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });




        dialog.show();
    }

    public void showApplicationDetails(Activity activity, int position){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.details_dialog_application);

        TextView content1 = (TextView) dialog.findViewById(R.id.tvContent1);
        TextView content2 = (TextView) dialog.findViewById(R.id.tvContent2);
        Button bt = (Button) dialog.findViewById(R.id.buttonDialog);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        content1.setText(getDisplayDate(ApplicationPresenter.model().get(200).get(position).getQuotaDate(),activity));
        content2.setText(getDisplayDate(ApplicationPresenter.model().get(200).get(position).getRequestDate(),activity));
        dialog.show();
    }

    private String getDisplayDate (String text, Activity activity) {
        return text.substring(0,10)+' '+activity.getString(R.string.scheduleIndicatorLabel)+' '+'\n'+text.substring(10);
    }


}
