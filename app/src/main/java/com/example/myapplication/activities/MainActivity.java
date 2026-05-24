package com.example.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpotAdapter;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.models.StudySpot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    ListView listView;
    Button btnGoToAdd;
    ArrayList<StudySpot> spotList;
    SpotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        listView = findViewById(R.id.spotListView);
        btnGoToAdd = findViewById(R.id.btnGoToAdd);

        btnGoToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddSpotActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudySpot clickedSpot = spotList.get(position);
                Intent intent = new Intent(MainActivity.this, SpotDetailsActivity.class);
                intent.putExtra("NAME", clickedSpot.getName());
                intent.putExtra("DESC", clickedSpot.getDescription());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Refreshes list automatically when returning
    }

    private void loadData() {
        spotList = new ArrayList<>();
        Cursor res = myDb.getAllSpots();

        while (res.moveToNext()) {
            int id = res.getInt(0);
            String name = res.getString(1);
            String desc = res.getString(2);
            spotList.add(new StudySpot(id, name, desc, res.getDouble(3), res.getDouble(4)));
        }

        adapter = new SpotAdapter(this, spotList);
        listView.setAdapter(adapter);
    }
}