package com.example.myapplication.activities;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import com.example.myapplication.R;
import com.example.myapplication.database.DataBaseHelper;

public class MapActivity extends AppCompatActivity {

    private MapView map;
    private DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ΚΡΙΣΙΜΟ: Αρχικοποίηση της βιβλιοθήκης OSMDroid ΠΡΙΝ το setContentView
        Configuration.getInstance().setUserAgentValue(getPackageName());

        setContentView(R.layout.activity_map);

        map = findViewById(R.id.mapView);
        map.setMultiTouchControls(true); // Ενεργοποιεί το zoom με δύο δάχτυλα

        // Κεντράρισμα του χάρτη στη Θεσσαλονίκη (Συντεταγμένες ΑΠΘ)
        map.getController().setZoom(16.0);
        map.getController().setCenter(new GeoPoint(40.6279, 22.9580));

        myDb = new DataBaseHelper(this);

        // Φόρτωση των Spots πάνω στον χάρτη
        loadSpotsOnMap();
    }

    private void loadSpotsOnMap() {
        Cursor cursor = myDb.getAllSpots();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1); // Στήλη 1: Όνομα
                double lat = cursor.getDouble(3);  // Στήλη 3: Latitude
                double lon = cursor.getDouble(4);  // Στήλη 4: Longitude

                // Δημιουργία πινέζας (Marker) για το συγκεκριμένο Study Spot
                Marker marker = new Marker(map);
                marker.setPosition(new GeoPoint(lat, lon));
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setTitle(name); // Όταν ο χρήστης πατάει την πινέζα, θα βλέπει το όνομα!

                // Προσθήκη της πινέζας στον χάρτη
                map.getOverlays().add(marker);
            }
            cursor.close();
        }
        map.invalidate(); // Ανανέωση του χάρτη για να σχεδιαστούν οι πινέζες
    }

    // Διαχείριση μνήμης για τον χάρτη
    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}