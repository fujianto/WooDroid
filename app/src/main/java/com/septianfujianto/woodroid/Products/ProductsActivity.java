package com.septianfujianto.woodroid.Products;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.Services.IProductsServices;
import com.septianfujianto.woodroid.R;
import com.woocommerse.OAuth1.services.HMACSha1SignatureService;
import com.woocommerse.OAuth1.services.TimestampServiceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import id.gits.baso.BasoProgressView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mProductRcv;
    private Context mContext;
    private List<Product> listProducts;
    private ProductsAdapter adapter;
    protected BasoProgressView basoProgressView;
    protected GridLayoutManager layoutManager;
    int init_page = 1, per_page = 4, run_counter= 0;
    protected boolean loadMoreProduct = true;
    IProductsServices service;
    protected SearchView searchView;
    protected Toolbar toolbar;
    protected SearchManager searchManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Latest Product");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        basoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);
        basoProgressView.startProgress();

        mContext = this;
        mProductRcv = (RecyclerView) findViewById(R.id.rcv_product);
        listProducts = new ArrayList<>();

        adapter = new ProductsAdapter(this, listProducts);

        mProductRcv.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        mProductRcv.setLayoutManager(layoutManager);
        mProductRcv.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+Config.BASE_SITE+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IProductsServices.class);

        handleSearchIntent(getIntent());
    }

    public void loadProducts(int page) {
        final String nonce = new TimestampServiceImpl().getNonce();
        final String timestamp = new TimestampServiceImpl().getTimestampInSeconds();

        Call<List<Product>> call = service.getProducts(
                "HMAC-SHA1",
                Config.COSTUMER_KEY,
                "1.0", timestamp,
                nonce,
                authenticatedLoadProductsRequest(Config.BASE_URL, nonce, timestamp, page, per_page),
                page,
                per_page
        );

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                basoProgressView.stopAndGone();
                Log.e(" Response RAW ",String.valueOf(response.raw()));
                if (response.isSuccessful()) {
                    listProducts.addAll(response.body());
                    adapter.notifyDataChanged();
                } else {
                    Log.e(" Response Error ",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadMoreProducts(int page) {
        final String nonce = new TimestampServiceImpl().getNonce();
        final String timestamp = new TimestampServiceImpl().getTimestampInSeconds();

        Call<List<Product>> call = service.getProducts(
                "HMAC-SHA1",
                Config.COSTUMER_KEY,
                "1.0", timestamp,
                nonce,
                authenticatedLoadProductsRequest(Config.BASE_URL, nonce, timestamp, page, per_page),
                page,
                per_page
        );

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    List<Product> result = response.body();

                    if(result.size() > 0) {
                        listProducts.addAll(result);
                    } else {
                        adapter.setMoreDataAvailable(false);
                        Toast.makeText(mContext,"No More Data Available",Toast.LENGTH_SHORT).show();
                    }

                    adapter.notifyDataChanged();
                } else {
                    Log.e("Response Error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void searchProducts(String query) {
        final String nonce = new TimestampServiceImpl().getNonce();
        final String timestamp = new TimestampServiceImpl().getTimestampInSeconds();
        Call<List<Product>> call = service.filterProducts(
                "HMAC-SHA1",
                Config.COSTUMER_KEY,
                "1.0", timestamp,
                nonce,
                authenticatedFilterProductsRequest(Config.BASE_URL, nonce, timestamp, 1, per_page, query),
                1, per_page, query);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.e(" Response  ", response.raw().toString());
                basoProgressView.stopAndGone();
                if (response.isSuccessful()) {
                    //clearData();
                    List<Product> result = response.body();
                    if(result.size() > 0) {
                        listProducts.addAll(result);
                    } else {
                        adapter.setMoreDataAvailable(false);
                        Toast.makeText(mContext,"No More Data Available",Toast.LENGTH_SHORT).show();
                    }

                    adapter.notifyDataChanged();
                    searchManager.stopSearch();
                } else {
                    Log.e(" Response Error ",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Log.e(" Response Error ",t.getMessage().toString());
            }
        });
    }

    public void clearData() {
        int size = listProducts.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                listProducts.remove(0);
            }

            adapter.notifyItemRangeRemoved(0, size);
            adapter.notifyDataSetChanged();
        }
    }

    private String authenticatedLoadProductsRequest(String url, String nonce, String timestamp, int page, int per_page) {
        String firstEncodedString = Config.METHOD+"&"+encodeUrl(url);
        String parameterString = "oauth_consumer_key="+ Config.COSTUMER_KEY+"&oauth_nonce="+nonce+"&oauth_signature_method=HMAC-SHA1&oauth_timestamp="+timestamp+"&oauth_version=1.0&page="+page+"&per_page="+per_page;

        String secoundEncodedString = "&"+encodeUrl(parameterString);
        String baseString = firstEncodedString+secoundEncodedString;
        String signature = new HMACSha1SignatureService().getSignature(baseString, Config.COSTUMER_SECRET, "");
        String finalSignature = encodeUrl(signature);

        return finalSignature;
    }

    private String authenticatedFilterProductsRequest(String url, String nonce, String timestamp, int page, int per_page, String search) {
        String firstEncodedString = Config.METHOD+"&"+encodeUrl(url);
        String parameterString = "oauth_consumer_key="+ Config.COSTUMER_KEY+"&oauth_nonce="+nonce+"&oauth_signature_method=HMAC-SHA1&oauth_timestamp="+timestamp+"&oauth_version=1.0&page="+page+"&per_page="+per_page+"&search="+search;

        String secoundEncodedString = "&"+encodeUrl(parameterString);
        String baseString = firstEncodedString+secoundEncodedString;
        String signature = new HMACSha1SignatureService().getSignature(baseString, Config.COSTUMER_SECRET, "");
        String finalSignature = encodeUrl(signature);

        return finalSignature;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleSearchIntent(intent);
    }

    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            if (query.length() > 3) {
                Toast.makeText(mContext, "Searching for "+query, Toast.LENGTH_SHORT).show();
                toolbar.setTitle("Searching for "+query);
                clearData();
                searchProducts(query);
            }

        } else {
            initProductsLoad();
        }
    }

    private void initProductsLoad() {
        loadProducts(init_page);
        adapter.setLoadMoreListener(new ProductsAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mProductRcv.post(new Runnable() {
                    @Override
                    public void run() {
                        int counter = run_counter++;
                        Toast.makeText(mContext, "LOADMORE RUN "+counter, Toast.LENGTH_SHORT).show();

                        loadMoreProducts(1+init_page++);
                    }
                });
            }
        });
    }

    private void retryClicked() {
        basoProgressView.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
                Toast.makeText(v.getContext(), "Retry CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String formatCurrency(Double money, String symbol, String groupingSep, String decimalSep) {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol(symbol);
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) currencyInstance).setDecimalFormatSymbols(dfs);

        return currencyInstance.format(money);
    }

    public String encodeUrl(String url)
    {
        String encodedurl = "";
        try {

            encodedurl = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedurl;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.products, menu);

        // Associate searchable configuration with the SearchView
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}