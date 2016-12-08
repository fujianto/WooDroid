package com.septianfujianto.woodroid.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Septian A. Fujianto on 11/27/2016.
 */

public class Utils {
    public static String formatCurrency(Double money, String symbol, char groupingSep, char decimalSep) {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol(symbol);
        dfs.setGroupingSeparator(groupingSep);
        dfs.setMonetaryDecimalSeparator(decimalSep);
        ((DecimalFormat) currencyInstance).setDecimalFormatSymbols(dfs);

        return currencyInstance.format(money);
    }

    public static Double sumList(List<Double> list){
        if(list == null || list.size()<1)
            return Double.valueOf(0);

        Double sum = Double.valueOf(0);
        for(Double i: list)
            sum = sum + i;

        return sum;
    }
}
