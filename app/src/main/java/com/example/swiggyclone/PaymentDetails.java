package com.example.swiggyclone;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity implements PaymentResultListener {
    private double totalPaymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_details);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Checkout.preload(getApplicationContext());
        totalPaymentAmount = getIntent().getDoubleExtra("totalPaymentAmount",0.0);
        startPayment();
    }

    private void startPayment(){
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_UKjnqIlIx2eD48");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","Swiggy Clone");
            jsonObject.put("description","Order Payment");
            jsonObject.put("currency","INR");
            jsonObject.put("amount",totalPaymentAmount*100);

            checkout.open(this,jsonObject);
        } catch (JSONException e)
        {
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();
    }
}