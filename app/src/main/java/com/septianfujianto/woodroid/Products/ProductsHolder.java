package com.septianfujianto.woodroid.Products;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.Utils.SquaredImageView;
import com.septianfujianto.woodroid.Utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by Septian A. Fujianto on 10/31/2016.
 */

public class ProductsHolder extends RecyclerView.ViewHolder {
    public SquaredImageView featuredImage;
    public TextView productTitle, productPrice;
    public LinearLayout productWrapper;

    public ProductsHolder(View itemView) {
        super(itemView);

        featuredImage = (SquaredImageView) itemView.findViewById(R.id.featuredImage);
        productTitle = (TextView) itemView.findViewById(R.id.productTitle);
        productPrice = (TextView) itemView.findViewById(R.id.productPrice);
        this.productWrapper = (LinearLayout) itemView.findViewById(R.id.productWrapper);

        itemView.setClickable(true);
    }

    void bindData(Product product) {
        Picasso.with(itemView.getContext())
                .load(product.getImages().get(0).getSrc())
                .placeholder(R.drawable.thumbnail)
                .into(featuredImage);
        productTitle.setText(product.getName());
        String formatedPrice = Utils.formatCurrency(
                Double.valueOf(product.getPrice()), Config.CURRENCY_SYMBOL,
                Config.GROUPING_SPEARATOR, Config.DECIMAL_SEPARATOR);
        productPrice.setText(formatedPrice);
    }
}