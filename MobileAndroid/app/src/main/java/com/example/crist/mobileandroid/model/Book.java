package com.example.crist.mobileandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crist on 02-Nov-17.
 */

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private Integer nrPages;
    private List<Quote> quotes;

    public Book(String title, String author, String ISBN, Integer nrPages) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.nrPages = nrPages;
        this.quotes = new ArrayList<>();
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

    public void addQuote(Quote q){
        this.quotes.add(q);
    }
}
