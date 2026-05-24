package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SpotDetailsActivity extends AppCompatActivity {

    TextView tvName, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);

        tvName = findViewById(R.id.detailName);
        tvDesc = findViewById(R.id.detailDesc);

        // Receive data from the Intent
        String name = getIntent().getStringExtra("NAME");
        String desc = getIntent().getStringExtra("DESC");

        tvName.setText(name != null ? name : "Unknown Spot");
        tvDesc.setText(desc != null ? desc : "No description available.");
    }
}