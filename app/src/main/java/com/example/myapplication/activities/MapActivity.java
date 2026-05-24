package com.example.myapplication.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// The class must implement OnMapReadyCallback
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Find the map fragment and load it in the background
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // AUTH Campus Coordinates
        LatLng authCampus = new LatLng(40.6279, 22.9580);

        // Drop a temporary test pin
        mMap.addMarker(new MarkerOptions().position(authCampus).title("AUTH Campus"));

        // Move the camera and zoom in (15f is street level)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(authCampus, 15f));
    }
}