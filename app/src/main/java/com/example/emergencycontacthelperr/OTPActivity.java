package com.example.emergencycontacthelperr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {

    Button verifyBtn;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        userEmail = getIntent().getStringExtra("email");

        verifyBtn = findViewById(R.id.verifyBtn);

        verifyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResetPasswordActivity.class);
            intent.putExtra("email", userEmail);
            startActivity(intent);
        });
    }
}