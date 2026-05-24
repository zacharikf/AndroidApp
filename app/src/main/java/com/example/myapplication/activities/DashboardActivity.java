package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    MaterialCardView cardBrowse, cardAdd, cardTopRated, cardSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Link code to XML
        cardBrowse = findViewById(R.id.cardBrowse);
        cardAdd = findViewById(R.id.cardAdd);
        cardTopRated = findViewById(R.id.cardTopRated);
        cardSettings = findViewById(R.id.cardSettings);

        // Click Listener for Browse (Goes to your existing list)
        cardBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            }
        });

        // Click Listener for Add (Goes to your existing form)
        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AddSpotActivity.class));
            }
        });

        // Inside DashboardActivity.java, update this listener:
        cardTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MapActivity.class));
            }
        });

        cardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Settings coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}