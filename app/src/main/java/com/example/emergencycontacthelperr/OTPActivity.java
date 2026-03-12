package com.example.emergencycontacthelperr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {

    Button verifyBtn;
    EditText otp1, otp2, otp3, otp4;
    TextView targetEmailTV;
    String userEmail;
    int expectedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        userEmail = getIntent().getStringExtra("email");
        expectedOTP = getIntent().getIntExtra("otp", -1);

        verifyBtn = findViewById(R.id.verifyBtn);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        targetEmailTV = findViewById(R.id.tvTargetEmail);

        if (userEmail != null) {
            targetEmailTV.setText("Enter the 4-digit code sent to\n" + userEmail);
        }

        verifyBtn.setOnClickListener(v -> {
            String enteredOTP = otp1.getText().toString() +
                    otp2.getText().toString() +
                    otp3.getText().toString() +
                    otp4.getText().toString();

            if (enteredOTP.length() < 4) {
                Toast.makeText(this, "Please enter all 4 digits", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int enteredValue = Integer.parseInt(enteredOTP);
                if (enteredValue == expectedOTP) {
                    Intent intent = new Intent(this, ResetPasswordActivity.class);
                    intent.putExtra("email", userEmail);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid characters in OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}