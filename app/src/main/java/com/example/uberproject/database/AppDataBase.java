package com.example.uberproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.uberproject.modelUser.User;
import com.example.uberproject.modelUser.UserDao;

@Database(entities = {User.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
private static AppDataBase db;
public abstract UserDao userDao();
public static AppDataBase getInstance(Context context){
    if(db==null){
        db= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"uber.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
    return db;
}
}
