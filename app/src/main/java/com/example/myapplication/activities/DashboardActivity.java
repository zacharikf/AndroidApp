package com.example.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.myapplication.R;
import com.example.myapplication.adapters.SpotAdapter;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.models.StudySpot;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabAdd;
    DataBaseHelper myDb;
    SpotAdapter adapter;
    List<StudySpot> spotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Κρύβουμε την πάνω μπάρα
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerViewSpots);
        fabAdd = findViewById(R.id.fabAdd); // Συνδέουμε το νέο FAB κουμπί!
        myDb = new DataBaseHelper(this);
        spotList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpotAdapter(this, spotList);
        recyclerView.setAdapter(adapter);

        // Η ΛΥΣΗ: Όταν πατάς το (+), πήγαινε στο AddSpotActivity
        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, AddSpotActivity.class));
        });
    }

    // Το onResume τρέχει κάθε φορά που επιστρέφουμε σε αυτή την οθόνη
    @Override
    protected void onResume() {
        super.onResume();
        loadSpots();
    }

    private void loadSpots() {
        spotList.clear(); // Καθαρίζουμε την παλιά λίστα
        Cursor cursor = myDb.getAllSpots();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                double lat = cursor.getDouble(3);
                double lon = cursor.getDouble(4);
                // Προσθήκη στη λίστα
                spotList.add(new StudySpot(id, name, desc, lat, lon));
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged(); // Ενημερώνουμε την οθόνη
    }
}