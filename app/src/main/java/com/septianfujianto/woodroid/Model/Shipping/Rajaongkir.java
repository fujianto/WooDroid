
package com.septianfujianto.woodroid.Model.Shipping;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rajaongkir {

    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("origin_details")
    @Expose
    private OriginDetails originDetails;
    @SerializedName("destination_details")
    @Expose
    private DestinationDetails destinationDetails;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    /**
     * 
     * @return
     *     The query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The originDetails
     */
    public OriginDetails getOriginDetails() {
        return originDetails;
    }

    /**
     * 
     * @param originDetails
     *     The origin_details
     */
    public void setOriginDetails(OriginDetails originDetails) {
        this.originDetails = originDetails;
    }

    /**
     * 
     * @return
     *     The destinationDetails
     */
    public DestinationDetails getDestinationDetails() {
        return destinationDetails;
    }

    /**
     * 
     * @param destinationDetails
     *     The destination_details
     */
    public void setDestinationDetails(DestinationDetails destinationDetails) {
        this.destinationDetails = destinationDetails;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
