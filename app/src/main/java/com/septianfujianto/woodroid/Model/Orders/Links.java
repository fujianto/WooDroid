package com.septianfujianto.woodroid.Model.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public class Links {

    @SerializedName("self")
    @Expose
    private List<Self> self = new ArrayList<Self>();
    @SerializedName("collection")
    @Expose
    private List<Collection> collection = new ArrayList<Collection>();

    /**
     *
     * @return
     * The self
     */
    public List<Self> getSelf() {
        return self;
    }

    /**
     *
     * @param self
     * The self
     */
    public void setSelf(List<Self> self) {
        this.self = self;
    }

    /**
     *
     * @return
     * The collection
     */
    public List<Collection> getCollection() {
        return collection;
    }

    /**
     *
     * @param collection
     * The collection
     */
    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

}
