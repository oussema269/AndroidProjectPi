package com.example.uberproject.modelUser;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertOne(User user);
    @Delete
    void deleteOne(User user);
    @Query("SELECT * FROM user")
    List<User> getAll();
    @Query("SELECT * FROM user where username=:username")
    User getUser(String username);
    @Query("DELETE FROM user ")
    int deleteAll();
    @Query("SELECT * FROM user where email=:email")
    User getUserByEmail(String email);
    @Query("DELETE FROM user where username=:username ")
    void deleteUser(String username);
    @Update
    void updateUser(User user);


}
