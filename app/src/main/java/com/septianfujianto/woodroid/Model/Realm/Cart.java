package com.septianfujianto.woodroid.Model.Realm;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Septian A. Fujianto on 11/30/2016.
 */

public class Cart extends RealmObject {
    @PrimaryKey
    private int productId;
    private String customerId;
    private String productName;
    private String productImage;
    private int productStock;
    private int productQty;
    private Double productPrice;

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setCart(String customerId, int productId, String productName, int productStock, int productQty, Double productPrice) {
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }


}
