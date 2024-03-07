package com.example.swiggyclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity
{
    EditText inputOtp1,inputOtp2,inputOtp3,inputOtp4,inputOtp5,inputOtp6;
    TextView txtGetMobileNo,txtResendOtp;
    String getOtpFromBackend;
    ProgressBar progressBarContinue;
    Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        inputOtp1 = findViewById(R.id.inputOtp1);
        inputOtp2 = findViewById(R.id.inputOtp2);
        inputOtp3 = findViewById(R.id.inputOtp3);
        inputOtp4 = findViewById(R.id.inputOtp4);
        inputOtp5 = findViewById(R.id.inputOtp5);
        inputOtp6 = findViewById(R.id.inputOtp6);
        txtResendOtp = findViewById(R.id.txtResendOtp);
        progressBarContinue = findViewById(R.id.progressBarContinue);
        btn_continue = findViewById(R.id.btn_continue);
        txtGetMobileNo = findViewById(R.id.txtGetMobileNo);
        txtGetMobileNo.setText(String.format("+91-%s",getIntent().getStringExtra("mobileno")));
        
        getOtpFromBackend = getIntent().getStringExtra("checkBackendOtp");

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (!inputOtp1.getText().toString().trim().isEmpty() && !inputOtp2.getText().toString().trim().isEmpty() && !inputOtp3.getText().toString().trim().isEmpty() && !inputOtp4.getText().toString().trim().isEmpty() && !inputOtp5.getText().toString().trim().isEmpty() && !inputOtp6.getText().toString().trim().isEmpty())
                {
                    String enterOtp = inputOtp1.getText().toString() + inputOtp2.getText().toString() + inputOtp3.getText().toString() + inputOtp4.getText().toString() + inputOtp5.getText().toString() + inputOtp6.getText().toString();

                    if (getOtpFromBackend!=null)
                    {
                        progressBarContinue.setVisibility(View.VISIBLE);
                        btn_continue.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getOtpFromBackend,enterOtp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarContinue.setVisibility(View.GONE);
                                        btn_continue.setVisibility(View.VISIBLE);
                                        if (task.isSuccessful())
                                        {
                                            Intent intent = new Intent(OtpVerification.this,PersonalDetails.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(OtpVerification.this, "Enter Current OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(OtpVerification.this, "OOPS...Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(this, "OTP Verify", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OtpVerification.this, "Please Enter all nos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberMoveToNext();
        txtResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobileno"), 60, TimeUnit.SECONDS, OtpVerification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getOtpFromBackend = newotp;
                                Toast.makeText(OtpVerification.this, "OTP sent Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    public void backBtn(View view)
    {
        startActivity(new Intent(OtpVerification.this, MobileNoVerification.class));
    }

    //Automatically number move to next
    private void numberMoveToNext()
    {
        inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputOtp2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        inputOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputOtp3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        inputOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputOtp4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        inputOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputOtp5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        inputOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    inputOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }




}