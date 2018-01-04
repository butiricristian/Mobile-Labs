package com.example.crist.mobileandroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.crist.mobileandroid.model.Book;

import java.util.List;

/**
 * Created by crist on 01-Dec-17.
 */
@Dao
public interface BookDao {
    @Query("SELECT * FROM Book")
    List<Book> findAll();

    @Query("SELECT * FROM Book WHERE ISBN = :isbn")
    Book findOne(String isbn);

    @Insert
    void save(Book... b);

    @Update
    void update(Book... b);

    @Delete
    void delete(Book... b);

    @Query("DELETE FROM Book")
    void deleteAll();
}
