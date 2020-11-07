package orama.ufv.br.operacoesemandamento.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.AccountPresenter;
import orama.ufv.br.operacoesemandamento.presenter.ApplicationPresenter;
import orama.ufv.br.operacoesemandamento.presenter.BuyFixedIncomePresenter;
import orama.ufv.br.operacoesemandamento.presenter.RedemptionPresenter;
import orama.ufv.br.operacoesemandamento.presenter.SubAccountsPresenter;
import orama.ufv.br.operacoesemandamento.presenter.WithdrawalCcPresenter;
import orama.ufv.br.operacoesemandamento.view.util.NavegacaoUtil;
import orama.ufv.br.operacoesemandamento.view.util.SpinnerAdapter;

public class OperationsInProgressActivity extends AppCompatActivity{

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner) findViewById(R.id.spinnerOperacoes);
        AccountPresenter.getAccountMoc(1, this);
        SubAccountsPresenter.getSubAccounts("1", this);

        final SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);


        Intent it = getIntent();
        spinner.setSelection(it.getIntExtra("spinner",0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                BuyFixedIncomePresenter.getBuyFixedIncome(adapter.getItem(position).toString(), getBaseContext());
                WithdrawalCcPresenter.getWithdrawalCc(adapter.getItem(position).toString(), getBaseContext());
                ApplicationPresenter.getApplicationFunds(adapter.getItem(position).toString(), getBaseContext());
                RedemptionPresenter.getRedemption(adapter.getItem(position).toString(), getBaseContext());


                CardView cv1 = (CardView) findViewById(R.id.card_view);
                TextView ctv1 = (TextView) cv1.findViewById(R.id.circularTextView);
                if(ApplicationPresenter.model().get(200).size() + RedemptionPresenter.model().get(200).size() > 99) {
                    ctv1.setText("99+");
                } else {
                    ctv1.setText((ApplicationPresenter.model().get(200).size() + RedemptionPresenter.model().get(200).size())+"");
                }

                CardView cv2 = (CardView) findViewById(R.id.card_view2);
                TextView ctv2 = (TextView) cv2.findViewById(R.id.circularTextView2);
                if(BuyFixedIncomePresenter.model().get(200).size() > 99) {
                    ctv2.setText("99+");
                } else {
                    ctv2.setText(BuyFixedIncomePresenter.model().get(200).size() + "");
                }

                CardView cv3 = (CardView) findViewById(R.id.card_view3);
                TextView ctv3 = (TextView) cv3.findViewById(R.id.circularTextView3);
                if(WithdrawalCcPresenter.model().get(200).size() > 99) {
                    ctv3.setText("99+");
                } else {
                    ctv3.setText(WithdrawalCcPresenter.model().get(200).size() + "");
                }

                if(ApplicationPresenter.model().get(200).size() + RedemptionPresenter.model().get(200).size()  == 0 ) {
                    cv1.setVisibility(View.GONE);
                } else {
                    cv1.setVisibility(View.VISIBLE);
                }

                if(BuyFixedIncomePresenter.model().get(200).size() == 0) {
                    cv2.setVisibility(View.GONE);
                } else {
                    cv2.setVisibility(View.VISIBLE);
                }

                if(WithdrawalCcPresenter.model().get(200).size() == 0) {
                    cv3.setVisibility(View.GONE);
                } else {
                    cv3.setVisibility(View.VISIBLE);
                }

                TextView tvInicial = (TextView) findViewById(R.id.tvTexto);
                LinearLayout opNF = (LinearLayout) findViewById(R.id.OperationsNotFound);
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout);
                if (ApplicationPresenter.model().get(200).size() + RedemptionPresenter.model().get(200).size()  == 0 &&
                        BuyFixedIncomePresenter.model().get(200).size() == 0 &&
                        WithdrawalCcPresenter.model().get(200).size() == 0) {
                    Log.i("MainViewTeste","NoOperation");
                    tvInicial.setVisibility(View.GONE);

                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
                    params.height = params.WRAP_CONTENT;
                    layout.setLayoutParams(params);

                    opNF.setVisibility(View.VISIBLE);
                }   else {
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
                    params.height = params.MATCH_PARENT;
                    layout.setLayoutParams(params);
                    Log.i("MainViewTeste","HasOperation");
                    tvInicial.setVisibility(View.VISIBLE);
                    opNF.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void cliqueFundos(View view){

        //torna o resto do cnteudo invisivel
        CardView cv = (CardView) findViewById(R.id.card_view);
        CardView cv2 = (CardView) findViewById(R.id.card_view2);
        CardView cv3 = (CardView) findViewById(R.id.card_view3);
        TextView tv = (TextView) findViewById(R.id.tvTexto);
        cv2.setVisibility(View.GONE);
        cv3.setVisibility(View.GONE);
        cv.findViewById(R.id.circularTextView).setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
        final CardView rl= (CardView) findViewById(R.id.card_view);

        AnimationSet animSet = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation( 0,0, rl.getHeight(), 0);
        translate.setDuration(300);
        final ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) rl.getLayoutParams();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                Log.i("interpolated", interpolatedTime + "");


                params.leftMargin = (int)(params.leftMargin * (1- interpolatedTime));
                params.rightMargin = (int)(params.rightMargin *(1- interpolatedTime));
                rl.setLayoutParams(params);
            }
        };

        translate.setDuration(300);
        a.setDuration(300);
        animSet.addAnimation(a);
        animSet.addAnimation(translate);
        rl.startAnimation(animSet);
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                NavegacaoUtil.navegar(OperationsInProgressActivity.this,FundsOperationsActivity.class,spinner.getSelectedItemPosition());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public void cliqueRendaFixa(View view){
        CardView cv = (CardView) findViewById(R.id.card_view);
        CardView cv2 = (CardView) findViewById(R.id.card_view2);
        CardView cv3 = (CardView) findViewById(R.id.card_view3);
        TextView tv = (TextView) findViewById(R.id.tvTexto);
        cv.setVisibility(View.GONE);
        cv3.setVisibility(View.GONE);

        cv2.findViewById(R.id.circularTextView2).setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
        final CardView rl1= (CardView) findViewById(R.id.card_view2);

        AnimationSet animSet = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation( 0,0, rl1.getY(), 0);

        final ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) rl1.getLayoutParams();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                Log.i("interpolated", interpolatedTime + "");


                params.leftMargin = (int)(params.leftMargin * (1- interpolatedTime));
                params.rightMargin = (int)(params.rightMargin *(1- interpolatedTime));
                rl1.setLayoutParams(params);
            }
        };


        if (BuyFixedIncomePresenter.model().get(200).size() == 0) {
            translate.setDuration(300);
            a.setDuration(300);
        }
        else {
            translate.setDuration(500);
            a.setDuration(500);
        }
        animSet.addAnimation(a);
        animSet.addAnimation(translate);
        rl1.startAnimation(animSet);
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                NavegacaoUtil.navegar(OperationsInProgressActivity.this,FixedIncomeActivity.class,spinner.getSelectedItemPosition());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void cliqueRetiradaCc(View view){
        CardView cv = (CardView) findViewById(R.id.card_view);
        CardView cv2 = (CardView) findViewById(R.id.card_view2);
        CardView cv3 = (CardView) findViewById(R.id.card_view3);
        TextView tv = (TextView) findViewById(R.id.tvTexto);
        cv.setVisibility(View.GONE);
        cv2.setVisibility(View.GONE);

        cv3.findViewById(R.id.circularTextView3).setVisibility(View.GONE);

        tv.setVisibility(View.GONE);
        final CardView rl1= (CardView) findViewById(R.id.card_view3);


        AnimationSet animSet = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation( 0,0, rl1.getY(), 0);
        final ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) rl1.getLayoutParams();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                Log.i("interpolated", interpolatedTime + "");


                params.leftMargin = (int)(params.leftMargin * (1- interpolatedTime));
                params.rightMargin = (int)(params.rightMargin *(1- interpolatedTime));
                rl1.setLayoutParams(params);
            }
        };
        if (BuyFixedIncomePresenter.model().get(200).size() == 0 &&
                WithdrawalCcPresenter.model().get(200).size() == 0) {
            translate.setDuration(300);
            a.setDuration(300);
        }
        else if (BuyFixedIncomePresenter.model().get(200).size() != 0 &&
                WithdrawalCcPresenter.model().get(200).size() != 0) {
            translate.setDuration(700);
            a.setDuration(700);
        }
        else {
            translate.setDuration(500);
            a.setDuration(500);
        }

        animSet.addAnimation(a);
        animSet.addAnimation(translate);
        rl1.startAnimation(animSet);
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                NavegacaoUtil.navegar(OperationsInProgressActivity.this,CheckingAccountWithdrawalActivity.class,spinner.getSelectedItemPosition());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        //trata evento do botão voltar
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        CardView cv = (CardView) findViewById(R.id.card_view);
        CardView cv2 = (CardView) findViewById(R.id.card_view2);
        CardView cv3 = (CardView) findViewById(R.id.card_view3);
        TextView tv = (TextView) findViewById(R.id.tvTexto);
        cv.setVisibility(View.VISIBLE);
        cv2.setVisibility(View.VISIBLE);
        cv3.setVisibility(View.VISIBLE);

        cv3.findViewById(R.id.circularTextView3).setVisibility(View.VISIBLE);
        cv.findViewById(R.id.circularTextView).setVisibility(View.VISIBLE);
        cv2.findViewById(R.id.circularTextView2).setVisibility(View.VISIBLE);

        tv.setVisibility(View.VISIBLE);

    }
}
