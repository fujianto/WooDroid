package com.septianfujianto.woodroid.Checkout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.septianfujianto.woodroid.Model.Realm.City;
import com.septianfujianto.woodroid.Model.Realm.Province;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.Sort;

import static android.R.id.list;
import static android.media.CamcorderProfile.get;

public class CheckoutActivity extends AppCompatActivity {
    private SearchableSpinner listProvince, listCity;
    private RealmHelper helper;
    private Realm realm;
    private RealmResults<Province> provinces;
    private RealmResults<City> cities;
    private int selProvId = 1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        context = this;
        listProvince = (SearchableSpinner) findViewById(R.id.listProvince);
        listCity = (SearchableSpinner)  findViewById(R.id.listCity);
        realm = Realm.getDefaultInstance();
        getSupportActionBar().setTitle("Customer Info");

        provinces = realm.where(Province.class).findAll();
        provinces.sort("province_id", Sort.ASCENDING);
        List<String> pList = new ArrayList<>();

        for (int i = 0; i < provinces.size(); i++) {
            pList.add(provinces.get(i).getProvince());
        }

        listProvince.setTitle("Select Province");
        listCity.setTitle("Select City");

        if (provinces.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, pList);
            listProvince.setAdapter(adapter);

            listProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    Toast.makeText(view.getContext(), "Province POS: "+pos+" ID: "+id, Toast.LENGTH_SHORT).show();
                    selProvId = pos + 1;
                    getSupportActionBar().setTitle("Province ID Selected: "+selProvId);

                    cities = realm.where(City.class).equalTo("province_id", selProvId).findAll();
                    List<String> cList = new ArrayList<>();

                    for (int i = 0; i < cities.size(); i++) {
                        cList.add(cities.get(i).getCityName());
                    }

                    listCity.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, cList));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            listCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String selCityName = parent.getSelectedItem().toString();
                    RealmResults<City> citi = realm.where(City.class).equalTo("city_name", selCityName).findAll();
                    int cityID = citi.get(0).getCityId();

                    Toast.makeText(view.getContext(), "City ID: "+cityID+" Name: "+selCityName, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }


}
