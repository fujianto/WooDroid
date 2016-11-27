package com.septianfujianto.woodroid;

import android.util.Log;

import com.woocommerse.OAuth1.services.HMACSha1SignatureService;
import com.woocommerse.OAuth1.services.TimestampServiceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Septian A. Fujianto on 10/31/2016.
 */

public class Config {
    public static final String SITE_URL = "http://zucharest.16mb.com";
    public static final String CURRENCY_SYMBOL = "Rp ";
    public static final char DECIMAL_SEPARATOR = ',';
    public static final char GROUPING_SPEARATOR = '.';
    public static final String WEIGHT_UNITS = " gr";
    public static final String HEIGHT_UNITS = " cm";

    public static final String BASE_SITE = "zucharest.16mb.com";
    public static final String OAUTH_METHOD = "HMAC-SHA1";
    public static final String BASE_URL = "http://"+BASE_SITE+"/wp-json/wc/v1/products";
    public static final String COSTUMER_KEY = "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d";
    public static final String COSTUMER_SECRET = "cs_556ca0d25608e767fe7f74c7fea6060fae313999";
    public static final String METHOD = "GET";//change API method eg POST,PUT, DELETE etc (ONLY FOR THIS EXAMPLE FOR LIB LIKE RETROFIT,OKHTTP, The Are Dynamic Way)
}
