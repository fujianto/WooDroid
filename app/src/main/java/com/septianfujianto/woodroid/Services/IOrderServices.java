package com.septianfujianto.woodroid.Services;

import com.septianfujianto.woodroid.Model.Orders.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public interface IOrderServices {

    @POST("wp-json/wc/v1/orders")
    Call<List<Order>> postOrders (
            @Header("oauth_signature_method") String oauth_method,
            @Header("oauth_consumer_key") String consumer_key,
            @Header("oauth_version") String version,
            @Header("oauth_timestamp") String timestamp,
            @Header("oauth_nonce") String nonce,
            @Header("oauth_signature") String signature,
            @Body String payment_method
    );


}
