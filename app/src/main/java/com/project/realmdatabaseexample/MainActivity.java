package com.project.realmdatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.project.realmdatabaseexample.adapter.RealmAdapter;
import com.project.realmdatabaseexample.db.RealmController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RealmAdapter.OnClickListener {

    private static final int INTENT_REQUEST = 123;

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IS_EDIT = "isEdit";

    @BindView(R.id.listView)
    ListView recyclerView;

    private RealmAdapter realmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupAdapter();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                startActivityForResult(new Intent(this, AddItemActivity.class), INTENT_REQUEST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        realmAdapter = new RealmAdapter(this, new RealmController(this).getInfo());
        realmAdapter.setOnClickListener(this);
        recyclerView.setAdapter(realmAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setupAdapter();
    }

    @Override
    public void onEditButtonCLick(int id, String title, String description) {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra(IS_EDIT, true);
        intent.putExtra(ID, id);
        intent.putExtra(TITLE, title);
        intent.putExtra(DESCRIPTION, description);
        startActivity(intent);
    }
}
