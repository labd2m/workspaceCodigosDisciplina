package orama.ufv.br.operacoesemandamento.view.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import orama.ufv.br.operacoesemandamento.R;

/**
 * Created by usuario on 13/08/2017.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{ "Aplicação", "Resgate"};
    private Context context;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public SampleFragmentPagerAdapter(FragmentManager manager) {
            super(manager);
        }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void removeAllFragments() {
        mFragmentList  = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }


    @Override
    public Fragment getItem(int position) {
    return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
    return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
    return mFragmentTitleList.get(position);
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView ctv = (TextView) v.findViewById(R.id.tv); //TODO: comentado para eliminar a bolinha e fazer voltar a funcionar
        TextView tv = (TextView) v.findViewById(R.id.tab);
        tv.setText(mFragmentTitleList.get(position));
        return v;
    }
}