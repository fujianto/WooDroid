package com.septianfujianto.woodroid.Checkout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Orders.Order;
import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.septianfujianto.woodroid.Model.Realm.City;
import com.septianfujianto.woodroid.Model.Realm.Province;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.Model.Shipping.Cost;

import com.septianfujianto.woodroid.Model.Shipping.RajaongkirShipping;
import com.septianfujianto.woodroid.Products.ProductsActivity;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Services.IRajaongkirServices;
import com.septianfujianto.woodroid.Services.IWoocommerceServices;
import com.septianfujianto.woodroid.Utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.data;
import static com.septianfujianto.woodroid.Config.*;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener, CheckoutCallback {
    private SearchableSpinner listProvince, listCity, listCourier;
    private RealmHelper helper;
    private Realm realm;
    private RealmResults<Province> provinces;
    private RealmResults<City> cities;
    private int selProvId = 1;
    private int selCityId = 1;
    private Context context;
    private TextView totalAmount, osWeight, osShipping, osSubtotal;
    private EditText firstName, lastName, email, phone, address, postCode, orderNotes;
    private Button btnOrder;
    private IRajaongkirServices services;
    private AlertDialog.Builder loadingDialog;
    private AlertDialog dialog;
    private BiMap<String, String> provinceList;
    private BiMap<Integer, String> cityList;
    private List<String> cityNameList;
    private BiMap<String, Integer> courierCostList;
    private List<String> serviceList;
    private LinearLayout llOrderSummaryItem;
    private LinearLayout llOrderSummaryTotal;
    private Double totalCost;
    private Double subTotal;
    private int totalCartWeight;
    protected IWoocommerceServices woocommerceServices;
    private View rootView;
    private View.OnClickListener retryCreateOrderClickListener;
    private View.OnClickListener retryCourierClickListener;
    public CheckoutCallback mCallbacks;
    private Snackbar snackbarCourier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        context = this;
        retryCreateOrderClickListener = this;
        mCallbacks = this;

        rootView = findViewById(R.id.activity_checkout);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.emailAddress);
        phone = (EditText) findViewById(R.id.phoneNumber);
        address = (EditText) findViewById(R.id.address);
        postCode = (EditText) findViewById(R.id.postalCode);
        orderNotes = (EditText) findViewById(R.id.orderNotes);
        btnOrder = (Button) findViewById(R.id.btnPlaceOrder);
        llOrderSummaryItem = (LinearLayout) findViewById(R.id.OrderSummaryItem);
        llOrderSummaryTotal = (LinearLayout) findViewById(R.id.OrderSummaryTotal);
        totalAmount = (TextView) findViewById(R.id.finalAmount);
        osShipping = (TextView) findViewById(R.id.osShippingPrice);
        osSubtotal = (TextView) findViewById(R.id.osSubtotal);
        osWeight = (TextView) findViewById(R.id.osTotalWeight);

        listProvince = (SearchableSpinner) findViewById(R.id.listProvince);
        listCity = (SearchableSpinner) findViewById(R.id.listCity);
        listCourier = (SearchableSpinner) findViewById(R.id.listCourier);

        realm = Realm.getDefaultInstance();
        helper = new RealmHelper(this);

        setupOrderSummary();
        getSupportActionBar().setTitle("Order");
        setupSpinner();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateField() == true) {
                    createOrder();
                } else {
                    Toast.makeText(context, "All form fields must be filled correctly", Toast.LENGTH_SHORT).show();
                    validateField();
                }
            }
        });
    }

    private void createOrderCompleteDialog(Order order) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Order #"+order.getId()+" Completed "+order.getTransactionId());
        alert.setMessage("Your order is completed. Please transfer the total amount to our account and " +
                "confirms your payment after transfer completed.");

        alert.setPositiveButton("Back to Store", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(context, ProductsActivity.class));
            }
        });

        alert.show();
    }

    private void orderCartItem(Map<String, Object> data) {
        RealmResults<Cart> cartResults =  helper.getAllCartItems();
        int cartSize = cartResults.size();

        for (int i = 0; i < cartSize; i++) {
            int productId = cartResults.get(i).getProductId();
            int qty = cartResults.get(i).getProductQty();

            data.put("data[line_items]["+i+"][product_id]", productId);
            data.put("data[line_items]["+i+"][quantity]", qty);
        }
    }

    private void createOrder() {
        Map<String, Object> options = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        options.put("options[wp_api]", true);
        options.put("options[version]", "wc/v1");

        data.put("data[payment_method]", "bacs");
        data.put("data[payment_method_title]", "Direct Bank Transfer");
        data.put("data[set_paid]", "false");
        data.put("data[customer_note]", orderNotes.getText().toString());

        data.put("data[billing][first_name]", firstName.getText().toString());
        data.put("data[billing][last_name]", lastName.getText().toString());
        data.put("data[billing][address_1]", address.getText().toString());
        data.put("data[billing][city]", listCity.getSelectedItem().toString());
        data.put("data[billing][state]", listProvince.getSelectedItem().toString());
        data.put("data[billing][postcode]", postCode.getText().toString());
        data.put("data[billing][country]", "ID");
        data.put("data[billing][email]", email.getText().toString());
        data.put("data[billing][phone]", phone.getText().toString());

        data.put("data[shipping][first_name]", firstName.getText().toString());
        data.put("data[shipping][last_name]", lastName.getText().toString());
        data.put("data[shipping][address_1]", address.getText().toString());
        data.put("data[shipping][city]", listCity.getSelectedItem().toString());
        data.put("data[shipping][state]", listProvince.getSelectedItem().toString());
        data.put("data[shipping][postcode]", postCode.getText().toString());
        data.put("data[shipping][country]", "ID");
        data.put("data[shipping][phone]", phone.getText().toString());

        orderCartItem(data);

        data.put("data[shipping_lines][0][method_id]", "jne");
        data.put("data[shipping_lines][0][method_title]", "JNE Shipping");
        data.put("data[shipping_lines][0][total]", totalCost);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.SITE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        woocommerceServices = retrofit.create(IWoocommerceServices.class);

        Call<Order> call = woocommerceServices.createOrder(
                SITE_URL, COSTUMER_KEY, COSTUMER_SECRET,
                options, "orders/", data);

        Snackbar.make(rootView, "Creating Order...", Snackbar.LENGTH_SHORT).show();

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(rootView, "Order Completed", Snackbar.LENGTH_LONG).show();

                    helper.deleteAllCartItem();
                    createOrderCompleteDialog(response.body());
                } else {
                    Snackbar.make(rootView, "Creating order failed, please retry", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Retry", retryCreateOrderClickListener).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                t.printStackTrace();
                if (t.getMessage() != null) {
                    Snackbar.make(rootView, "Creating order failed, please retry: "+t.getMessage(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Retry", retryCreateOrderClickListener).show();
                }
            }
        });
    }

    private void setupSpinner() {
        provinces = realm.where(Province.class).findAll();
        provinces.sort("province_id", Sort.ASCENDING);
        List<String> pList = new ArrayList<>();
        provinceList = HashBiMap.create();

        for (int i = 0; i < provinces.size(); i++) {
            pList.add(provinces.get(i).getProvince());
            provinceList.forcePut(String.valueOf(provinces.get(i).getProvinceId()), provinces.get(i).getProvince().toString());
        }

        /*listProvince.setTitle("Select Province");
        listCity.setTitle("Select City");*/

        if (provinces.size() > 0) {
            clearSpinnerChild();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, pList);
            listProvince.setAdapter(adapter);

            listProvince.setTitle("Choose Province");
            listProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String selItem = parent.getSelectedItem().toString();
                    selProvId =  Integer.valueOf(provinceList.inverse().get(selItem));

                    //Toast.makeText(view.getContext(), "Province POS: "+pos+" ID: "+selProvId, Toast.LENGTH_SHORT).show();

                    cities = realm.where(City.class).equalTo("province_id", selProvId).findAll();
                    List<String> cList = new ArrayList<>();
                    cityList = HashBiMap.create();
                    cityNameList = new ArrayList<>();

                    for (int i = 0; i < cities.size(); i++) {
                        cityList.forcePut(cities.get(i).getCityId(), cities.get(i).getCityName());
                        cityNameList.add(cities.get(i).getCityName());

                        if (TextUtils.equals("Kabupaten", cities.get(i).getType())) {
                            cList.add(cities.get(i).getType() + " " + cities.get(i).getCityName());
                        } else {
                            cList.add(cities.get(i).getCityName());
                        }
                    }

                    if (listCity.getSelectedItemPosition() >= 0) {
                        listCity.setAdapter(null);
                        listCity.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, cList));
                        System.out.println("DISABLE CITY");
                    } else {
                        listCity.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, cList));
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            listCity.setTitle("Choose city Destination");
            listCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String selCityName = cityNameList.get(parent.getSelectedItemPosition());
                    selCityId = Integer.valueOf(cityList.inverse().get(selCityName));

                    Toast.makeText(view.getContext(), "City ID: "+selCityId+" Name: "+selCityName+" at Province: "+selProvId, Toast.LENGTH_LONG).show();
                    getShippingCosts(ORIGIN_CITY_ID, selCityId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            listCourier.setTitle("Choose JNE Courier");
            listCourier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String selCourierLabel = serviceList.get(pos);
                    int selCourierPrice = courierCostList.get(selCourierLabel);
                    Double totalPrice = selCourierPrice + subTotal ;
                    totalCost = totalPrice;
                    String formattedShippingCost = Utils.formatCurrency(Double.valueOf(courierCostList.get(selCourierLabel)), CURRENCY_SYMBOL,
                            GROUPING_SPEARATOR, DECIMAL_SEPARATOR);
                    String formattedTotalAmount = String.valueOf(Utils.formatCurrency(totalPrice, CURRENCY_SYMBOL,
                            GROUPING_SPEARATOR, DECIMAL_SEPARATOR));
                    totalAmount.setText("Total: "+formattedTotalAmount);

                    osShipping.setText("Shipping cost: "+formattedShippingCost);
                    Toast.makeText(context, "Shipping Prices "+totalPrice, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });
        }
    }

    private void clearSpinnerChild() {
        listProvince.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Adapter lCourierAdapter = listCourier.getAdapter() != null ? listCourier.getAdapter() : null;
                int lCourierCount = lCourierAdapter != null && lCourierAdapter.getCount() > 0 ? lCourierAdapter.getCount() : 0;

                Adapter lCityAdapter = listCity.getAdapter() != null ? listCity.getAdapter() : null;
                int lCityCount = lCityAdapter != null && lCityAdapter.getCount() > 0 ? lCityAdapter.getCount() : 0;

                System.out.println("setOnFocusChangeListener: "+ lCourierCount);

                if (lCourierCount > 0) {
                    listCourier.setAdapter(null);
                }

                if (lCityCount > 0) {
                    listCity.setAdapter(null);
                }
            }
        });
    }

    private void getShippingCosts(int originCityId, final int destinationCityId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RAJAONGKIR_STARTER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        services = retrofit.create(IRajaongkirServices.class);
        Call<RajaongkirShipping> call = services.getShippingCost(
                RAJAONGKIR_KEY,
                originCityId, destinationCityId,
                totalCartWeight, DEFAULT_COURIER);

        loadingDialog = new AlertDialog.Builder(context);
        loadingDialog.setTitle("Fetching shipping costs");
        loadingDialog.setMessage("Please wait");
        loadingDialog.setCancelable(true);
        dialog = loadingDialog.create();
        dialog.show();

        call.enqueue(new Callback<RajaongkirShipping>() {
            @Override
            public void onResponse(Call<RajaongkirShipping> call, Response<RajaongkirShipping> response) {
                dialog.dismiss();
                snackbarCourier = Snackbar.make(rootView, "Courier to your city in Unavailable", Snackbar.LENGTH_INDEFINITE);

                System.out.println("RESPONSE SUC: "+response.isSuccessful());
                System.out.println("RESPONSE RAW: "+response.raw());

                if (response.isSuccessful()) {
                    List<Cost> courierCostsResult = response.body().getRajaongkir().getResults().get(0).getCosts();

                    if (courierCostsResult.size() > 0) {
                        if (snackbarCourier.isShownOrQueued()) {
                            snackbarCourier.dismiss();
                        }

                        System.out.println("SNACKBAR: "+snackbarCourier.isShownOrQueued());

                        courierCostList = HashBiMap.create();
                        List<String> courierLabel = new ArrayList<>();
                        serviceList = new ArrayList<>();

                        int courierCostResult = courierCostsResult.size();

                        for (int i = 0; i < courierCostResult; i++) {
                            String service = response.body().getRajaongkir().getResults().get(0).getCosts().get(i).getService();
                            int value = courierCostsResult.get(i).getCost().get(0).getValue();
                            courierLabel.add(service + " - " + value);
                            courierCostList.forcePut(service, value);
                            serviceList.add(service);
                        }

                        listCourier.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, courierLabel));
                    } else {
                        listCourier.setAdapter(null);
                        snackbarCourier.setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbarCourier.dismiss();
                            }
                        }).show();
                    }
                } else {
                    mCallbacks.onFailedCourierFetch(ORIGIN_CITY_ID, destinationCityId);
                    Snackbar.make(rootView, "Error fetching shipping costs.", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Retry", retryCourierClickListener).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RajaongkirShipping> call, Throwable t) {
                dialog.dismiss();

                mCallbacks.onFailedCourierFetch(ORIGIN_CITY_ID, destinationCityId);
                Snackbar.make(rootView, "Trouble fetching shipping costs, please Retry", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", retryCourierClickListener).show();
                t.printStackTrace();
            }
        });
    }

    private void setupOrderSummary() {
        totalCartWeight = helper.getCartTotalWeight().intValue();
        subTotal = helper.getCartTotalAmount();
        RealmResults<Cart> cart = helper.getAllCartItems();
        List<String> orderSummaryItem = new ArrayList<>();

        // Print all Item from shopping cart
        for (int i = 0; i < cart.size(); i++) {
            String formattedPrice = Utils.formatCurrency(cart.get(i).getProductPrice(), CURRENCY_SYMBOL, GROUPING_SPEARATOR, DECIMAL_SEPARATOR);
            orderSummaryItem.add(cart.get(i).getProductName()+" - "+cart.get(i).getProductQty()+" pcs @ "+formattedPrice);
        }

        Utils.getSimpleList(context, llOrderSummaryItem, orderSummaryItem, 18);

        osWeight.setText("Total Weight: "+totalCartWeight+WEIGHT_UNITS);
        osShipping.setText("Shipping cost: ");
        osSubtotal.setText("Subtotal: "+Utils.formatCurrency(subTotal, CURRENCY_SYMBOL, GROUPING_SPEARATOR, DECIMAL_SEPARATOR));
    }

    private Boolean validateField() {
        boolean isValidated = false;

        if (!Utils.isFormFilled(firstName.getText().toString())) {
            isValidated = false;
            firstName.setError(getResources().getString(R.string.msg_first_name));
        }

        else if (!Utils.isFormFilled(lastName.getText().toString())) {
            isValidated = false;
            lastName.setError(getResources().getString(R.string.msg_last_name));
        }

        else if (!Utils.isValidEmail(email.getText().toString())) {
            isValidated = false;
            email.setError(getResources().getString(R.string.msg_email));
        }

        else if (!Utils.isFormFilled(phone.getText().toString())) {
            isValidated = false;
            phone.setError(getResources().getString(R.string.msg_phone_number));
        }

        else if (!Utils.isFormFilled(address.getText().toString())) {
            isValidated = false;
            address.setError(getResources().getString(R.string.msg_address));
        }

        else if(!Utils.isValidSpinner(listProvince)) {
            isValidated = false;
            View selectedView = listProvince.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError(getResources().getString(R.string.msg_province));
        }

        else if(!Utils.isValidSpinner(listCity)) {
            isValidated = false;
            View selectedView = listCity.getSelectedView();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError(getResources().getString(R.string.msg_city));
        }

        else if(listCourier.getSelectedItem() == null ) {
            isValidated = false;
        }

        else if (!Utils.isFormFilled(postCode.getText().toString())) {
            isValidated = false;
            postCode.setError(getResources().getString(R.string.msg_postalcode));
        }

        else {
            isValidated = true;
        }

        return isValidated;
    }

    private void retryFetchingCourier(final int originCityId, final int destinationCityId) {
        dialog.dismiss();

        retryCourierClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getShippingCosts(originCityId, destinationCityId);
            }
        };
    }

    @Override
    public void onClick(View view) {
        createOrder();
    }

    @Override
    public void onFailedCourierFetch(int originCityId, int destinationCityId) {
        retryFetchingCourier(originCityId, destinationCityId);
    }
}
