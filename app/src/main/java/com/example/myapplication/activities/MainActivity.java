package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);


        MaterialCardView cardBrowse = findViewById(R.id.cardBrowse);
        MaterialCardView cardAdd = findViewById(R.id.cardAdd);
        MaterialCardView cardMap = findViewById(R.id.cardMap);
        MaterialCardView cardSettings = findViewById(R.id.cardSettings);


        cardBrowse.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DashboardActivity.class)));

        cardAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddSpotActivity.class)));

        cardMap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapActivity.class)));

        cardSettings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
}