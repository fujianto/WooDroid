package com.septianfujianto.woodroid.Model.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public class ShippingLine {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("method_title")
    @Expose
    private String methodTitle;
    @SerializedName("method_id")
    @Expose
    private String methodId;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("taxes")
    @Expose
    private List<Object> taxes = new ArrayList<Object>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The methodTitle
     */
    public String getMethodTitle() {
        return methodTitle;
    }

    /**
     *
     * @param methodTitle
     * The method_title
     */
    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    /**
     *
     * @return
     * The methodId
     */
    public String getMethodId() {
        return methodId;
    }

    /**
     *
     * @param methodId
     * The method_id
     */
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    /**
     *
     * @return
     * The total
     */
    public String getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The totalTax
     */
    public String getTotalTax() {
        return totalTax;
    }

    /**
     *
     * @param totalTax
     * The total_tax
     */
    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    /**
     *
     * @return
     * The taxes
     */
    public List<Object> getTaxes() {
        return taxes;
    }

    /**
     *
     * @param taxes
     * The taxes
     */
    public void setTaxes(List<Object> taxes) {
        this.taxes = taxes;
    }

}
