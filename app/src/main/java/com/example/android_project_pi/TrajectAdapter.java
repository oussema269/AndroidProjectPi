package com.example.android_project_pi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_project_pi.model.Trajet;

import java.util.List;

public class TrajectAdapter extends RecyclerView.Adapter<TrajetViewHolder> {
Context cnx;
List<Trajet> trajets ;
TrajectAdapter(Context cnx, List<Trajet> trajets){
    this.cnx=cnx;
    this.trajets=trajets;
}

    @NonNull
    @Override
    public TrajetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(cnx).inflate(R.layout.single_traject_card,parent,false);
        return new TrajetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrajetViewHolder holder, int position) {
    Trajet trajet = trajets.get(position);
        holder.pointDeDepart.setText(trajets.get(position).getPointDeDepart());
        holder.destination.setText(trajets.get(position).getDestination());
        holder.prix.setText(String.valueOf(trajets.get(position).getPrix()));
        holder.button.setOnClickListener(v->{
           Toast.makeText(cnx,"Trajet  : "+trajets.get(position).getPrix(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(cnx, Traject_Description.class);
            intent.putExtra("id",trajets.get(position).getId());
            intent.putExtra("pointDeDepart",trajets.get(position).getPointDeDepart());
            intent.putExtra("destination",trajets.get(position).getDestination());
            intent.putExtra("prix", trajets.get(position).getPrix());
            intent.putExtra("date",trajets.get(position).getDate());
            intent.putExtra("HeureDepart",trajets.get(position).getHeureDepart());
            cnx.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return trajets.size();
    }
}
