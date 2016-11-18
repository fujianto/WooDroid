package com.septianfujianto.woodroid.Main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.septianfujianto.woodroid.Products.ProductsActivity;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Services.IOrderServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    protected Button btnDefault, btnAuth;
    protected IOrderServices service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDefault = (Button)  findViewById(R.id.btnDefault);
        btnAuth = (Button) findViewById(R.id.authenticatedSite);

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductsActivity.class);
                startActivity(intent);
            }
        });

        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "OAUTH TEST CLICk", Toast.LENGTH_SHORT).show();
                testGetRequest();
            }
        });
    }

    public void testGetRequest() {
        String url = "http://zucharest.16mb.com/wp-json/swrc/v1/get";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("base_url", "http://zucharest.16mb.com")
                .add("consumer_key", "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d")
                .add("consumer_secret", "cs_556ca0d25608e767fe7f74c7fea6060fae313999")
                .add("options[wp_api]", "true")
                .add("options[version]", "wc/v1")
                .add("endpoint", "products")
                .add("parameters[page]", "1")
                .add("parameters[per_page]", "2")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("RESPONSE CODE: "+response.code());
                Logger.json(response.body().string());
            }
        });
    }

    public void testDeleteRequest() {
        String url = "http://zucharest.16mb.com/wp-json/swrc/v1/delete/103";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("base_url", "http://zucharest.16mb.com")
                .add("consumer_key", "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d")
                .add("consumer_secret", "cs_556ca0d25608e767fe7f74c7fea6060fae313999")
                .add("options[wp_api]", "true")
                .add("options[version]", "wc/v1")
                .add("endpoint", "products")
                .add("parameters[force]", "true")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("RESPONSE CODE: "+response.code());
                System.out.println("RESPONSE: "+response.body().string());
            }
        });
    }

    public void testPostRequest() {
        String url = "http://zucharest.16mb.com/wp-json/swrc/v1/post";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("base_url", "http://zucharest.16mb.com")
                .add("consumer_key", "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d")
                .add("consumer_secret", "cs_556ca0d25608e767fe7f74c7fea6060fae313999")
                .add("options[wp_api]", "true")
                .add("options[version]", "wc/v1")
                .add("endpoint", "products")
                .add("data[name]", "Ultra T-Shirt")
                .add("data[regular_price]", "125000")
                .add("data[type]", "simple")
                .add("data[description]", "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("RESPONSE CODE: "+response.code());
                System.out.println("RESPONSE: "+response.body().string());
            }
        });
    }

    public void testUpdateRequest() {
        String url = "http://zucharest.16mb.com/wp-json/swrc/v1/put/44";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("base_url", "http://zucharest.16mb.com")
                .add("consumer_key", "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d")
                .add("consumer_secret", "cs_556ca0d25608e767fe7f74c7fea6060fae313999")
                .add("options[wp_api]", "true")
                .add("options[version]", "wc/v1")
                .add("endpoint", "products")
                .add("data[regular_price]", "55000")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("RESPONSE CODE: "+response.code());
                System.out.println("RESPONSE: "+response.body().string());
            }
        });
    }
}
