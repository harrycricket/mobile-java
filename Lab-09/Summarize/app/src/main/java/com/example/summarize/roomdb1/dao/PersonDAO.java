package com.example.summarize.roomdb1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.summarize.roomdb1.model.Person;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE id IN (:id)")
    Person getById(int id);

    @Query("SELECT * FROM person WHERE (first_name + last_name) LIKE :text")
    List<Person> findByName(String text);

    @Query("SELECT * FROM person WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    List<Person> findByName(String first, String last);

    @Insert
    void insert(Person entity);
    @Update
    void update (Person entity);
    @Delete
    void delete(Person entity);
}
