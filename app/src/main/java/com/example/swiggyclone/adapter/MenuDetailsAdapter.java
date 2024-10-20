package com.example.swiggyclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggyclone.R;
import com.example.swiggyclone.model.MenuDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuDetailsAdapter extends RecyclerView.Adapter<MenuDetailsAdapter.MenuDetailsHolder>{

    private List<MenuDetails> menuDetailsList;
    private Context context;

    public MenuDetailsAdapter(List<MenuDetails> menuDetailsList, Context context) {
        this.menuDetailsList = menuDetailsList;
        this.context = context;
    }



    @NonNull
    @Override
    public MenuDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_ui,parent,false);
        return new MenuDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuDetailsHolder holder, int position) {
        holder.dishName.setText(menuDetailsList.get(position).getDish_name());
        holder.rating.setText(menuDetailsList.get(position).getRating());
        holder.price.setText("₹"+menuDetailsList.get(position).getPrice());
        holder.description.setText(menuDetailsList.get(position).getDescription());
        Picasso.get().load(menuDetailsList.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return menuDetailsList.size();
    }

    public class MenuDetailsHolder extends RecyclerView.ViewHolder{

        private TextView dishName,rating,price,description;
        private ImageView image;
        public MenuDetailsHolder(@NonNull View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.dishName);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.dishImg);
        }
    }
}
