package com.example.lengocque;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);
    @Query("SELECT COUNT(`full name`) FROM `user feedback` ")
    int count();
}
