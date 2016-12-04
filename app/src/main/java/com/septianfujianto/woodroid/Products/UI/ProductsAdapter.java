package com.septianfujianto.woodroid.Products.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.SingleProduct.SingleProductActivity;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Septian A. Fujianto on 10/31/2016.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Product> productsList;
    private Context context;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public ProductsAdapter(Context context, List<Product> productsList) {
        this.productsList = productsList;
        this.context = context;
    }

    @Override
    public ProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products_list_row, null);
        final ProductsHolder holder = new ProductsHolder(view);

        holder.productWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productId = productsList.get(holder.getAdapterPosition()).getId();

                Intent intent = new Intent(view.getContext(), SingleProductActivity.class);
                intent.putExtra("productId", productId);

                view.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position >= getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        ((ProductsHolder) holder).bindData(productsList.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
