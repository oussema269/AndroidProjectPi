package com.example.android_project_pi;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrajetViewHolder extends RecyclerView.ViewHolder {
    TextView pointDeDepart, destination, prix, date,heureDepart;
    Button button ;

    public TrajetViewHolder(@NonNull View itemView) {
        super(itemView);
        pointDeDepart=itemView.findViewById(R.id.Depart);
        destination=itemView.findViewById(R.id.destination);
        prix=itemView.findViewById(R.id.prix);
        date=itemView.findViewById(R.id.tempsDepart);
        button=itemView.findViewById(R.id.button2);
    }


}
