package com.septianfujianto.woodroid.Woocommerce;

import android.util.Log;

import com.septianfujianto.woodroid.Woocommerce.Oauth.OAuthSignature;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.Build.VERSION_CODES.M;
import static com.septianfujianto.woodroid.R.menu.products;

/**
 * Created by Septian A. Fujianto on 11/14/2016.
 */

public class DefaultHttpClient implements HttpClient{

    @Override
    public Map get(String url) {
        return null;
    }

    @Override
    public List getAll(String url) {
        List<String> result = new ArrayList<>();
        System.out.println("GETALLURL "+url);

        /*try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

           client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        Log.e("btnAddToCart", "OKHTTP RESPONSE");
                        System.out.println("==================");
                        Log.e("OKHTTP RESPONSE", response.body().string());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return result;
    }


    @Override
    public Map post(String url, Map<String, String> params, Map<String, Object> object) {
        List<NameValuePair> postParameters = getParametersAsList(params);

        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("name", "CLIEN Premium XA")
                    .add("type", "simple")
                    .add("regular_price", "21099")
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
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        Log.e("btnAddToCart", "OKHTTP RESPONSE");
                        System.out.println("==================");
                        Log.e("OKHTTP RESPONSE", response.body().string());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<NameValuePair> getParametersAsList(Map<String, String> params) {
        List<NameValuePair> postParameters = new ArrayList<>();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                postParameters.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return postParameters;
    }

    @Override
    public Map put(String url, Map<String, String> params, Map<String, Object> object) {
        return null;
    }

    @Override
    public Map delete(String url, Map<String, String> params) {
        return null;
    }

}
