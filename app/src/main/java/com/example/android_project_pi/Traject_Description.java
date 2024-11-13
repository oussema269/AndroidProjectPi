package com.example.android_project_pi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project_pi.database.DataBase;
import com.example.android_project_pi.model.Trajet;

public class Traject_Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_traject_description);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //binding
        EditText textPointDeDepart = findViewById(R.id.textPointDeDepart);
        EditText textDestination = findViewById(R.id.textDestination);
        EditText textDateDepart = findViewById(R.id.textDateDepart);
        EditText textHeureDepart = findViewById(R.id.textHeureDepart);
        EditText textPrix = findViewById(R.id.textPrix);
        Button supprimer_btn = findViewById(R.id.supprimer_btn);
        Button modifier_btn = findViewById(R.id.modifier_btn);


        //récupération des données
        Intent  intent =getIntent();
        int id = intent.getIntExtra("id",0);
        textPointDeDepart.setText(intent.getStringExtra("pointDeDepart"));
        textDestination.setText(intent.getStringExtra("destination"));
        textDateDepart.setText(intent.getStringExtra("date"));
        textHeureDepart.setText(intent.getStringExtra("HeureDepart"));
       textPrix.setText(String.valueOf(intent.getIntExtra("prix",0)));
        // Configuration du bouton de suppression
        supprimer_btn.setOnClickListener(v -> {
            DataBase db = DataBase.getInstance(this);
            Trajet trajetASupprimer = new Trajet();
            trajetASupprimer.setId(id);
            db.trajet_dao().deleteOne(trajetASupprimer);
            Toast.makeText(this, "Trajet supprimé avec succès", Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent(Traject_Description.this, ShowTraject.class);
            startActivity(returnIntent);
            finish();
        });
        modifier_btn.setOnClickListener(v -> {
            // Récupération des nouvelles valeurs modifiées par l'utilisateur
            String pointDeDepartModifie = textPointDeDepart.getText().toString();
            String destinationModifie = textDestination.getText().toString();
            String dateDepartModifie = textDateDepart.getText().toString();
            String heureDepartModifie = textHeureDepart.getText().toString();
            int prixModifie = Integer.parseInt(textPrix.getText().toString());

            // Mise à jour du trajet avec les nouvelles valeurs
            Trajet trajetModifie = new Trajet();
            trajetModifie.setId(id);
            trajetModifie.setPointDeDepart(pointDeDepartModifie);
            trajetModifie.setDestination(destinationModifie);
            trajetModifie.setDate(dateDepartModifie);  // Assurez-vous de bien manipuler la date
            trajetModifie.setHeureDepart(heureDepartModifie);
            trajetModifie.setPrix(prixModifie);

            // Mise à jour du trajet dans la base de données
            DataBase db = DataBase.getInstance(this);  // Assurez-vous que vous avez une instance de votre DB
            db.trajet_dao().updateOne(trajetModifie);

            // Affichage d'un message Toast pour confirmer la modification
            Toast.makeText(this, "Trajet modifié avec succès", Toast.LENGTH_SHORT).show();

            // Retour à l'activité précédente ou à une autre activité
            Intent returnIntent = new Intent(Traject_Description.this, ShowTraject.class);  // Remplacez MainActivity par l'activité souhaitée
            startActivity(returnIntent);
            finish();  // Fin de l'activité actuelle
        });

    }
}