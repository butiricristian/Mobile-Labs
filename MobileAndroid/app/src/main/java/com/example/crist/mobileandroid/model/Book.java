package com.example.crist.mobileandroid.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by crist on 02-Nov-17.
 */
@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private Integer nrPages;
    private String endDate;

    public Book(Long id, String title, String author, String ISBN, Integer nrPages, String endDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.nrPages = nrPages;
        this.endDate = endDate;
    }

    @Ignore
    public Book(String title, String author, String ISBN, Integer nrPages, String endDate) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.nrPages = nrPages;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getNrPages() {
        return nrPages;
    }

    public void setNrPages(Integer nrPages) {
        this.nrPages = nrPages;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
