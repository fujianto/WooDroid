
package com.septianfujianto.woodroid.Model.Shipping;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cost {

    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private List<Cost_> cost = null;

    /**
     * 
     * @return
     *     The service
     */
    public String getService() {
        return service;
    }

    /**
     * 
     * @param service
     *     The service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The cost
     */
    public List<Cost_> getCost() {
        return cost;
    }

    /**
     * 
     * @param cost
     *     The cost
     */
    public void setCost(List<Cost_> cost) {
        this.cost = cost;
    }

}
