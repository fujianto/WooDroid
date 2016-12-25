package com.septianfujianto.woodroid.Confirmation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.septianfujianto.woodroid.R;

public class PaymentConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        getSupportActionBar().setTitle("Payment Confirmation");
    }
}
