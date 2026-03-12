package com.example.emergencycontacthelperr;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;
    ImageButton backBtn;
    ArrayList<String> phoneNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listView = findViewById(R.id.listView);
        backBtn = findViewById(R.id.backBtn);
        db = new DatabaseHelper(this);

        backBtn.setOnClickListener(v -> finish());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String phoneNumber = phoneNumbers.get(position);
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        loadContacts();
    }

    private void loadContacts() {
        Cursor cursor = db.getAllContacts();
        phoneNumbers.clear();

        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No contacts found", Toast.LENGTH_SHORT).show();
            if (cursor != null) cursor.close();
            return;
        }

        ArrayList<String> contactDisplayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            String relationship = cursor.getString(cursor.getColumnIndexOrThrow("relationship"));

            contactDisplayList.add(name + " (" + relationship + ")\n" + phone);
            phoneNumbers.add(phone);
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactDisplayList
        );

        listView.setAdapter(adapter);
    }
}