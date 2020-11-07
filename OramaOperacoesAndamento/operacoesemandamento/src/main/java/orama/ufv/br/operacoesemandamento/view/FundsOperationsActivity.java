package orama.ufv.br.operacoesemandamento.view;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.view.util.NavegacaoUtil;
import orama.ufv.br.operacoesemandamento.view.util.SampleFragmentPagerAdapter;
import orama.ufv.br.operacoesemandamento.view.util.SpinnerAdapter;

public class FundsOperationsActivity extends AppCompatActivity {

    private Spinner spinner;
    List<String> listSpinner = new ArrayList<String>();
    private static TabLayout tabLayout;
    private ViewPager viewPager;
    static SampleFragmentPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds_operations);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(), FundsOperationsActivity.this);
        viewPager.setAdapter(pagerAdapter);


        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                tab.select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Iterate over all tabs and set the custom view
        setupViewPager(pagerAdapter);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }


        spinner = (Spinner) findViewById(R.id.spinner);
        final SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);

        Intent it = getIntent();
        spinner.setSelection(it.getIntExtra("spinner",0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((ApplicationFragment) pagerAdapter.getItem(0)).setRecyclerView(adapter.getItem(position).toString());
                ((RedemptionFragment) pagerAdapter.getItem(1)).setRecyclerView(adapter.getItem(position).toString());

                TextView ctv = (TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.tv);

                if(((ApplicationFragment) pagerAdapter.getItem(0)).getCount() > 0) {
                    ctv.setVisibility(View.VISIBLE);
                    if(((ApplicationFragment) pagerAdapter.getItem(0)).getCount() > 99) {
                        ctv.setText("99+");
                    } else {
                        ctv.setText(((ApplicationFragment) pagerAdapter.getItem(0)).getCount()+"");
                    }
                } else {
                    ctv.setVisibility(View.GONE);
                }

                ctv = (TextView) tabLayout.getTabAt(1).getCustomView().findViewById(R.id.tv);

                if(((RedemptionFragment) pagerAdapter.getItem(1)).getCount() > 0) {
                    ctv.setVisibility(View.VISIBLE);
                    if(((RedemptionFragment) pagerAdapter.getItem(1)).getCount() > 99) {
                        ctv.setText("99+");
                    } else {
                        ctv.setText(((RedemptionFragment) pagerAdapter.getItem(1)).getCount()+"");
                    }
                } else {
                    ctv.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        tabLayout.getTabAt(0).getCustomView().findViewById(R.id.tv).setVisibility(View.GONE);
    }

    private void setupViewPager(SampleFragmentPagerAdapter adapter1) {
        adapter1.addFragment(new ApplicationFragment(), getString(R.string.applicationTitle));
        adapter1.addFragment(new RedemptionFragment(), getString(R.string.redemptionTitle));
        viewPager.setAdapter(adapter1);
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

    public static void refresh(){

        //pagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(), FundsOperationsActivity.this);

        TextView ctv = (TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.tv);

        // img = (ImageView) tabLayout.getTabAt(0).findViewById(R.id.imgNotFound);

        if(((ApplicationFragment) pagerAdapter.getItem(0)).getCount() > 0) {
            ctv.setVisibility(View.VISIBLE);
            if(((ApplicationFragment) pagerAdapter.getItem(0)).getCount() > 99) {
                ctv.setText("99+");
            } else {
                ctv.setText(((ApplicationFragment) pagerAdapter.getItem(0)).getCount()+"");
            }
        } else {
            ctv.setVisibility(View.GONE);
        }
        ((ApplicationFragment) pagerAdapter.getItem(0)).setRecyclerViewAfterCancelling();

        ctv = (TextView) tabLayout.getTabAt(1).getCustomView().findViewById(R.id.tv);

        if(((RedemptionFragment) pagerAdapter.getItem(1)).getCount() > 0) {
            ctv.setVisibility(View.VISIBLE);
            if(((RedemptionFragment) pagerAdapter.getItem(1)).getCount() > 99) {
                ctv.setText("99+");
            } else {
                ctv.setText(((RedemptionFragment) pagerAdapter.getItem(1)).getCount()+"");
            }
        } else {
            ctv.setVisibility(View.GONE);
        }
        ((RedemptionFragment) pagerAdapter.getItem(1)).setRecyclerViewAfterCancelling();
    }

}