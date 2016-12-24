
package com.septianfujianto.woodroid.Model.Shipping;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.septianfujianto.woodroid.Model.Shipping.Cost;

public class Result {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("costs")
    @Expose
    private List<Cost> costs = null;

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The costs
     */
    public List<Cost> getCosts() {
        return costs;
    }

    /**
     * 
     * @param costs
     *     The costs
     */
    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

}
