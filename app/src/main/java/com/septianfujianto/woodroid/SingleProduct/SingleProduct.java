package com.septianfujianto.woodroid.SingleProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.Products.ProductsActivity;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Services.IProductsServices;
import com.septianfujianto.woodroid.Utils.SquaredImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.gits.baso.BasoProgressView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SingleProduct extends AppCompatActivity {
    protected TextView productTitle, productPrice, productWeight, productDesc, productCat, productTag;
    protected SquaredImageView featuredImage;
    protected Button btnAddToCart;
    protected IProductsServices service;
    protected int productId;
    protected BasoProgressView basoProgressView;
    protected LinearLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        getSupportActionBar().setTitle("Product Detail");

        productTitle = (TextView) findViewById(R.id.productTitle);
        productCat = (TextView) findViewById(R.id.productCat);
        productDesc = (TextView) findViewById(R.id.productDesc);
        productPrice = (TextView) findViewById(R.id.productPrice);
        productWeight = (TextView) findViewById(R.id.productWeight);
        productTag = (TextView) findViewById(R.id.productTag);
        featuredImage = (SquaredImageView) findViewById(R.id.featuredImage);
        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);
        basoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);
        mainContent = (LinearLayout)  findViewById(R.id.mainContent);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            productId = extras.getInt("productId");
            getProductById(productId);

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "ADD TO CART CLICKED", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void getProductById(int productId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("parameters[page]", "1");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zucharest.16mb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IProductsServices.class);
        Call<Product> call = service.getProductById(
                "http://zucharest.16mb.com/",
                "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d",
                "cs_556ca0d25608e767fe7f74c7fea6060fae313999",
                "products/"+productId
        );

        mainContent.setVisibility(View.INVISIBLE);
        basoProgressView.startProgress();

        call.enqueue(new Callback<Product>() {

            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                basoProgressView.stopAndGone();
                mainContent.setVisibility(View.VISIBLE);

                if (response.code() == 200) {
                    String prodName = response.body().getName();
                    String prodPrice = response.body().getPrice();
                    String prodWeight = response.body().getWeight();
                    String prodDesc = response.body().getDescription();
                    String prodFeaturedImage = response.body().getImages().get(0).getSrc();

                    int prodCatSize = response.body().getCategories().size();
                    String[] cat = new String[prodCatSize];

                    int prodTagSize =  response.body().getTags().size();
                    String[] tag = new String[prodTagSize];

                    for (int i= 0; i <  response.body().getCategories().size(); i++) {
                        cat[i] = response.body().getCategories().get(i).getName();
                    }

                    for (int i= 0; i < response.body().getTags().size(); i++) {
                        tag[i] = response.body().getTags().get(i).getName();
                    }

                    String prodCat = TextUtils.join(",", cat);
                    String prodTag = TextUtils.join(",", tag);

                    bindResults(prodName, prodPrice, prodWeight,  prodDesc, prodCat ,  prodTag , prodFeaturedImage);
                } else {
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void bindResults(String prodName, String prodPrice, String prodWeight,
                            String prodDesc, String prodCat , String prodTag , String prodFeaturedImage) {

        productTitle.setText(prodName);
        productCat.setText(prodCat);
        productDesc.setText(Html.fromHtml(prodDesc));
        productPrice.setText(ProductsActivity.formatCurrency(Double.valueOf(prodPrice), "Rp ", ".", ","));
        productWeight.setText(prodWeight+" gr");
        productTag.setText(prodTag);
        Picasso.with(this)
                .load(prodFeaturedImage)
                .placeholder(R.drawable.thumbnail)
                .into(featuredImage);


        getSupportActionBar().setTitle(prodName);
    }

    public void getAllProducts(){
        Map<String, Object> options = new HashMap<>();
        Map<String, Object> parameters = new HashMap<>();

        options.put("options[wp_api]", true);
        options.put("options[version]", "wc/v1");

        parameters.put("parameters[page]", 3);
        parameters.put("parameters[per_page]", 2);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zucharest.16mb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(IProductsServices.class);


        Call<List<Product>> call = service.getAllProducts(
                "http://zucharest.16mb.com/",
                "ck_d88c431a0c72079a8e47fb93485f05c43ccfe04d",
                "cs_556ca0d25608e767fe7f74c7fea6060fae313999",
                options, "products/", parameters
        );

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                System.out.println(response.body());
                if(response.code() == 200) {
                    Gson gson = new Gson();
                    Logger.json(gson.toJson(response.body()));

                    for(int i = 0; i < response.body().size(); i++) {
                        System.out.println(response.body().get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

