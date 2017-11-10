package com.example.crist.mobileandroid;

import android.util.Log;

import com.example.crist.mobileandroid.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by crist on 06-Nov-17.
 */

public class BookRepository {
    private static List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("Pragmatic Programmer", "Andrew Hunt", "123344325", 340),
            new Book("Pragmatic Programmer 2", "Andrew Hunt", "1233421345", 340),
            new Book("Harry Potter 1", "J. K. Rowling", "123344326", 343),
            new Book("Harry Potter 2", "J. K. Rowling", "123344327", 435),
            new Book("Harry Potter 3", "J. K. Rowling", "123344328", 436),
            new Book("Harry Potter 4", "J. K. Rowling", "123344329", 765)
    ));

    public static List<Book> getBooks(){
        return books;
    }

    public static void addBook(Book b){
        books.add(b);
    }

    public static void updateBook(Book b){
        for(Book book : books){
            if(book.getISBN().equals(b.getISBN())){
                book.setAuthor(b.getAuthor());
                book.setTitle(b.getTitle());
                book.setNrPages(b.getNrPages());
            }
        }
        Log.d("BookRepo update", books.toString());
    }
}
