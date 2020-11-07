package orama.ufv.br.operacoesemandamento.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import orama.ufv.br.operacoesemandamento.R;
import orama.ufv.br.operacoesemandamento.presenter.ApplicationPresenter;
import orama.ufv.br.operacoesemandamento.view.util.ApplicationFundsCardsAdapter;


/**
 * Created by Fábio on 13/08/2017.
 */

public class ApplicationFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ApplicationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.application_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_application);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new ApplicationFundsCardsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);



        return rootView;
    }

    public void setRecyclerView(String subAccountName) {
        ApplicationPresenter.getApplicationFunds(subAccountName, getActivity());
        mAdapter = new ApplicationFundsCardsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        if(ApplicationPresenter.model().get(200).size() > 0) {
            getActivity().findViewById(R.id.imgNotFoundApplication).setVisibility(View.GONE);
            getActivity().findViewById(R.id.tituloCardApplication).setVisibility(View.GONE);
        } else {
            getActivity().findViewById(R.id.imgNotFoundApplication).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.tituloCardApplication).setVisibility(View.VISIBLE);
        }
    }

    public void setRecyclerViewAfterCancelling() {
        mAdapter = new ApplicationFundsCardsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        if(ApplicationPresenter.model().get(200).size() > 0) {
            getActivity().findViewById(R.id.imgNotFoundApplication).setVisibility(View.GONE);
            getActivity().findViewById(R.id.tituloCardApplication).setVisibility(View.GONE);
        } else {
            getActivity().findViewById(R.id.imgNotFoundApplication).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.tituloCardApplication).setVisibility(View.VISIBLE);
        }
    }

    public int getCount() {
        return mAdapter.getItemCount();
    }
}
