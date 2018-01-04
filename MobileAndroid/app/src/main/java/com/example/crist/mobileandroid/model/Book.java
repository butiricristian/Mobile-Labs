package com.example.crist.mobileandroid.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private Integer nrPages;
    private String endDate;
    private String user_id;

    public Book(Long id, String title, String author, String ISBN, Integer nrPages, String endDate, String user_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.nrPages = nrPages;
        this.endDate = endDate;
        this.user_id = user_id;
    }

    @Ignore
    public Book(){
    }

    @Ignore
    public Book(String title, String author, String ISBN, Integer nrPages, String endDate, String user_id) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.nrPages = nrPages;
        this.endDate = endDate;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (ISBN != null ? !ISBN.equals(book.ISBN) : book.ISBN != null) return false;
        if (nrPages != null ? !nrPages.equals(book.nrPages) : book.nrPages != null) return false;
        return endDate != null ? endDate.equals(book.endDate) : book.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        result = 31 * result + (nrPages != null ? nrPages.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
