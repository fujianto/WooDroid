
package com.septianfujianto.woodroid.Model.Shipping;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RajaongkirShipping {

    @SerializedName("rajaongkir")
    @Expose
    private Rajaongkir rajaongkir;

    /**
     * 
     * @return
     *     The rajaongkir
     */
    public Rajaongkir getRajaongkir() {
        return rajaongkir;
    }

    /**
     * 
     * @param rajaongkir
     *     The rajaongkir
     */
    public void setRajaongkir(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

}
