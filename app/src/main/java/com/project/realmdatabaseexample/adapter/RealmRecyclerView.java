package com.project.realmdatabaseexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.realmdatabaseexample.R;
import com.project.realmdatabaseexample.db.RealmController;
import com.project.realmdatabaseexample.db.model.RealmModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/13/17.
 */

public class RealmRecyclerView extends RecyclerView.Adapter<RealmRecyclerView.RealmViewHolder>{

    private ArrayList<RealmModel> realmModels = new ArrayList<>();
    private Context context;

    public RealmRecyclerView(Context context, ArrayList<RealmModel> realmModels) {
        this.context = context;
        this.realmModels = realmModels;
    }

    @Override
    public RealmRecyclerView.RealmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_realm, parent, false);
        RealmRecyclerView.RealmViewHolder pvh = new RealmRecyclerView.RealmViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RealmRecyclerView.RealmViewHolder holder, final int position) {
        holder.title.setText(realmModels.get(position).getTitle());
        holder.description.setText(realmModels.get(position).getDescription());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return realmModels.size();
    }

    public class RealmViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.editButton)
        ImageView editButton;

        @BindView(R.id.removeButton)
        ImageView removeButton;

        public RealmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = RealmViewHolder.super.getAdapterPosition();
                    new RealmController(context).removeItemById(realmModels.get(position).getId());
                    RealmRecyclerView.super.notifyItemRemoved(position);
                }
            });
        }
    }
}