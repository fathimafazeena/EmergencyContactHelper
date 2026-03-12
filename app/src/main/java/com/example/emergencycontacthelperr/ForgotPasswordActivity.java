package com.example.emergencycontacthelperr;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button sendBtn;
    EditText emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

        db = new DatabaseHelper(this);

        sendBtn = findViewById(R.id.sendBtn);
        emailET = findViewById(R.id.emailET);

        sendBtn.setOnClickListener(v -> {
            String email = emailET.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = db.getReadableDatabase().rawQuery(
                    "SELECT * FROM users WHERE email=?", new String[]{email});

            if (cursor != null && cursor.getCount() > 0) {
                Random random = new Random();
                int otp = 1000 + random.nextInt(9000); // 4-digit OTP

                Intent intent = new Intent(this, OTPActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("otp", otp);
                startActivity(intent);

                Toast.makeText(this, "OTP Sent: " + otp, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
            }

            if (cursor != null) {
                cursor.close();
            }
        });
    }
}