package com.example.crist.mobileandroid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.crist.mobileandroid.model.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao getBookDao();
}
