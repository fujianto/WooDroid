package com.septianfujianto.woodroid.ShoppingCart;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.septianfujianto.woodroid.Checkout.CheckoutActivity;
import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Item;
import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.ShoppingCart.UI.ShoppingCartAdapter;
import com.septianfujianto.woodroid.ShoppingCart.UI.onCartItemEdit;
import com.septianfujianto.woodroid.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ShoppingCart extends AppCompatActivity implements ShoppingCartAdapter.Callbacks, View.OnClickListener {
    private RealmResults<Cart> results;
    private RecyclerView rcvCart;
    private RealmHelper helper;
    private TextView totalPrices;
    private ShoppingCartAdapter adapter;
    private Context context;
    private RealmResults<Cart> realmResults;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().show();

        helper = new RealmHelper(this);
        rcvCart = (RecyclerView) findViewById(R.id.rcv_cart);
        totalPrices = (TextView) findViewById(R.id.totalPrices);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        context = this;

        getSupportActionBar().setTitle("Checkout");

        setUpRecyclerView();
        setUpTotalAmount();
        btnOrder.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shopping_cart, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete_cart:
                deleteCart();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteCart() {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Do you want to remove all items from Cart?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                helper.deleteAllCartItem();
                adapter.notifyDataSetChanged();
                helper.getCartTotalAmount();
                setUpTotalAmount();
                getSupportActionBar().setTitle("0 item in Cart");
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

    private void setUpRecyclerView() {
        results = helper.getAllCartItems();
        getSupportActionBar().setTitle(results.size()+" items in Cart");
        adapter = new ShoppingCartAdapter(this, results, this);
        rcvCart.setHasFixedSize(true);
        rcvCart.setAdapter(adapter);
        rcvCart.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpTotalAmount() {
        Double totalAmout = helper.getCartTotalAmount();

        if (totalAmout.intValue() > 0) {
            totalPrices.setText(Utils.formatCurrency(
                    totalAmout,
                    Config.CURRENCY_SYMBOL,
                    Config.GROUPING_SPEARATOR,
                    Config.DECIMAL_SEPARATOR)
            );
        } else {
            totalPrices.setText("");
        }
    }


    @Override
    public void onUpdateTotalPrice(String data) {
        totalPrices.setText(String.valueOf(data));
    }

    @Override
    public void onCartSizeChange(int size) {
        getSupportActionBar().setTitle(size+" items in Cart");
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(context, CheckoutActivity.class));
    }
}
