package com.example.summarize.roomdb1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.summarize.roomdb1.dao.PersonDAO;
import com.example.summarize.roomdb1.model.Person;


@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}