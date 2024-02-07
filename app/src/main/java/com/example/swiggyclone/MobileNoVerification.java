package com.example.swiggyclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MobileNoVerification extends AppCompatActivity
{
    TextInputLayout layoutMobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no_verification);
        layoutMobileNo = findViewById(R.id.layoutMobileNo);
    }

    //Button Back
    public void backBtn(View view)
    {
        startActivity(new Intent(MobileNoVerification.this, GetStartedWindow.class));
    }

    //Button Get OTP
    /*public void getOTP(View view)
    {
        if (!layoutMobileNo.getEditText().toString().trim().isEmpty())
        {
            if ((layoutMobileNo.getEditText().toString().trim()).length() == 10)
            {
                Intent intent = new Intent(MobileNoVerification.this, OtpVerification.class);
                intent.putExtra("mobileno",layoutMobileNo.getEditText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Enter Correct Number", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
        }
    }*/
}