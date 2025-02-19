package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Adapter used to show the navigation drawer options
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private Context mContext;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_row_nav_drawer, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = mData.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}