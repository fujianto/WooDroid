package com.septianfujianto.woodroid.Checkout;

/**
 * Created by Septian A. Fujianto on 12/24/2016.
 */

public interface CheckoutCallback {
    void onFailedCourierFetch(int originCityId, int destinationCityId);
}
