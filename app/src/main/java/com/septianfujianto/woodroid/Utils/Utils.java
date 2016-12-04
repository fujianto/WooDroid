package com.septianfujianto.woodroid.Utils;

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
            sum = sum+i;

        return sum;
    }
}
