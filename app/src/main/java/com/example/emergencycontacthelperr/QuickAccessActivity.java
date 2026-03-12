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

public class QuickAccessActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;
    ImageButton backBtn;
    ArrayList<String> phones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickaccess);

        listView = findViewById(R.id.quickListView);
        backBtn = findViewById(R.id.backBtn);
        db = new DatabaseHelper(this);

        backBtn.setOnClickListener(v -> finish());

        loadQuickAccess();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String phoneNumber = phones.get(position);
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });
    }

    private void loadQuickAccess() {
        // Only load contacts where is_quick_access = 1
        Cursor cursor = db.getQuickAccessContacts();
        ArrayList<String> displayList = new ArrayList<>();
        phones.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String rel = cursor.getString(cursor.getColumnIndexOrThrow("relationship"));

                displayList.add("Call " + name + " (" + rel + ")");
                phones.add(phone);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "Please add quick access contacts first", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }
}