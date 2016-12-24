package com.septianfujianto.woodroid.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    // validating password with retype password
    public static boolean isFormFilled(String input) {
        if (input != null && input.length() > 3) {
            return true;
        }

        return false;
    }

    public static boolean isValidSpinner(Spinner spinner){
        String selectedView = (String) spinner.getSelectedItem();
        if (selectedView != null) {
           return true;
        }

        return false;
    }

    public static void getSimpleList(Context context, LinearLayout layout, List<String> listContent, int rowFontSize) {
        for (int i = 0; i < listContent.size(); i++) {
            // create a new textview
            final TextView rowTextView = new TextView(context);

            // set some properties of rowTextView or something
            rowTextView.setText(listContent.get(i));
            rowTextView.setTextSize(rowFontSize);
            rowTextView.setX(10);
            rowTextView.setPadding(0, 0, 0, 10);

            // add the textview to the linearlayout
            layout.addView(rowTextView);
        }
    }
}
