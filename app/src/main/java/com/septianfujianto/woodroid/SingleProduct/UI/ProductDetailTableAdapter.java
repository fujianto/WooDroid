package com.septianfujianto.woodroid.SingleProduct.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.septianfujianto.woodroid.Model.Item;
import com.septianfujianto.woodroid.R;

import java.util.ArrayList;

/**
 * Created by Septian A. Fujianto on 11/28/2016.
 */

public class ProductDetailTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Item> tableData;
    private Context context;

    public ProductDetailTableAdapter(Context context, ArrayList<Item> tableData){
        this.context = context;
        this.tableData = tableData;
    }

    @Override
    public ProductDetailTableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_detail_table_row, null);
        ProductDetailTableHolder holder = new ProductDetailTableHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ProductDetailTableHolder) holder).bindData(tableData.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }
}
