package com.example.myapplication.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch switchNotif = findViewById(R.id.switchNotifications);

        // 1. Φόρτωση της αποθηκευμένης ρύθμισης
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isNotifEnabled = prefs.getBoolean("Notifications", true);
        switchNotif.setChecked(isNotifEnabled);

        // 2. Αποθήκευση όταν ο χρήστης αλλάζει τον διακόπτη
        switchNotif.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Notifications", isChecked);
            editor.apply(); // Η αποθήκευση γίνεται μόνιμα!

            if(isChecked) {
                Toast.makeText(this, "Οι ειδοποιήσεις ενεργοποιήθηκαν", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Οι ειδοποιήσεις απενεργοποιήθηκαν", Toast.LENGTH_SHORT).show();
            }
        });
    }
}