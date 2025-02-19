package com.ufvmobile.pvanet.presentation.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufvmobile.pvanet.Constants;
import com.ufvmobile.pvanet.R;
import com.ufvmobile.pvanet.domain.model.Module;
import com.ufvmobile.pvanet.presentation.ui.activities.ContentActivity;
import com.ufvmobile.pvanet.utils.ItemClickSupport;

import java.util.List;

/**
 * Adapter used to host a list of {@link Module} which every single Module contains a list of
 * {@link Tool}
 */
public class ModulesMainAdapter  extends RecyclerView.Adapter<ModulesMainAdapter.MyViewHolder>   {

    //variables
    private List<Module> mModuleList;
    private Context mContext;

    //views
    private RecyclerView mRecyclerView;
    private ModulesToolsAdapter mAdapter;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView moduleTitle;

        public MyViewHolder(View view) {
            super(view);
            moduleTitle = (TextView) view.findViewById(R.id.cardTitle);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.cardRecyclerView);
        }
    }


    public ModulesMainAdapter(List<Module> moduleList, Context context) {
        this.mModuleList = moduleList;
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

        final Module module = mModuleList.get(position);
        holder.moduleTitle.setText(module.getTitle());

        mAdapter = new ModulesToolsAdapter(module.getModuleContentList());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //handling list clicks
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Intent it = new Intent(mContext, ContentActivity.class);

                        String name = module.getModuleContentList().get(position).getTitle();
                        int courseId = module.getCourseId();
                        int moduleId = module.getId();
                        int toolId = module.getModuleContentList().get(position).getId();
                        it.putExtra(Constants.KEY_TOOL_NAME, name);
                        it.putExtra(Constants.KEY_COURSE_ID, courseId);
                        it.putExtra(Constants.KEY_MODULE_ID, moduleId);
                        it.putExtra(Constants.KEY_TOOL_ID, toolId);

                        //flag used  because calling startActivity outside an Activity
                        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        mContext.startActivity(it);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return mModuleList.size();
    }




}
