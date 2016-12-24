
package com.septianfujianto.woodroid.Model.Shipping;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cost_ {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("etd")
    @Expose
    private String etd;
    @SerializedName("note")
    @Expose
    private String note;

    /**
     * 
     * @return
     *     The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The etd
     */
    public String getEtd() {
        return etd;
    }

    /**
     * 
     * @param etd
     *     The etd
     */
    public void setEtd(String etd) {
        this.etd = etd;
    }

    /**
     * 
     * @return
     *     The note
     */
    public String getNote() {
        return note;
    }

    /**
     * 
     * @param note
     *     The note
     */
    public void setNote(String note) {
        this.note = note;
    }

}
