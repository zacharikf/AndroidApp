package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class SpotDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);

        TextView tvName = findViewById(R.id.detailName);
        TextView tvDesc = findViewById(R.id.detailDesc);
        TextView tvCoords = findViewById(R.id.detailCoords);
        Button btnBack = findViewById(R.id.btnBack);

        // ΠΑΙΡΝΟΥΜΕ τα δεδομένα που μας έστειλε το Intent
        String name = getIntent().getStringExtra("SPOT_NAME");
        String desc = getIntent().getStringExtra("SPOT_DESC");
        double lat = getIntent().getDoubleExtra("SPOT_LAT", 0.0);
        double lon = getIntent().getDoubleExtra("SPOT_LON", 0.0);

        // ΕΜΦΑΝΙΖΟΥΜΕ τα δεδομένα
        tvName.setText(name);
        tvDesc.setText(desc);
        tvCoords.setText(String.format("Latitude: %.4f\nLongitude: %.4f", lat, lon));

        // Λειτουργία κουμπιού επιστροφής
        btnBack.setOnClickListener(v -> finish()); // Κλείνει την οθόνη και σε γυρνάει πίσω
    }
}