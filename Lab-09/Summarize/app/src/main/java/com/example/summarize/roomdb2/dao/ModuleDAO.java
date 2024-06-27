package com.example.summarize.roomdb2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.summarize.roomdb2.model.Module;

import java.util.List;

@Dao
public interface ModuleDAO {
    @Query("SELECT * FROM module")
    List<Module> getAll();

    @Query("SELECT * FROM module WHERE id IN (:id)")
    Module getById(int id);

    @Query("SELECT * FROM module WHERE title LIKE :text")
    List<Module> findByTitle(String text);

    @Insert
    void insert(Module entity);
    @Update
    void update (Module entity);
    @Delete
    void delete(Module entity);
}

