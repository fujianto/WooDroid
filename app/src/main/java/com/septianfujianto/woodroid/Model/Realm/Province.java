package com.septianfujianto.woodroid.Model.Realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Septian A. Fujianto on 12/7/2016.
 */

public class Province extends RealmObject {
    @PrimaryKey
    private int province_id;
    private String province;
    private RealmList<City> cities;

    public RealmList<City> getCities() {
        return cities;
    }

    public void setCities(RealmList<City> cities) {
        this.cities = cities;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvinceId() {
        return province_id;
    }

    public void setProvinceId(int province_id) {
        this.province_id = province_id;
    }
}
