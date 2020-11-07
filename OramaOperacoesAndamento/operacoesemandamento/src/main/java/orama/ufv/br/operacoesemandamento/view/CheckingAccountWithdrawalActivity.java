package orama.ufv.br.operacoesemandamento.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.WithdrawalCcPresenter;
import orama.ufv.br.operacoesemandamento.view.util.NavegacaoUtil;
import orama.ufv.br.operacoesemandamento.view.util.SpinnerAdapter;
import orama.ufv.br.operacoesemandamento.view.util.WithdrawalCcCardsAdapter;

import static orama.ufv.br.operacoesemandamento.R.id.spinner;

public class CheckingAccountWithdrawalActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_withdrawal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_RetiradaCc);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WithdrawalCcCardsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        spinner = (Spinner) findViewById(R.id.spinner);
        final SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);

        Intent it = getIntent();
        spinner.setSelection(it.getIntExtra("spinner",0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                WithdrawalCcPresenter.getWithdrawalCc(adapter.getItem(position).toString(), getBaseContext());
                mAdapter = new WithdrawalCcCardsAdapter(CheckingAccountWithdrawalActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                if(WithdrawalCcPresenter.model().get(200).size() > 0) {
                    findViewById(R.id.imgNotFound).setVisibility(View.GONE);
                    findViewById(R.id.tituloCard).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.imgNotFound).setVisibility(View.VISIBLE);
                    findViewById(R.id.tituloCard).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavegacaoUtil.navegar(this,OperationsInProgressActivity.class,spinner.getSelectedItemPosition());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //trata evento do botão voltar
        NavegacaoUtil.navegar(this,OperationsInProgressActivity.class,spinner.getSelectedItemPosition());
    }

}
