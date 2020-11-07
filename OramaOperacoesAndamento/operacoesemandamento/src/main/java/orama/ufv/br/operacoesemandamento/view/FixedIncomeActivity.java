package orama.ufv.br.operacoesemandamento.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.BuyFixedIncomePresenter;
import orama.ufv.br.operacoesemandamento.view.util.BuyFixedIncomeCardsAdapter;
import orama.ufv.br.operacoesemandamento.view.util.NavegacaoUtil;
import orama.ufv.br.operacoesemandamento.view.util.SpinnerAdapter;

public class FixedIncomeActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_income);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view1);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BuyFixedIncomeCardsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        Intent it = getIntent();

        spinner = (Spinner) findViewById(R.id.spinner);
        final SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);
        spinner.setSelection(it.getIntExtra("spinner",0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                BuyFixedIncomePresenter.getBuyFixedIncome(adapter.getItem(position).toString(), getBaseContext());
                mAdapter = new BuyFixedIncomeCardsAdapter(FixedIncomeActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                if(BuyFixedIncomePresenter.model().get(200).size() > 0) {
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
