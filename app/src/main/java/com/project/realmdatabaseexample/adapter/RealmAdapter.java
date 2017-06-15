package com.project.realmdatabaseexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.realmdatabaseexample.R;
import com.project.realmdatabaseexample.db.RealmController;
import com.project.realmdatabaseexample.db.model.RealmModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by gleb on 6/13/17.
 */

public class RealmAdapter extends RealmBaseAdapter<RealmModel> {

    private OnClickListener onClickListener;

    public RealmAdapter(Context context, RealmResults<RealmModel> realmResults) {
        super(context, realmResults, true);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_realm, parent, false);
        RealmViewHolder viewHolder = new RealmViewHolder(convertView);

        final RealmModel model = getRealmResults().get(position);
        viewHolder.title.setText(model.getTitle());
        viewHolder.description.setText(model.getTitle());
        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RealmController(context).removeItemById(getRealmResults().get(position).getId());
            }
        });
        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onEditButtonCLick(model.getTitle(), model.getDescription());
            }
        });
        return convertView;
    }

    public RealmResults<RealmModel> getRealmResults() {
        return realmResults;
    }

    public class RealmViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.editButton)
        ImageView editButton;

        @BindView(R.id.removeButton)
        ImageView removeButton;

        public RealmViewHolder(final View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onEditButtonCLick(String title, String description);
    }
}