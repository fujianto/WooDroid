package com.septianfujianto.woodroid.Services;

import com.septianfujianto.woodroid.Model.Products.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Septian A. Fujianto on 10/31/2016.
 */

public interface IProductsServices {
    @GET("wp-json/wc/v1/products")
    Call<List<Product>> getProducts (
        @Query("oauth_signature_method") String oauth_method,
        @Query("oauth_consumer_key") String consumer_key,
        @Query("oauth_version") String version,
        @Query("oauth_timestamp") String timestamp,
        @Query("oauth_nonce") String nonce,
        @Query("oauth_signature") String signature,
        @Query("page") Integer page,
        @Query("per_page") Integer perpage
    );

    @FormUrlEncoded
    @POST("wp-json/swrc/v1/get")
    Call<Product> getProductById (
            @Field("base_url") String base_url,
            @Field("consumer_key") String consumer_key,
            @Field("consumer_secret") String oauth_version,
            @Field("endpoint") String endpoint
    );

    @GET("wp-json/wc/v1/products")
    Call<List<Product>> filterProducts (
            @Query("oauth_signature_method") String oauth_method,
            @Query("oauth_consumer_key") String consumer_key,
            @Query("oauth_version") String version,
            @Query("oauth_timestamp") String timestamp,
            @Query("oauth_nonce") String nonce,
            @Query("oauth_signature") String signature,
            @Query("page") Integer page,
            @Query("per_page") Integer perpage,
            @Query("search") String search
    );


    @FormUrlEncoded
    @POST("wp-json/swrc/v1/get")
    Call<List<Product>> getAllProducts (
            @Field("base_url") String base_url,
            @Field("consumer_key") String consumer_key,
            @Field("consumer_secret") String oauth_version,
            @FieldMap Map<String, Object> options,
            @Field("endpoint") String endpoint,
            @FieldMap Map<String, Object> parameters
    );
}
