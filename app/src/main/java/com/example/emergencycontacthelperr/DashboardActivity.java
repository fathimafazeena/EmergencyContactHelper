package com.example.emergencycontacthelperr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button addContact, viewContacts, quickAccess, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addContact = findViewById(R.id.addContact);
        viewContacts = findViewById(R.id.viewContacts);
        quickAccess = findViewById(R.id.quickAccess);
        logout = findViewById(R.id.logout);

        addContact.setOnClickListener(v ->
                startActivity(new Intent(this, AddContactActivity.class)));

        viewContacts.setOnClickListener(v ->
                startActivity(new Intent(this, ContactListActivity.class)));

        quickAccess.setOnClickListener(v ->
                startActivity(new Intent(this, QuickAccessActivity.class)));

        logout.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));
    }
}