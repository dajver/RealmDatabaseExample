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

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_REQUEST = 123;

    @BindView(R.id.listView)
    ListView recyclerView;

    private RealmAdapter realmRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        realmRecyclerView = new RealmAdapter(this, new RealmController(this).getInfo());
        recyclerView.setAdapter(realmRecyclerView);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        realmRecyclerView = new RealmAdapter(this, new RealmController(this).getInfo());
        recyclerView.setAdapter(realmRecyclerView);
    }
}
