package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.News;

import java.util.ArrayList;


/**
 * Adapter used to show a list of {@link News}
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {

    private ArrayList<News> mNewsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNewsTitle;
        public TextView mNewsResume;
        public View divider;

        public MyViewHolder(View view) {
            super(view);
            mNewsResume = (TextView) view.findViewById(R.id.news_resume_txt);
            mNewsTitle = (TextView) view.findViewById(R.id.news_title_txt);
            divider = view.findViewById(R.id.divider);

        }
    }


    public NewsAdapter(ArrayList<News> newsList) {
        this.mNewsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News news = mNewsList.get(position);
        String title = news.getTitle();
        holder.mNewsTitle.setText(title);
        if(news.getResume().equalsIgnoreCase("")) {
            holder.mNewsResume.setVisibility(View.GONE);
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.mNewsResume.setText(news.getResume());
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}