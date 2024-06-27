package com.example.summarize.roomdb2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.summarize.roomdb2.dao.ModuleDAO;
import com.example.summarize.roomdb2.model.Module;


@Database(entities = {Module.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModuleDAO moduleDAO();
}
