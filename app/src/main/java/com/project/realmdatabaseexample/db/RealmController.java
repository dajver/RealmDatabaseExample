package com.project.realmdatabaseexample.db;

import android.content.Context;

import com.project.realmdatabaseexample.db.model.RealmModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by gleb on 6/13/17.
 */

public class RealmController {

    private Realm realm;

    public RealmController(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    public void addInfo(String title, String description) {
        realm.beginTransaction();

        RealmModel realmObject = realm.createObject(RealmModel.class);
        int id = getNextKey();
        realmObject.setId(id);
        realmObject.setTitle(title);
        realmObject.setDescription(description);

        realm.commitTransaction();
    }

    public RealmResults<RealmModel> getInfo() {
        ArrayList<RealmModel> realmModels = new ArrayList<>();
        RealmResults<RealmModel> results = realm.where(RealmModel.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            RealmModel u = results.get(i);
            realmModels.add(u);
        }
        return realm.where(RealmModel.class).findAll();
    }

    public void updateInfo(String title, String description) {
        realm.beginTransaction();

        RealmModel realmObject = realm.where(RealmModel.class).findFirst();
        realmObject.setTitle(title);
        realmObject.setDescription(description);

        realm.commitTransaction();
    }

    public void removeItemById(int id) {
        realm.beginTransaction();

        RealmResults<RealmModel> rows = realm.where(RealmModel.class).equalTo("id", id).findAll();
        rows.clear();

        realm.commitTransaction();
    }

    private int getNextKey() {
        return realm.where(RealmModel.class).max("id").intValue() + 1;
    }
}
