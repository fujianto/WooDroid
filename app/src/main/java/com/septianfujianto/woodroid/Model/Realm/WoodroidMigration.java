package com.septianfujianto.woodroid.Model.Realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Septian A. Fujianto on 12/6/2016.
 */

public class WoodroidMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();


        if (oldVersion == 0) {
            RealmObjectSchema cartSchema = schema.get("Cart");

            cartSchema.addField("testField", String.class);

            oldVersion++;
        }

        if (oldVersion == 1) {
            RealmObjectSchema cartSchema = schema.get("Cart");

            cartSchema.removeField("testField");

            oldVersion++;
        }

        if (oldVersion == 2) {
            RealmObjectSchema cartSchema = schema.get("Cart");

            cartSchema.addField("productStock", int.class);

            oldVersion++;
        }

        if (oldVersion == 3) {
            schema.create("City")
                    .addPrimaryKey("city_id")
                    .addField("city_id", int.class)
                    .addField("type", String.class)
                    .addField("city_name", String.class)
                    .addField("postal_code", int.class)
                    .addField("province_id", int.class)
                    .addField("province", String.class);

            schema.create("Province")
                    .addPrimaryKey("province_id")
                    .addField("province_id",int.class)
                    .addField("province", String.class)
                    .addRealmListField("cities", schema.get("City"));

            oldVersion++;
        }

        if (oldVersion == 4) {
            RealmObjectSchema cartSchema = schema.get("Cart");
            cartSchema.addField("productWeight", int.class);

            oldVersion++;
        }
    }
}
