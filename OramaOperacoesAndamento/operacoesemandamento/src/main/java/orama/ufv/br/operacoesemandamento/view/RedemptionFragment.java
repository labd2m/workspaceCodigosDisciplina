package orama.ufv.br.operacoesemandamento.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.RedemptionPresenter;
import orama.ufv.br.operacoesemandamento.view.util.RedemptionCardsAdapter;

/**
 * Created by Fábio on 13/08/2017.
 */

public class RedemptionFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RedemptionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.redemption_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_redemption);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RedemptionCardsAdapter(getActivity(), getFragmentManager());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setRecyclerView(String subAccountName) {
        RedemptionPresenter.getRedemption(subAccountName, getActivity());
        mAdapter = new RedemptionCardsAdapter(getActivity(), getFragmentManager());
        mRecyclerView.setAdapter(mAdapter);

        if(RedemptionPresenter.model().get(200).size() > 0) {
            Log.i("FRAGMENT","passou no if");
            this.getActivity().findViewById(R.id.imgNotFoundRedemption).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.tituloCardRedemption).setVisibility(View.GONE);
        } else {
            Log.i("FRAGMENT","não passou no if");
            this.getActivity().findViewById(R.id.imgNotFoundRedemption).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.tituloCardRedemption).setVisibility(View.VISIBLE);
        }
    }

    public void setRecyclerViewAfterCancelling() {
        mAdapter = new RedemptionCardsAdapter(getActivity(), getFragmentManager());
        mRecyclerView.setAdapter(mAdapter);

        if(RedemptionPresenter.model().get(200).size() > 0) {
            Log.i("FRAGMENT","passou no if");
            this.getActivity().findViewById(R.id.imgNotFoundRedemption).setVisibility(View.GONE);
            this.getActivity().findViewById(R.id.tituloCardRedemption).setVisibility(View.GONE);
        } else {
            Log.i("FRAGMENT","não passou no if");
            this.getActivity().findViewById(R.id.imgNotFoundRedemption).setVisibility(View.VISIBLE);
            this.getActivity().findViewById(R.id.tituloCardRedemption).setVisibility(View.VISIBLE);
        }
    }

    public int getCount() {
        return mAdapter.getItemCount();
    }



}
