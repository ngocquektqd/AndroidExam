package com.example.lengocque;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private  static AppDatabase appdatabase;
    public abstract UserDao userDao();
    public static AppDatabase getAppDatabase(Context context){
        if(appdatabase==null){
            appdatabase= Room.databaseBuilder(context,
                    AppDatabase.class,"User.db").allowMainThreadQueries().build();
        }
        return  appdatabase;
    }
}
