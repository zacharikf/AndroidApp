package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DataBaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class AddSpotActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    TextInputEditText editName, editDesc;
    Button btnSave;
    MapView mapPicker;

    // Μεταβλητές για την αποθήκευση της επιλεγμένης τοποθεσίας
    // Αρχική τιμή: Συντεταγμένες ΑΠΘ ως ασφάλεια
    double selectedLat = 40.6279;
    double selectedLng = 22.9580;
    Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Αρχικοποίηση της Osmdroid
        Configuration.getInstance().setUserAgentValue(getPackageName());

        setContentView(R.layout.activity_add_spot);

        myDb = new DataBaseHelper(this);
        editName = findViewById(R.id.editSpotName);
        editDesc = findViewById(R.id.editSpotDesc);
        btnSave = findViewById(R.id.btnSaveSpot);
        mapPicker = findViewById(R.id.mapPicker);

        // Ρύθμιση του Χάρτη Επιλογής
        mapPicker.setMultiTouchControls(true);
        mapPicker.getController().setZoom(16.0);
        GeoPoint startPoint = new GeoPoint(selectedLat, selectedLng);
        mapPicker.getController().setCenter(startPoint);

        // Τοποθέτηση του αρχικού marker στο ΑΠΘ
        currentMarker = new Marker(mapPicker);
        currentMarker.setPosition(startPoint);
        currentMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        currentMarker.setTitle("Αρχική Τοποθεσία");
        mapPicker.getOverlays().add(currentMarker);

        // Δημιουργία του μηχανισμού που "ακούει" τα κλικ στον χάρτη
        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                // 1. Αποθηκεύουμε τις νέες συντεταγμένες όπου πάτησε ο χρήστης
                selectedLat = p.getLatitude();
                selectedLng = p.getLongitude();

                // 2. Μετακινούμε την πινέζα στη νέα θέση
                currentMarker.setPosition(p);
                currentMarker.setTitle("Επιλεγμένο Σημείο");
                mapPicker.invalidate(); // Ανανέωση του χάρτη για να φανεί η αλλαγή

                Toast.makeText(AddSpotActivity.this, "Η τοποθεσία επιλέχθηκε!", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false; // Δεν χρειαζόμαστε το παρατεταμένο πάτημα
            }
        };

        // Προσθήκη του μηχανισμού κλικ στα επίπεδα (overlays) του χάρτη
        MapEventsOverlay eventsOverlay = new MapEventsOverlay(mapEventsReceiver);
        mapPicker.getOverlays().add(eventsOverlay);

        // Λειτουργία κουμπιού Αποθήκευσης
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String desc = editDesc.getText().toString().trim();

                if (name.isEmpty() || desc.isEmpty()) {
                    Toast.makeText(AddSpotActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Πλέον περνάμε τις ΠΡΑΓΜΑΤΙΚΕΣ δυναμικές συντεταγμένες selectedLat και selectedLng
                boolean inserted = myDb.insertSpot(name, desc, selectedLat, selectedLng);
                if (inserted) {
                    Toast.makeText(AddSpotActivity.this, "Spot Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddSpotActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() { super.onResume(); mapPicker.onResume(); }
    @Override
    protected void onPause() { super.onPause(); mapPicker.onPause(); }
}