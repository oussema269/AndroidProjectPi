package com.example.uberproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.model.User;

public class ProfileActivity extends AppCompatActivity {
SharedPreferences loginPref;
    private AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //binding

        Button logOut = findViewById(R.id.logOutButton);
        loginPref = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        String email = loginPref.getString("email", "email not found");

        Log.d("ProfileActivity", "Email récupéré : " + email);

        SharedPreferences.Editor editor=loginPref.edit();


        loginPref = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        afficherProfile(email);
        logOut.setOnClickListener(view -> {
            editor.clear();
            editor.apply();
            finish();
            Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
        });
    }

    void afficherProfile(String email){
        appDataBase = AppDataBase.getInstance(this);
        User user = appDataBase.userDao().getUserByEmail(email);
        TextView emailProfile = findViewById(R.id.emailProfile);
        TextView roleProfile = findViewById(R.id.roleProfile);
        TextView usernameProfile = findViewById(R.id.usernameProfile);
        if (user != null) {
            System.out.println("**********************************"+user.getEmail()+"le role "+ user.getRole()+"le username" + user.getUsername());
            emailProfile.setText(user.getEmail());
            roleProfile.setText(user.getRole());
            usernameProfile.setText(user.getUsername());
        }

        Button logOut = findViewById(R.id.logOutButton);
        SharedPreferences.Editor editor = loginPref.edit();

        logOut.setOnClickListener(view -> {
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}