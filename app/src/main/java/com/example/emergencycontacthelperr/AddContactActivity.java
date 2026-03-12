package com.example.emergencycontacthelperr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameET, phoneET, relationshipET, notesET;
    private CheckBox quickAccessCB;
    private Button saveBtn;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        db = new DatabaseHelper(this);

        nameET = findViewById(R.id.contactName);
        phoneET = findViewById(R.id.phone);
        relationshipET = findViewById(R.id.relationship);
        notesET = findViewById(R.id.notes);
        quickAccessCB = findViewById(R.id.checkQuickAccess);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            String name = nameET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();
            String relationship = relationshipET.getText().toString().trim();
            String notes = notesET.getText().toString().trim();
            boolean isQuickAccess = quickAccessCB.isChecked();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Name and Phone are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isQuickAccess && db.getQuickAccessCount() >= 3) {
                Toast.makeText(this, "Quick Access limit (3) reached!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = db.insertContact(name, phone, relationship, notes, isQuickAccess);
            if (inserted) {
                Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save contact", Toast.LENGTH_SHORT).show();
            }
        });
    }
}