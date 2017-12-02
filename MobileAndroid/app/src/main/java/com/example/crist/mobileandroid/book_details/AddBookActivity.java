package com.example.crist.mobileandroid.book_details;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.crist.mobileandroid.R;
import com.example.crist.mobileandroid.database.BookDao;
import com.example.crist.mobileandroid.database.DatabaseProvider;
import com.example.crist.mobileandroid.model.Book;

import java.sql.Date;
import java.util.Calendar;

public class AddBookActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    BookDao bookDao;
    Calendar calendar;
    EditText title;
    EditText author;
    EditText isbn;
    EditText nrPages;
    EditText endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.add_book_title);
        author = (EditText) findViewById(R.id.add_book_author);
        isbn = (EditText) findViewById(R.id.add_book_isbn);
        nrPages = (EditText) findViewById(R.id.add_book_nr_pages);
        endDate = (EditText) findViewById(R.id.add_book_end_date);
        calendar = Calendar.getInstance();
        endDate.setOnClickListener(this);

        bookDao = DatabaseProvider.getDatabaseInstance(getApplicationContext()).getBookDao();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            bookDao.save(new Book(
                    title.getText().toString(),
                    author.getText().toString(),
                    isbn.getText().toString(),
                    Integer.valueOf(nrPages.getText().toString()),
                    endDate.getText().toString())
            );
            finish();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        this.endDate.setText(new StringBuilder()
                .append(datePicker.getDayOfMonth())
                .append("-")
                .append(datePicker.getMonth())
                .append("-")
                .append(datePicker.getYear())
        );
    }

    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddBookActivity.this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)
        );
        datePickerDialog.show();
    }
}
