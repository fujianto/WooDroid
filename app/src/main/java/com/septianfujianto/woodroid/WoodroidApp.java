package com.septianfujianto.woodroid;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Septian A. Fujianto on 11/30/2016.
 */

public class WoodroidApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmMigration migration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                RealmSchema schema = realm.getSchema();

                if (oldVersion == 1) {
                    schema.create("Cart")
                            .addField("productId", int.class)
                            .addField("cartId", String.class)
                            .addField("customerId", String.class)
                            .addField("productName", String.class);
                    oldVersion++;
                }

                if (oldVersion == 2) {
                    schema.get("Cart")
                            .addField("productImage", String.class);
                    oldVersion++;
                }
            }
        };

        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(2) // Must be bumped when the schema changes
                .migration(migration) // Migration to run instead of throwing an exception
                .build();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
