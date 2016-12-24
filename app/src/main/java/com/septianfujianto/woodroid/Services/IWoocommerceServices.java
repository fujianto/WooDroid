package com.septianfujianto.woodroid.Services;

import com.septianfujianto.woodroid.Model.Orders.Order;
import com.septianfujianto.woodroid.Model.Products.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Septian A. Fujianto on 11/18/2016.
 */

public interface IWoocommerceServices {
    @FormUrlEncoded
    @POST("wp-json/swrc/v1/get")
    Call<List<Product>> getAllItems (
            @Field("base_url") String base_url,
            @Field("consumer_key") String consumer_key,
            @Field("consumer_secret") String oauth_version,
            @FieldMap Map<String, Object> options,
            @Field("endpoint") String endpoint,
            @FieldMap Map<String, Object> parameters
    );

    @FormUrlEncoded
    @POST("wp-json/swrc/v1/get")
    Call<Product> getItem (
            @Field("base_url") String base_url,
            @Field("consumer_key") String consumer_key,
            @Field("consumer_secret") String oauth_version,
            @FieldMap Map<String, Object> options,
            @Field("endpoint") String endpoint,
            @FieldMap Map<String, Object> parameters
    );

    @FormUrlEncoded
    @POST("wp-json/swrc/v1/post")
    Call<Order> createOrder (
            @Field("base_url") String base_url,
            @Field("consumer_key") String consumer_key,
            @Field("consumer_secret") String oauth_version,
            @FieldMap Map<String, Object> options,
            @Field("endpoint") String endpoint,
            @FieldMap Map<String, Object> data
    );
}
