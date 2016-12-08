package com.septianfujianto.woodroid.Model.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by Septian A. Fujianto on 12/7/2016.
 */

public class City extends RealmObject {
    @PrimaryKey
    private int city_id;
    private String type;
    private String city_name;
    private int postal_code;
    private int province_id;
    private String province;

    public int getCityId() {
        return city_id;
    }

    public void setCityId(int city_id) {
        this.city_id = city_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvinceId(int province_id) {
        this.province_id = province_id;
    }

    public int getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getCityName() {
        return city_name;
    }

    public void setCityName(String city_name) {
        this.city_name = city_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
