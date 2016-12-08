package com.septianfujianto.woodroid;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.septianfujianto.woodroid.Model.Realm.WoodroidMigration;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Septian A. Fujianto on 11/30/2016.
 */

public class WoodroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(4) // Must be bumped when the schema changes
                .migration(new WoodroidMigration()) // WoodroidMigration to run instead of throwing an exception
                .build();

        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
