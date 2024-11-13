package com.example.uberproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.modelUser.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
SharedPreferences loginPref;
    private AppDataBase appDataBase;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private byte[] imageBytes;

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


        Button buttonImportImage = findViewById(R.id.button_import_image);
        imageView = findViewById(R.id.profileImage);

        buttonImportImage.setOnClickListener(v -> openGallery());
        Button save=findViewById(R.id.savebtn);
        save.setOnClickListener(view -> {
            saveUserImage(email);
            finish();
        });

        }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private void saveUserImage(String email) {
        if (imageBytes != null) {
            // Récupère l'utilisateur par email
            User user = appDataBase.userDao().getUserByEmail(email);
            if (user != null) {
                // Met à jour l'image de profil de l'utilisateur
                user.setProfileImage(imageBytes);

                // Exécuter la mise à jour dans un thread séparé pour éviter le blocage de l'interface
                new Thread(() -> {
                    appDataBase.userDao().updateUser(user);
                }).start();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);

                // Convertir en tableau d'octets pour l'enregistrement dans la base de données
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                imageBytes = baos.toByteArray();
                String email = loginPref.getString("email", "email not found");

                // Appeler saveUserImage avec l'email de l'utilisateur pour enregistrer l'image
                saveUserImage(email);  // Assure-toi que 'email' contient bien l'email de l'utilisateur

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    void afficherProfile(String email){
        appDataBase = AppDataBase.getInstance(this);
        User user = appDataBase.userDao().getUserByEmail(email);

        TextView emailProfile = findViewById(R.id.emailProfile);
        TextView roleProfile = findViewById(R.id.roleProfile);
        TextView usernameProfile = findViewById(R.id.usernameProfile);
        imageView = findViewById(R.id.profileImage);

        if (user != null) {
            emailProfile.setText(user.getEmail());
            roleProfile.setText(user.getRole());
            usernameProfile.setText(user.getUsername());


            // Affiche l'image si elle existe
            if (user.getProfileImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user.getProfileImage(), 0, user.getProfileImage().length);
                imageView.setImageBitmap(bitmap);
            }
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