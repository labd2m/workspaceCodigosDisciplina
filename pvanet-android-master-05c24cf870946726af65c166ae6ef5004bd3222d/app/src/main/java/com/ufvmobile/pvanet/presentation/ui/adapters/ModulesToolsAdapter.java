package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Tool;

import java.util.List;


/**
 *  Adapter used to show a list of {@link Tool}
 */
public class ModulesToolsAdapter extends RecyclerView.Adapter<ModulesToolsAdapter.MyViewHolder>  {

    private List<Tool> mModuleToolsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView toolTitle;
        public View divider;

        public MyViewHolder(View view) {
            super(view);
            toolTitle = (TextView) view.findViewById(R.id.itemTitle);
            divider =  view.findViewById(R.id.itemDivider);
        }
    }


    public ModulesToolsAdapter(List<Tool> moduleToolsList) {
        this.mModuleToolsList = moduleToolsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_card_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String toolTitle = mModuleToolsList.get(position).getTitle();
        holder.toolTitle.setText(toolTitle);

        //last item of the list don't need a divider
        if(position == mModuleToolsList.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mModuleToolsList.size();
    }
}