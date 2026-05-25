package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide(); // Κρύβουμε την μπάρα
        setContentView(R.layout.activity_splash);

        // Βάζουμε χρονόμετρο 2 δευτερολέπτων (2000 ms)
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish(); // Κλείνουμε το Splash για να μην μπορεί να γυρίσει πίσω με το κουμπί "Back"
        }, 2000);
    }
}