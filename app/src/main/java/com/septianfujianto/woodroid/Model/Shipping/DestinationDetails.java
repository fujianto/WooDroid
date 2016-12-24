
package com.septianfujianto.woodroid.Model.Shipping;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestinationDetails {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;

    /**
     * 
     * @return
     *     The cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 
     * @param cityId
     *     The city_id
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * @return
     *     The provinceId
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 
     * @param provinceId
     *     The province_id
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 
     * @return
     *     The province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 
     * @param province
     *     The province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 
     * @param cityName
     *     The city_name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postal_code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
