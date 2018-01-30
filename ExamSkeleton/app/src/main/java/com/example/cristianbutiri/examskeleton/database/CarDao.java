package com.example.cristianbutiri.examskeleton.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.cristianbutiri.examskeleton.model.Car;

import java.util.List;


/**
 * Created by crist on 28-Jan-18.
 */
@Dao
public interface CarDao {
    @Query("SELECT * FROM Car")
    List<Car> findAll();

    @Query("SELECT * FROM Car WHERE id = :id")
    Car findOne(Long id);

    @Insert
    void save(Car... b);

    @Update
    void update(Car... b);

    @Delete
    void delete(Car... b);

    @Query("DELETE FROM Car")
    void deleteAll();
}
