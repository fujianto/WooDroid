package com.septianfujianto.woodroid.SingleProduct.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.septianfujianto.woodroid.Model.Item;
import com.septianfujianto.woodroid.R;

/**
 * Created by Septian A. Fujianto on 11/28/2016.
 */

public class ProductDetailTableHolder extends RecyclerView.ViewHolder {
    protected TextView tableTitle, tableDetail;

    public ProductDetailTableHolder(View itemView) {
        super(itemView);

        tableTitle = (TextView) itemView.findViewById(R.id.tableDetailTitle);
        tableDetail = (TextView) itemView.findViewById(R.id.tableDetailContent);
    }

    void bindData(Item tableItem){
        tableTitle.setText(tableItem.getTitle());
        tableDetail.setText(tableItem.getDetail());
    }
}
