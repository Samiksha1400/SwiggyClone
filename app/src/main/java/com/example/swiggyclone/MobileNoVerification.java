package com.example.swiggyclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileNoVerification extends AppCompatActivity
{
    TextInputLayout layoutMobileNo;
    TextInputEditText mobileNo;
    ProgressBar progressBarGetOtp;
    Button btn_get_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no_verification);
        layoutMobileNo = findViewById(R.id.layoutMobileNo);
        mobileNo = findViewById(R.id.mobileNo);
        progressBarGetOtp =findViewById(R.id.progressBarGetOtp);
        btn_get_otp = findViewById(R.id.btn_get_otp);
    }

    //Button Back
    public void backBtn(View view)
    {
        startActivity(new Intent(MobileNoVerification.this, GetStartedWindow.class));
    }

    //Button Get OTP
    public void getOTP(View view)
    {
        if (!mobileNo.getText().toString().trim().isEmpty())
        {
            if ((mobileNo.getText().toString().trim()).length() == 10)
            {
                progressBarGetOtp.setVisibility(View.VISIBLE);
                btn_get_otp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + mobileNo.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        MobileNoVerification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBarGetOtp.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBarGetOtp.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);
                                Toast.makeText(MobileNoVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBarGetOtp.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(MobileNoVerification.this, OtpVerification.class);
                                intent.putExtra("mobileno", mobileNo.getText().toString());
                                intent.putExtra("checkBackendOtp",s);
                                startActivity(intent);
                            }
                        }
                );

//                Intent intent = new Intent(MobileNoVerification.this, OtpVerification.class);
//                intent.putExtra("mobileno",mobileNo.getText().toString());
//                startActivity(intent);
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
    }
}