package com.example.swiggyclone;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggyclone.adapter.CartAdapter;
import com.example.swiggyclone.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private TextView totalAmount, gstAmount, deliveryFee, grandTotal;
    private double gstRate = 0.05; // 5% GST
    private double deliveryServiceFee = 30.0; // example delivery fee

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        cartRecyclerView = findViewById(R.id.cartItemsRecyclerView);
        totalAmount = findViewById(R.id.itemTotal);
        gstAmount = findViewById(R.id.gstTax);
        deliveryFee = findViewById(R.id.deliveryFee);
        grandTotal = findViewById(R.id.totalCost);

        cartItemList = new ArrayList<>();

        // Retrieve data from intent and add to cart
        String dishName = getIntent().getStringExtra("dish_name");
        String price = getIntent().getStringExtra("price");
        String image = getIntent().getStringExtra("image");

        // Add the item to the cart
        cartItemList.add(new CartItem(dishName, Double.parseDouble(price), image, 1));

        cartAdapter = new CartAdapter(cartItemList, this, this::updateTotalCost);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Initial calculation
        updateTotalCost();

        //Back Button
        ImageButton backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(view -> onBackPressed());
    }

    private void updateTotalCost() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getQuantity();
        }

        double gst = total * gstRate;
        double grandTotalAmount = total + gst + deliveryServiceFee;

        totalAmount.setText("₹" + String.format("%.2f", total));
        gstAmount.setText("₹" + String.format("%.2f", gst));
        deliveryFee.setText("₹" + String.format("%.2f", deliveryServiceFee));
        grandTotal.setText("₹" + String.format("%.2f", grandTotalAmount));
    }
}
