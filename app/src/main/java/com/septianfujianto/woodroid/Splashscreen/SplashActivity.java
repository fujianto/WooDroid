package com.septianfujianto.woodroid.Splashscreen;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.septianfujianto.woodroid.Main.MainActivity;
import com.septianfujianto.woodroid.Model.Realm.City;
import com.septianfujianto.woodroid.Model.Realm.Province;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;

import java.io.IOException;

import id.gits.baso.BasoProgressView;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.septianfujianto.woodroid.Config.RAJAONGKIR_KEY;
import static com.septianfujianto.woodroid.Config.RAJAONGKIR_STARTER_URL;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtSplashNotif;
    private RealmHelper helper;
    private Context context;
    private String mNotif = "";
    private RealmResults<RealmModel> cityRealmResults;
    private RealmResults<RealmModel> provinceRealmResults;
    private BasoProgressView basoProgressView;
    private View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtSplashNotif = (TextView) findViewById(R.id.notifSplash);
        basoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);
        context = this;
        onClickListener = this;
        helper = new RealmHelper(this);
        cityRealmResults = helper.getAllRealmItems(City.class);
        provinceRealmResults = helper.getAllRealmItems(Province.class);
        basoProgressView.startProgress();

        splashScreenTransition();
    }

    private void splashScreenTransition() {
        mNotif = "Downloading Province & City data..";

        if (cityRealmResults.size() <= 0) {
            System.out.println("Downloading City Data...");
            txtSplashNotif.setText(mNotif);

            getRajaongkirResults("city", new IPassCallback() {
                @Override
                public void getAPIResults(String jsonResults) {
                    helper = new RealmHelper(context);
                    helper.addtoRealmFromJsonRajaongkir(jsonResults, City.class);
                    System.out.println("Downloading City complete");
                    openMainApp();
                }
            });
        }

        if (provinceRealmResults.size() <= 0) {
            System.out.println("Downloading Province Data...");
            txtSplashNotif.setText(mNotif);

            getRajaongkirResults("province", new IPassCallback() {
               @Override
               public void getAPIResults(String jsonResults) {
                   helper = new RealmHelper(context);
                   helper.addtoRealmFromJsonRajaongkir(jsonResults, Province.class);
                   System.out.println("Downloading Province Complete");

                   openMainApp();
               }
            });

        }

        openMainApp();
    }

    public void openMainApp() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<City> cityResults = realm.where(City.class).findAll();
        RealmResults<Province> provinceResults = realm.where(Province.class).findAll();

        if (cityResults.size() > 0 && provinceResults.size() > 0) {
            System.out.println("Opening main app");
            mNotif = "Opening main app";

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            System.out.println("Downloading Data...");
        }
    }

    public void getRajaongkirResults(final String endpoint, final IPassCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(RAJAONGKIR_STARTER_URL+"/"+endpoint)
                .addHeader("key", RAJAONGKIR_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("Failure: Force open main App?");

               if (e.getMessage() != null) {
                   basoProgressView.stopAndError("Something wrong when downloading App Data "+e.getMessage());
                   basoProgressView.setOnButtonClickListener(onClickListener);
               }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    basoProgressView.stopAndGone();
                    callback.getAPIResults(response.body().string());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        basoProgressView.startProgress();
        Toast.makeText(context, "Retry fetching data", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }

    interface IPassCallback{
        void getAPIResults(String jsonResults);
    }
}
