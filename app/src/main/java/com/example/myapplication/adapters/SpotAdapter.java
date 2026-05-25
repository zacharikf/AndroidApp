package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.activities.MapActivity;
import com.example.myapplication.models.StudySpot;
import java.util.List;
import com.example.myapplication.activities.SpotDetailsActivity;

public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.SpotViewHolder> {

    private Context context;
    private List<StudySpot> spotList;

    public SpotAdapter(Context context, List<StudySpot> spotList) {
        this.context = context;
        this.spotList = spotList;
    }

    @NonNull
    @Override
    public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_spot, parent, false);
        return new SpotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotViewHolder holder, int position) {
        StudySpot currentSpot = spotList.get(position);

        // 1. Βάζουμε το Όνομα και την Περιγραφή
        holder.txtSpotName.setText(currentSpot.getName());
        holder.txtSpotDesc.setText(currentSpot.getDescription());

        // 2. Μορφοποιούμε και βάζουμε τις συντεταγμένες! (Π.χ. Lat: 40.62, Lon: 22.95)
        String coordinatesText = String.format("Lat: %.4f, Lon: %.4f", currentSpot.getLatitude(), currentSpot.getLongitude());
        holder.txtSpotCoords.setText(coordinatesText);

        // 3. Δυναμική αλλαγή εικονιδίου ανάλογα με το όνομα του μέρους (Προαιρετικό αλλά εντυπωσιακό)
        String nameLower = currentSpot.getName().toLowerCase();
        if (nameLower.contains("βιβλιοθήκη") || nameLower.contains("library")) {
            holder.imgSpotIcon.setImageResource(R.drawable.ic_logo); // Αν έχεις το ic_library βάλτο εδώ!
        } else if (nameLower.contains("καφέ") || nameLower.contains("cafe")) {
            holder.imgSpotIcon.setImageResource(R.drawable.ic_logo); // Αν έχεις το ic_cafe βάλτο εδώ!
        } else {
            holder.imgSpotIcon.setImageResource(R.drawable.ic_logo);
        }
        // 1. Κλικ σε ΟΛΟΚΛΗΡΗ την κάρτα (ανοίγει τις λεπτομέρειες)
        holder.itemView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(context, SpotDetailsActivity.class);
            intent.putExtra("SPOT_NAME", currentSpot.getName());
            intent.putExtra("SPOT_DESC", currentSpot.getDescription());
            intent.putExtra("SPOT_LAT", currentSpot.getLatitude());
            intent.putExtra("SPOT_LON", currentSpot.getLongitude());
            context.startActivity(intent);
        });

        // 2. ΝΕΟ: Κλικ ΜΟΝΟ στο κουμπάκι "Εμφάνιση στον χάρτη"
        holder.btnShowOnMap.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(context, MapActivity.class);
            intent.putExtra("FOCUS_LAT", currentSpot.getLatitude());
            intent.putExtra("FOCUS_LON", currentSpot.getLongitude());
            intent.putExtra("SHOULD_FOCUS", true);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }



    public static class SpotViewHolder extends RecyclerView.ViewHolder {
        TextView txtSpotName, txtSpotDesc, txtSpotCoords;
        ImageView imgSpotIcon;


        android.widget.LinearLayout btnShowOnMap;

        public SpotViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSpotName = itemView.findViewById(R.id.txtSpotName);
            txtSpotDesc = itemView.findViewById(R.id.txtSpotDesc);
            txtSpotCoords = itemView.findViewById(R.id.txtSpotCoords);
            imgSpotIcon = itemView.findViewById(R.id.imgSpotIcon);


            btnShowOnMap = itemView.findViewById(R.id.btnLayoutShowOnMap);
        }
    }
}