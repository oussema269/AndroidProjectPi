package com.example.uberproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.modelUser.User;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private AppDataBase appDataBase;
    List<User> users = new ArrayList<>();
    boolean check = false;
    public static final int PICK_TEXT_FILE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        appDataBase = AppDataBase.getInstance(this);

        addUser();
    }

    public void addUser() {
        // Récupérer le Spinner et définir l'adaptateur
        Spinner userTypeSpinner = findViewById(R.id.userTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        // Récupérer les champs d'édition et le bouton
        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);
        Button signUpButton = findViewById(R.id.signUpButton);

        // Ajouter un OnClickListener au bouton d'inscription
        signUpButton.setOnClickListener(v -> {
            // Récupérer la sélection du Spinner
            String selectedRole = userTypeSpinner.getSelectedItem().toString();

            // Vérifier si l'utilisateur existe déjà
            users = appDataBase.userDao().getAll();
            check = true; // Set check to true initially
            for (User u : users) {
                if (u.getUsername().equals(username.getText().toString()) || u.getEmail().equals(email.getText().toString())) {
                    check = false;
                    Toast.makeText(SignupActivity.this, "Utilisateur existe déjà", Toast.LENGTH_LONG).show();
                    break;
                }
            }

            // Valider l'email
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!email.getText().toString().matches(emailPattern)) {
                check = false;
                Toast.makeText(SignupActivity.this, "L'email n'est pas valide", Toast.LENGTH_LONG).show();
            }

            // Vérifier les mots de passe
            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                check = false;
                Toast.makeText(SignupActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_LONG).show();
            } else if (username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
                check = false;
                Toast.makeText(SignupActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
            }

            // Si toutes les vérifications sont passées, ajouter l'utilisateur
            if (check) {
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setRole(selectedRole); // Utiliser le rôle sélectionné
                appDataBase.userDao().insertOne(user);
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SignupActivity.this, "Inscription réussie", Toast.LENGTH_LONG).show();


            }

        });
    }






    int deleteAll() {
        return appDataBase.userDao().deleteAll();
    }
}
