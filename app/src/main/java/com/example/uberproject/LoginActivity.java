package com.example.uberproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.modelUser.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences loginPref;
    static final String PREF_NAME = "login";
    private AppDataBase appDataBase;
    List<User> users = new ArrayList<>();
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation de l'interface de login
        login();
    }

    void login() {
        // Récupération des vues
        EditText email = findViewById(R.id.emailLogin);
        EditText password = findViewById(R.id.passwordLogin);
        Button logIn = findViewById(R.id.logInButton);

        // Récupération de la base de données
        appDataBase = AppDataBase.getInstance(this);
        users = appDataBase.userDao().getAll();

        // Listener pour le bouton de connexion
        logIn.setOnClickListener(view -> {
            // Vérifiez si l'utilisateur existe dans la base de données
            check = false;
            for (User u : users) {
                if (u.getEmail().equals(email.getText().toString()) && u.getPassword().equals(password.getText().toString())) {
                    check = true;
                    break; // L'utilisateur existe, on peut arrêter la boucle
                }
            }

            if (check) {
                // Si l'utilisateur existe, enregistrez l'email et le mot de passe dans SharedPreferences
                loginPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = loginPref.edit();
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                email.setText(email.getText().toString());
                password.setText(password.getText().toString());
                editor.apply();

                // Affichez un message de bienvenue
                Toast.makeText(LoginActivity.this, "Bienvenue dans l'application Uber", Toast.LENGTH_LONG).show();

                // Lancer l'activité ProfileActivity
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
            } else {
                // Si l'utilisateur n'est pas trouvé, affichez un message
                Toast.makeText(LoginActivity.this, "Utilisateur n'existe pas, veuillez vous inscrire", Toast.LENGTH_LONG).show();
            }
        });
    }
}
