package com.septianfujianto.woodroid.ShoppingCart.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.septianfujianto.woodroid.Config;
import com.septianfujianto.woodroid.Model.Products.Product;
import com.septianfujianto.woodroid.Model.Realm.Cart;
import com.septianfujianto.woodroid.Model.Realm.RealmHelper;
import com.septianfujianto.woodroid.Products.UI.ProductsHolder;
import com.septianfujianto.woodroid.R;
import com.septianfujianto.woodroid.ShoppingCart.ShoppingCart;
import com.septianfujianto.woodroid.Utils.SquaredImageView;
import com.septianfujianto.woodroid.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.septianfujianto.woodroid.Config.ITEM_UNITS;
import static com.septianfujianto.woodroid.R.id.totalPrices;

/**
 * Created by Septian A. Fujianto on 11/30/2016.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartHolder> {
    private Context context;
    private RealmResults<Cart> cart;
    private RealmHelper helper;
    private ShoppingCartAdapter adapter;
    public Callbacks mCallbacks;

    public ShoppingCartAdapter(Context context, RealmResults<Cart> cart, Callbacks callbacks) {
        this.context = context;
        this.cart = cart;
        helper = new RealmHelper(context);
        this.mCallbacks = callbacks;
        adapter = this;
    }

    @Override
    public ShoppingCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_row, null);
        final ShoppingCartHolder holder = new ShoppingCartHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ShoppingCartHolder holder, int position) {
        Picasso.with(context)
                .load(cart.get(holder.getAdapterPosition()).getProductImage())
                .placeholder(R.drawable.thumbnail)
                .into(holder.cartImage);

        holder.itemName.setText(cart.get(holder.getAdapterPosition()).getProductName());
        holder.itemPrice.setText(Utils.formatCurrency(
                cart.get(holder.getAdapterPosition()).getProductPrice(),
                Config.CURRENCY_SYMBOL,
                Config.GROUPING_SPEARATOR,
                Config.DECIMAL_SEPARATOR)
        );

        holder.itemQty.setText(String.valueOf(cart.get(holder.getAdapterPosition()).getProductQty())+ITEM_UNITS);
        Double subtotal = cart.get(holder.getAdapterPosition()).getProductPrice() * cart.get(holder.getAdapterPosition()).getProductQty();

        holder.subtotal.setText(Utils.formatCurrency(
                subtotal,
                Config.CURRENCY_SYMBOL,
                Config.GROUPING_SPEARATOR,
                Config.DECIMAL_SEPARATOR));

        holder.btnEditCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(final ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                        menu.setHeaderTitle("Select Action");
                        //groupId, itemId, order, title
                        menu.add(0, 22, 0, "Update Item").setOnMenuItemClickListener(
                                new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Toast.makeText(context,
                                        "Update ITEM "+cart.get(holder.getAdapterPosition()).getProductId(),
                                        Toast.LENGTH_SHORT).show();

                                return true;
                            }
                        });

                        menu.add(0, 33, 1, "Delete item").setOnMenuItemClickListener(
                            new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    int productId = cart.get(holder.getAdapterPosition()).getProductId();
                                    helper.deleteCartItemByProductId(productId);
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(context, "DELETE ITEM "+productId, Toast.LENGTH_SHORT).show();
                                    mCallbacks.onUpdateItem(Utils.formatCurrency(
                                            helper.getCartTotalAmount(),
                                            Config.CURRENCY_SYMBOL,
                                            Config.GROUPING_SPEARATOR,
                                            Config.DECIMAL_SEPARATOR));
                                    mCallbacks.onCartSizeChange(helper.getAllCartItems().size());

                                    return true;
                                }
                            });
                    }
                });

                view.showContextMenu();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.cart.size();
    }

    public interface Callbacks  {
        void onUpdateItem(String data);
        void onCartSizeChange(int size);
    }

    public class ShoppingCartHolder extends RecyclerView.ViewHolder {
        public SquaredImageView cartImage;
        public TextView itemName, itemPrice, itemQty, subtotal;
        public Button btnEdit;
        public Button btnEditCart;
        public TableRow cartItemWrapper;

        public ShoppingCartHolder(View itemView) {
            super(itemView);

            btnEditCart = (Button) itemView.findViewById(R.id.btnEdit);
            cartImage = (SquaredImageView) itemView.findViewById(R.id.cartImage);
            itemName = (TextView) itemView.findViewById(R.id.cartItemTitle);
            itemPrice = (TextView) itemView.findViewById(R.id.cartItemPrice);
            itemQty = (TextView) itemView.findViewById(R.id.cartItemQty);
            subtotal = (TextView) itemView.findViewById(R.id.cartItemSubtotal);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            cartItemWrapper = (TableRow) itemView.findViewById(R.id.cartItemWrapper);
        }
    }
}

