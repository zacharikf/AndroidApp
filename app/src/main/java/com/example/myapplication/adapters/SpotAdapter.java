package com.example.myapplication.adapters; // Updated to match your project

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.StudySpot;

import java.util.ArrayList;

public class SpotAdapter extends ArrayAdapter<StudySpot> {

    public SpotAdapter(Context context, ArrayList<StudySpot> spots) {
        super(context, 0, spots);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflate the custom layout if it doesn't exist yet
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_spot, parent, false);
        }

        // Get the data item for this position
        StudySpot currentSpot = getItem(position);

        // Find the TextViews in the list_item_spot.xml layout
        TextView nameTextView = convertView.findViewById(R.id.rowSpotName);
        TextView descTextView = convertView.findViewById(R.id.rowSpotDesc);

        // Populate the data into the template view
        if (currentSpot != null) {
            nameTextView.setText(currentSpot.getName());
            descTextView.setText(currentSpot.getDescription());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}