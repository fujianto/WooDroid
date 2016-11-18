package com.septianfujianto.woodroid.Model.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public class LineItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("variation_id")
    @Expose
    private Integer variationId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("tax_class")
    @Expose
    private String taxClass;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("subtotal")
    @Expose
    private String subtotal;
    @SerializedName("subtotal_tax")
    @Expose
    private String subtotalTax;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("taxes")
    @Expose
    private List<Object> taxes = new ArrayList<Object>();
    @SerializedName("meta")
    @Expose
    private List<Object> meta = new ArrayList<Object>();

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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     *
     * @param sku
     * The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     *
     * @return
     * The productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     * The product_id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     *
     * @return
     * The variationId
     */
    public Integer getVariationId() {
        return variationId;
    }

    /**
     *
     * @param variationId
     * The variation_id
     */
    public void setVariationId(Integer variationId) {
        this.variationId = variationId;
    }

    /**
     *
     * @return
     * The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     * The taxClass
     */
    public String getTaxClass() {
        return taxClass;
    }

    /**
     *
     * @param taxClass
     * The tax_class
     */
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The subtotal
     */
    public String getSubtotal() {
        return subtotal;
    }

    /**
     *
     * @param subtotal
     * The subtotal
     */
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    /**
     *
     * @return
     * The subtotalTax
     */
    public String getSubtotalTax() {
        return subtotalTax;
    }

    /**
     *
     * @param subtotalTax
     * The subtotal_tax
     */
    public void setSubtotalTax(String subtotalTax) {
        this.subtotalTax = subtotalTax;
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

    /**
     *
     * @return
     * The meta
     */
    public List<Object> getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }

}
