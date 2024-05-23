package com.mirea.usatyukds.employeedb;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

public class App extends Application {
    public static App instance;
    private AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static App getInstance() {
        return instance;
    }
    public AppDatabase getDatabase() {
        return database;
    }
}

