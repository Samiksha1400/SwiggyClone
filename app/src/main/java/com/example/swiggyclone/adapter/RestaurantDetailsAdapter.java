package com.example.swiggyclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggyclone.FoodFragment;
import com.example.swiggyclone.MenuDetailsFragment;
import com.example.swiggyclone.PersonalDetails;
import com.example.swiggyclone.R;
import com.example.swiggyclone.model.RestaurantDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantDetailsAdapter extends RecyclerView.Adapter<RestaurantDetailsAdapter.RestaurantDetailsHolder>
{
    private List<RestaurantDetails> restaurantDetailsList;
    private Context context;

    public RestaurantDetailsAdapter(List<RestaurantDetails> restaurantDetailsList, Context context) {
        this.restaurantDetailsList = restaurantDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_ui,parent,false);
        return new RestaurantDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailsHolder holder, int position) {
        holder.restaurantName.setText(restaurantDetailsList.get(position).getName());
        holder.ratings.setText(restaurantDetailsList.get(position).getRating());
        holder.cuisine.setText(restaurantDetailsList.get(position).getCuisine());
        holder.address.setText(restaurantDetailsList.get(position).getAddress());
        Picasso.get().load(restaurantDetailsList.get(position).getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return restaurantDetailsList.size();
    }

    public class RestaurantDetailsHolder extends RecyclerView.ViewHolder{
        private TextView restaurantName,ratings,cuisine,address;
        private ImageView thumbnail;
        public RestaurantDetailsHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(v.getContext(), PersonalDetails.class);
                    //v.getContext().startActivity(i);

                    FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.containerFragment, new MenuDetailsFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
            restaurantName = itemView.findViewById(R.id.restaurantName);
            ratings = itemView.findViewById(R.id.restaurantRatings);
            cuisine = itemView.findViewById(R.id.cuisine);
            address = itemView.findViewById(R.id.address);
            thumbnail = itemView.findViewById(R.id.restaurantImg);
        }
    }
}
