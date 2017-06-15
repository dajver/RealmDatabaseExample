package com.project.realmdatabaseexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.project.realmdatabaseexample.db.RealmController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.project.realmdatabaseexample.MainActivity.DESCRIPTION;
import static com.project.realmdatabaseexample.MainActivity.ID;
import static com.project.realmdatabaseexample.MainActivity.IS_EDIT;
import static com.project.realmdatabaseexample.MainActivity.TITLE;

/**
 * Created by gleb on 6/13/17.
 */

public class AddItemActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.description)
    EditText description;

    private boolean isEditMode = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);

        if(getIntent().getExtras() != null) {
            isEditMode = getIntent().getExtras().getBoolean(IS_EDIT);
            title.setText(getIntent().getExtras().getString(TITLE));
            description.setText(getIntent().getExtras().getString(DESCRIPTION));
            id = getIntent().getExtras().getInt(ID);
        }
    }

    @OnClick(R.id.addButton)
    public void onAddClick() {
        if(!isEditMode)
            new RealmController(this).addInfo(title.getText().toString(), description.getText().toString());
        else
            new RealmController(this).updateInfo(id, title.getText().toString(), description.getText().toString());
        finish();
    }
}
