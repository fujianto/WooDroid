package com.septianfujianto.woodroid.Model.Realm;

import io.realm.Realm;

/**
 * Created by Septian A. Fujianto on 11/30/2016.
 */

public interface IAutoIncrement {
    public void setPrimaryKey(int primaryKey);
    public int getNextPrimaryKey(Realm realm);
}
