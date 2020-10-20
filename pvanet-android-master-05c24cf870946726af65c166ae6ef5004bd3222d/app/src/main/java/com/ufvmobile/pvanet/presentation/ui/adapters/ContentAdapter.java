package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Content;
import com.ufvmobile.pvanet.utils.IconResolver;

import java.util.List;

/**
 *   Adapter used to show a list of {@link Content}
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder>  {

    private List<Content> mContentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView contentTitle;
        public View divider;
        public ImageView mIcon;

        public MyViewHolder(View view) {
            super(view);
            contentTitle = (TextView) view.findViewById(R.id.itemTitle);
            divider =  view.findViewById(R.id.itemDivider);
            mIcon = (ImageView) view.findViewById(R.id.itemIcon);
        }
    }


    public ContentAdapter(List<Content> contentList) {
        this.mContentList = contentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_card_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = mContentList.get(position).getTitle();
        String fileName = mContentList.get(position).getFileName();
        int icon = IconResolver.getIconByExtension(fileName);
        holder.mIcon.setImageResource(icon);
        holder.contentTitle.setText(title);

        //last item of the list don't need a divider
        if(position == mContentList.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }
}