package com.septianfujianto.woodroid.Main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.septianfujianto.woodroid.Model.Realm.City;
import com.septianfujianto.woodroid.Model.Realm.Province;
import com.septianfujianto.woodroid.Products.ProductsActivity;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Services.IOrderServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.facebook.stetho.inspector.network.PrettyPrinterDisplayType.JSON;
import static com.septianfujianto.woodroid.Config.RAJAONGKIR_KEY;
import static okhttp3.Protocol.get;


public class MainActivity extends AppCompatActivity {
    protected Button btnDefault, btnAuth;
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

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
                Toast.makeText(getApplicationContext(), "RJOKIR CLICk", Toast.LENGTH_SHORT).show();

                Realm realm = Realm.getDefaultInstance();
                RealmResults<Province> dataResults = realm.where(Province.class).findAll();
                RealmResults<City> cityResults = realm.where(City.class).findAll();
                realm.beginTransaction();
                cityResults.deleteAllFromRealm();
                dataResults.deleteAllFromRealm();
                realm.commitTransaction();
            }
        });
    }
    public void testRajaOngkirCity() {
        String url = "http://api.rajaongkir.com/starter/city";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("key", RAJAONGKIR_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Realm realm = Realm.getDefaultInstance();

                    try {
                        JSONObject obj = new JSONObject(response.body().string());
                        JSONArray cityArr = obj.getJSONObject("rajaongkir").getJSONArray("results");
                        realm.beginTransaction();

                        for (int i = 0; i < cityArr.length(); i++) {
                            realm.createOrUpdateObjectFromJson(City.class, cityArr.get(i).toString());
                        }

                        realm.commitTransaction();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void testRajaOngkirProvince() {
        String url = "http://api.rajaongkir.com/starter/province";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("key", RAJAONGKIR_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("RESPONSE CODE: " + response.code());
                    Logger.json(response.body().string());
                    Realm realm = Realm.getDefaultInstance();

                    try {
                        JSONObject obj = new JSONObject(response.body().string());
                        JSONArray provincesArr = obj.getJSONObject("rajaongkir").getJSONArray("results");
                        realm.beginTransaction();

                        for (int i = 0; i < provincesArr.length(); i++) {
                            realm.createOrUpdateObjectFromJson(Province.class, provincesArr.get(i).toString());
                        }

                        realm.commitTransaction();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void testGetCookies() {
        String url = "http://zucharest.16mb.com/?add-to-cart=44";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("RESPONSE CODE: "+response.code());
                System.out.println("RESPONSE Header: "+response.headers());

                Log.i("HSXX ", response.headers().name(7));
                Log.i("HSXX2 ", response.headers().value(7));
                Log.i("HSXX ", response.headers().value(7));
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
