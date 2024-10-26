package com.example.uberproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberproject.database.AppDataBase;
import com.example.uberproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class listUser extends AppCompatActivity {
    private AppDataBase appDataBase;
    RecyclerView rv;
    List<User> users=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv=findViewById(R.id.recyclerView);
        initUsers();
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        UserAdapter adapter=new UserAdapter(this,users);
        rv.setAdapter(adapter);
    }
    private void initUsers(){
        appDataBase = AppDataBase.getInstance(this);
        users = appDataBase.userDao().getAll();
        users.add(new User("islem","islem.mejri@esprit.tn","islem","Chauffeur",R.drawable.pdp));
        users.add(new User("mohsen","mohsen.mejri@esprit.tn","islem","Client",R.drawable.pdp));
        users.add(new User("mostfa","mostfa.mejri@esprit.tn","islem","Chauffeur",R.drawable.pdp));
    }
}