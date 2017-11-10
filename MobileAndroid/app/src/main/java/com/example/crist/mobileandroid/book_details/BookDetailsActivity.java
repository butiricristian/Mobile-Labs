package com.example.crist.mobileandroid.book_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.crist.mobileandroid.BookListActivity;
import com.example.crist.mobileandroid.BookRepository;
import com.example.crist.mobileandroid.R;
import com.example.crist.mobileandroid.model.Book;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView title = (TextView) findViewById(R.id.details_title);
        final TextView author = (TextView) findViewById(R.id.details_author);
        final TextView isbn = (TextView) findViewById(R.id.details_isbn);
        final TextView nrPages = (TextView) findViewById(R.id.details_nr_pages);

        title.setText(this.getIntent().getStringExtra("EXTRA_TITLE"));
        author.setText(this.getIntent().getStringExtra("EXTRA_AUTHOR"));
        isbn.setText(this.getIntent().getStringExtra("EXTRA_ISBN"));
        nrPages.setText(this.getIntent().getStringExtra("EXTRA_NR_PAGES"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookRepository.updateBook(new Book(title.getText().toString(), author.getText().toString(), isbn.getText().toString(), Integer.valueOf(nrPages.getText().toString())));
                finish();
            }
        });
    }

}
