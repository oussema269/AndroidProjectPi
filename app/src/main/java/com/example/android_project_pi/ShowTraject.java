package com.example.android_project_pi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_project_pi.database.DataBase;
import com.example.android_project_pi.model.Trajet;

import java.util.ArrayList;
import java.util.List;

public class ShowTraject extends AppCompatActivity {
        RecyclerView rv ;
        List<Trajet> trajets =new ArrayList<Trajet>() ;
        DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_traject);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //binding recycle view

        rv=findViewById(R.id.recyclerView);
        loadTrajets() ;
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(new TrajectAdapter(this,trajets));


    }
    private void loadTrajets() {
        db = DataBase.getInstance(this);
        trajets = db.trajet_dao().getAll();


    }
}