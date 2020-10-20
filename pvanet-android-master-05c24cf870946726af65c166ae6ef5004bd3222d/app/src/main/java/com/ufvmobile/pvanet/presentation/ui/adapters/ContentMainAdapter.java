package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Topic;
import com.ufvmobile.pvanet.utils.ItemClickSupport;

import java.util.List;

/**
 * Adapter used to host a list of {@link Topic} which every single Topic contains a list of
 * {@link com.ufvmobile.pvanet.domain.model.Content}
 */
public class ContentMainAdapter extends RecyclerView.Adapter<ContentMainAdapter.MyViewHolder>   {

    //variables
    private List<Topic> mContentList;
    private Context mContext;

    //views
    RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView moduleTitle;

        public MyViewHolder(View view) {
            super(view);
            moduleTitle = (TextView) view.findViewById(R.id.cardTitle);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.cardRecyclerView);
        }
    }


    public ContentMainAdapter(List<Topic> contentList, Context context) {
        this.mContentList = contentList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_card_with_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //used because itens was becoming invisible in the list
        holder.setIsRecyclable(false);

        final Topic topic = mContentList.get(position);
        holder.moduleTitle.setText(topic.getDescription());

        mAdapter = new ContentAdapter(topic.getContentList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //handling list clicks
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        String url = topic.getContentList().get(position).getUrl();
                        if(url.startsWith("http://") || url.startsWith("https://")) {
                            final Intent it = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(it);

                        } else if(url.startsWith("../")) {

                            //TODO: método provisório
                            url = url.substring(2);
                            Uri.Builder builder = new Uri.Builder();
                            builder.scheme("https")
                                    .authority("www2.cead.ufv.br")
                                    .appendPath("sistemas")
                                    .appendPath("pvanet");
                            url = builder.toString().concat(url);
                            final Intent it = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(it);
                        }
                        else {

                            Log.e("CONTEUDOS", "O conteúdo " + topic.getContentList().
                                    get(position).getTitle() + "não possui url válida");
                        }
                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }




}
