package com.example.android_project_pi.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_project_pi.model.Trajet;

import java.util.List;

@Dao
public interface Trajet_Dao {
    @Insert
    void insertOne(Trajet trajet);
    @Delete
    void deleteOne(Trajet trajet);
    @Query("SELECT * FROM trajet_table")
    List<Trajet> getAll();
    @Update
    void updateOne(Trajet trajet);

}
