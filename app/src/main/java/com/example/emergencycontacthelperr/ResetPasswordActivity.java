package com.example.emergencycontacthelperr;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText newPassET, confirmPassET;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        db = new DatabaseHelper(this);

        // Receive the email passed from OTPActivity
        userEmail = getIntent().getStringExtra("email");

        Button resetBtn = findViewById(R.id.resetBtn);
        newPassET = findViewById(R.id.newPass);
        confirmPassET = findViewById(R.id.confirmPass);

        resetBtn.setOnClickListener(v -> {
            String newPassword = newPassET.getText().toString().trim();
            String confirmPassword = confirmPassET.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userEmail == null || userEmail.isEmpty()) {
                Toast.makeText(this, "Error: session lost", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update password in database
            boolean updated = db.updatePassword(userEmail, newPassword);
            if (updated) {
                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SuccessActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}