package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DataBaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class AddSpotActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    TextInputEditText editName, editDesc;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot);

        myDb = new DataBaseHelper(this);
        editName = findViewById(R.id.editSpotName);
        editDesc = findViewById(R.id.editSpotDesc);
        btnSave = findViewById(R.id.btnSaveSpot);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String desc = editDesc.getText().toString().trim();

                if (name.isEmpty() || desc.isEmpty()) {
                    Toast.makeText(AddSpotActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Hardcoding AUTH coordinates for right now so the app compiles
                double defaultLat = 40.6279;
                double defaultLng = 22.9580;

                boolean inserted = myDb.insertSpot(name, desc, defaultLat, defaultLng);
                if (inserted) {
                    Toast.makeText(AddSpotActivity.this, "Spot Saved!", Toast.LENGTH_SHORT).show();
                    finish(); // Closes screen and goes back to Dashboard
                } else {
                    Toast.makeText(AddSpotActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
//comment