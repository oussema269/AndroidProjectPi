package com.example.android_project_pi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_project_pi.DAO.Trajet_Dao;
import com.example.android_project_pi.model.Trajet;

@Database(entities = {Trajet.class}, version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance;
    public abstract Trajet_Dao trajet_dao() ;

    public static DataBase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                        DataBase.class, "bdTest").allowMainThreadQueries().build();
        }
        return instance ;
    }
}
