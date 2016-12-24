package com.septianfujianto.woodroid.Services;

import com.septianfujianto.woodroid.Model.Shipping.RajaongkirShipping;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Septian A. Fujianto on 12/9/2016.
 */

public interface IRajaongkirServices {
    @FormUrlEncoded
    @POST("cost")
    Call<RajaongkirShipping> getShippingCost (
        @Header("key") String key,
        @Field("origin") int originId,
        @Field("destination") int destinationId,
        @Field("weight") int weight,
        @Field("courier") String courier
    );
}
