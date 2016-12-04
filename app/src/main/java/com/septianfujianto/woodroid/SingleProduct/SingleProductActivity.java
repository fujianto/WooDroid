package com.septianfujianto.woodroid.SingleProduct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Item;
import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Services.IWoocommerceServices;
import com.septianfujianto.woodroid.SingleProduct.UI.ProductDetailTableAdapter;
import com.septianfujianto.woodroid.Utils.SquaredImageView;
import com.septianfujianto.woodroid.Utils.Utils;
import com.squareup.picasso.Picasso;
import com.woocommerse.OAuth1.services.TimestampServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.gits.baso.BasoProgressView;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.septianfujianto.woodroid.Config.HEIGHT_UNITS;
import static com.septianfujianto.woodroid.Config.WEIGHT_UNITS;


public class SingleProductActivity extends AppCompatActivity {
    private Context mContext;
    protected TextView productTitle, productPrice, productDesc;
    protected SquaredImageView featuredImage;
    protected Button btnAddToCart;
    protected IWoocommerceServices service;
    protected int productId;
    protected BasoProgressView basoProgressView;
    protected LinearLayout mainContent;
    protected Map<String, Object> options;
    protected Map<String, Object> parameters;
    protected ArrayList<Item> tableData;
    private ProductDetailTableAdapter adapter;
    private LinearLayoutManager manager;
    private RecyclerView mTableDetailRcv;
    private Realm realm;
    private String prodName;
    private String prodPrice;
    private String prodWeight;
    private int prodQty;
    private String prodDesc;
    private String prodFeaturedImage;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        getSupportActionBar().setTitle("Product Detail");

        mContext = this;
        productTitle = (TextView) findViewById(R.id.productTitle);
        productDesc = (TextView) findViewById(R.id.productDesc);
        productPrice = (TextView) findViewById(R.id.productPrice);
        featuredImage = (SquaredImageView) findViewById(R.id.featuredImage);
        btnAddToCart = (Button) findViewById(R.id.btnAddToCart);
        basoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);
        mainContent = (LinearLayout)  findViewById(R.id.mainContent);
        mTableDetailRcv = (RecyclerView) findViewById(R.id.rcv_table_detail);
        helper = new RealmHelper(this);

        Bundle extras = getIntent().getExtras();

        btnAddToCart.setVisibility(View.INVISIBLE);

        if (extras != null) {
            tableData = new ArrayList<>();
            adapter = new ProductDetailTableAdapter(this, tableData);
            manager = new LinearLayoutManager(this);
            mTableDetailRcv.setHasFixedSize(true);
            mTableDetailRcv.setAdapter(adapter);
            mTableDetailRcv.setLayoutManager(manager);

            productId = extras.getInt("productId");
            getProductById(productId);

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToCart();
                }
            });
        }
    }

    private void addToCart() {
        Toast.makeText(mContext, prodName+" Added to Cart "+"prodId="+productId+"prodprice="+prodPrice+"prodQty=1"+"prodPrice="+prodPrice, Toast.LENGTH_SHORT).show();
        int itemSize = 0;

        if (helper.getCartItemsByProductId(productId) != null) {
            itemSize = helper.getCartItemsByProductId(productId).size();
            System.out.println("Size by ID: " + itemSize);
        }

        if (itemSize > 0) {
            int newQty = Integer.valueOf(helper.getCartItemsByProductId(productId).get(0).getProductQty()) + 1;
            helper.updateCartItemByProductId(productId, null, newQty, null);
        } else {
            helper.addItemToCart(new TimestampServiceImpl().getNonce(), "0", productId, prodName, 1, Double.valueOf(prodPrice), prodFeaturedImage);
        }

    }

    private void getProductById(int productId) {
        options = new HashMap<>();
        parameters = new HashMap<>();

        options.put("options[wp_api]", true);
        options.put("options[version]", "wc/v1");
        parameters.put("parameters[page]", "1");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.SITE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IWoocommerceServices.class);
        Call<Product> call = service.getItem(
                Config.SITE_URL, Config.COSTUMER_KEY, Config.COSTUMER_SECRET,
                options, "products/"+productId, parameters
        );

        mainContent.setVisibility(View.INVISIBLE);
        basoProgressView.startProgress();

        call.enqueue(new Callback<Product>() {

            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                basoProgressView.stopAndGone();
                mainContent.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    prodName = response.body().getName();
                    prodPrice = response.body().getPrice();
                    prodWeight = response.body().getWeight();
                    prodDesc = response.body().getDescription();
                    prodFeaturedImage = response.body().getImages().get(0).getSrc();

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

                    btnAddToCart.setVisibility(View.VISIBLE);
                    bindResults(prodName, prodPrice, prodWeight,  prodDesc, prodFeaturedImage);

                    /* Bind Data to Product detail table*/
                    prodQty = 1;

                    tableData.add(new Item("Quantity", String.valueOf(prodQty)+" pcs"));
                    tableData.add(new Item("Weight", response.body().getWeight()+" "+WEIGHT_UNITS));

                    if (response.body().getDimensions().getLength() != "") {
                        tableData.add(new Item("Length", response.body().getDimensions().getLength()+" "+HEIGHT_UNITS));
                        tableData.add(new Item("Width", response.body().getDimensions().getWidth()+" "+HEIGHT_UNITS));
                        tableData.add(new Item("Height", response.body().getDimensions().getHeight()+" "+HEIGHT_UNITS));
                    }

                    tableData.add(new Item("Date Modified", response.body().getDateModified()));
                    tableData.add(new Item("Categories", prodCat));
                    tableData.add(new Item("Tags", prodTag));
                    adapter.notifyDataSetChanged();

                } else {
                    basoProgressView.stopAndError("Oops, something wrong: "+response.raw());
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                t.printStackTrace();
                basoProgressView.stopAndError("Oops, failure on: "+t.getMessage());
            }
        });
    }

    private void bindResults(String prodName, String prodPrice, String prodWeight, String prodDesc, String prodFeaturedImage) {
        String formattedPrice = Utils.formatCurrency(
                Double.valueOf(prodPrice), Config.CURRENCY_SYMBOL,
                Config.GROUPING_SPEARATOR, Config.DECIMAL_SEPARATOR);

        productTitle.setText(prodName);
        productDesc.setText(Html.fromHtml(prodDesc));
        productPrice.setText(formattedPrice);
        Picasso.with(this)
                .load(prodFeaturedImage)
                .placeholder(R.drawable.thumbnail)
                .into(featuredImage);

        getSupportActionBar().setTitle(prodName);
    }
}

