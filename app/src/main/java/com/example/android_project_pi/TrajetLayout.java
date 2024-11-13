package com.example.android_project_pi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project_pi.database.DataBase;
import com.example.android_project_pi.model.Trajet;

import java.util.Date;


public class TrajetLayout extends AppCompatActivity {
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trajet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //binding =
        EditText pointDeDepart = findViewById(R.id.pointDeDepart);
        EditText destination = findViewById(R.id.destination);
        EditText prix = findViewById(R.id.prix);
        EditText tempsDepart = findViewById(R.id.tempsDepart);
        Button saveButton = findViewById(R.id.saveButton);

        db = DataBase.getInstance(this);
        saveButton.setOnClickListener(v -> {
                Trajet trajet = new Trajet();
                trajet.setPointDeDepart(pointDeDepart.getText().toString());
                trajet.setDestination(destination.getText().toString());
                trajet.setPrix(Integer.parseInt(prix.getText().toString()));
                 trajet.setHeureDepart(tempsDepart.getText().toString());
                trajet.setDate(String.valueOf(new Date()));

            db.trajet_dao().insertOne(trajet);
            System.out.println( "Trajet ajouté avec succès" +trajet.getPointDeDepart() + trajet.getDestination());
            pointDeDepart.setText("");
           destination.setText("");
           prix.setText("");
           tempsDepart.setText("");
            Intent intent = new Intent(TrajetLayout.this, ShowTraject.class);
            startActivity(intent);
        });
        //fel ajout yekedh date systeme w yzidou w kya3mel voir plus ychouf el date ken fet ynajem ya3mel ken delet
    }
}