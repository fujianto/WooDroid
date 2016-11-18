package com.septianfujianto.woodroid.Products;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.SingleProduct.SingleProduct;

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
        View view = LayoutInflater.from(context).inflate(R.layout.products_list, null);
        final ProductsHolder holder = new ProductsHolder(view);
        /*LayoutInflater inflater = LayoutInflater.from(context);
        final ProductsHolder holder = new ProductsHolder(inflater.inflate(R.layout.products_list, parent, false));*/

        holder.productWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int productId = productsList.get(holder.getAdapterPosition()).getId();

                /* Fast Way */
                String productName = productsList.get(holder.getAdapterPosition()).getName();
                String productPrice = productsList.get(holder.getAdapterPosition()).getPrice();
                String productWeight = productsList.get(holder.getAdapterPosition()).getWeight();
                String productDesc = productsList.get(holder.getAdapterPosition()).getDescription();
                int prodCatSize = productsList.get(holder.getAdapterPosition()).getCategories().size();
                String[] cat = new String[prodCatSize];
                int prodTagSize = productsList.get(holder.getAdapterPosition()).getTags().size();
                String[] tag = new String[prodTagSize];

                for (int i= 0; i < productsList.get(holder.getAdapterPosition()).getCategories().size(); i++) {
                    cat[i] = productsList.get(holder.getAdapterPosition()).getCategories().get(i).getName();
                }

                for (int i= 0; i < productsList.get(holder.getAdapterPosition()).getTags().size(); i++) {
                    tag[i] = productsList.get(holder.getAdapterPosition()).getTags().get(i).getName();
                }

                String productCat = TextUtils.join(",", cat);
                String productTag = TextUtils.join(",", tag);
                String featuredImage = productsList.get(holder.getAdapterPosition()).getImages().get(0).getSrc();
                Log.e("CAT", productCat);
                Toast.makeText(view.getContext(), "Image Tapped "+productId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), SingleProduct.class);
                intent.putExtra("productId", productId);
                intent.putExtra("productName", productName);
                intent.putExtra("productPrice", productPrice);
                intent.putExtra("productWeight", productWeight);
                intent.putExtra("productDesc", productDesc);
                intent.putExtra("productCat", productCat);
                intent.putExtra("productTag", productTag);
                intent.putExtra("featuredImage", featuredImage);

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

        ((ProductsHolder) holder).bindData(productsList.get(position));
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


    interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
