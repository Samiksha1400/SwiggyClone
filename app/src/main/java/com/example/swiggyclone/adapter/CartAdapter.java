package com.example.swiggyclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggyclone.R;
import com.example.swiggyclone.model.CartItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItemList;
    private Context context;
    private Runnable updateTotalCost;

    public CartAdapter(List<CartItem> cartItemList, Context context, Runnable updateTotalCost) {
        this.cartItemList = cartItemList;
        this.context = context;
        this.updateTotalCost = updateTotalCost;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_ui, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.dishName.setText(item.getDishName());
        holder.price.setText("₹" + item.getPrice());
        holder.quantity.setText(String.valueOf(item.getQuantity()));
        Picasso.get().load(item.getImage()).into(holder.image);

        holder.plusBtn.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.quantity.setText(String.valueOf(item.getQuantity()));
            updateTotalCost.run();
        });

        holder.minusBtn.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.quantity.setText(String.valueOf(item.getQuantity()));
                updateTotalCost.run();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView dishName, price, quantity;
        private ImageView image;
        private Button plusBtn, minusBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.cartItemDishName);
            price = itemView.findViewById(R.id.cartItemPrice);
            quantity = itemView.findViewById(R.id.cartItemQuantity);
            image = itemView.findViewById(R.id.cartItemImage);
            plusBtn = itemView.findViewById(R.id.increaseQuantityButton);
            minusBtn = itemView.findViewById(R.id.decreaseQuantityButton);
        }
    }
}
