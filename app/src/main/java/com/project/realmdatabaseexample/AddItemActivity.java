package com.project.realmdatabaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.project.realmdatabaseexample.db.RealmController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gleb on 6/13/17.
 */

public class AddItemActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.description)
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addButton)
    public void onAddClick() {
        new RealmController(this).addInfo(title.getText().toString(), description.getText().toString());
        finish();
    }
}
