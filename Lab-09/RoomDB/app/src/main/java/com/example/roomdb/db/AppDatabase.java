package com.example.roomdb.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomdb.dao.PersonDAO;
import com.example.roomdb.model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}