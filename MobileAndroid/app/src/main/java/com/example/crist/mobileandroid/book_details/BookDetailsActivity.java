package com.example.crist.mobileandroid.book_details;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.crist.mobileandroid.R;
import com.example.crist.mobileandroid.database.BookDao;
import com.example.crist.mobileandroid.database.DatabaseProvider;
import com.example.crist.mobileandroid.model.Book;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    BookDao bookDao;
    Calendar calendar;
    TextView title;
    TextView author;
    TextView isbn;
    TextView nrPages;
    TextView endDate;
    BarChart barChart;
    Button removeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.details_title);
        author = (TextView) findViewById(R.id.details_author);
        isbn = (TextView) findViewById(R.id.details_isbn);
        nrPages = (TextView) findViewById(R.id.details_nr_pages);
        endDate = (TextView) findViewById(R.id.details_end_date);
        barChart = (BarChart) findViewById(R.id.details_chart);
        removeBtn = (Button) findViewById((R.id.remove_btn));

        final Long id = this.getIntent().getLongExtra("EXTRA_ID", 1L);
        title.setText(this.getIntent().getStringExtra("EXTRA_TITLE"));
        author.setText(this.getIntent().getStringExtra("EXTRA_AUTHOR"));
        isbn.setText(this.getIntent().getStringExtra("EXTRA_ISBN"));
        nrPages.setText(this.getIntent().getStringExtra("EXTRA_NR_PAGES"));

        endDate.setText(this.getIntent().getStringExtra("EXTRA_END_DATE"));
        endDate.setOnClickListener(this);

        bookDao = DatabaseProvider.getDatabaseInstance(getApplicationContext()).getBookDao();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDao.update(new Book(
                        id,
                        title.getText().toString(),
                        author.getText().toString(),
                        isbn.getText().toString(),
                        Integer.valueOf(nrPages.getText().toString()),
                        endDate.getText().toString())
                );
                finish();
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDao.delete(new Book(
                        id,
                        title.getText().toString(),
                        author.getText().toString(),
                        isbn.getText().toString(),
                        Integer.valueOf(nrPages.getText().toString()),
                        endDate.getText().toString())
                );
                finish();
            }
        });

        setBarChart();
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
        String[] dateString = endDate.getText().toString().split("-");
        Date date = new Date(Integer.valueOf(dateString[2]), Integer.valueOf(dateString[1]), Integer.valueOf(dateString[0]));
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookDetailsActivity.this, this,
                date.getYear(), date.getMonth(), date.getDate()
        );
        datePickerDialog.show();
    }

    private void setBarChart() {
        List<BarEntry> entries = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        String[] dateString = endDate.getText().toString().split("-");
        Calendar finalDate = Calendar.getInstance();
        finalDate.set(Integer.valueOf(dateString[2]), Integer.valueOf(dateString[1]), Integer.valueOf(dateString[0]));
        Long longNrDays = finalDate.getTimeInMillis() - today.getTimeInMillis();
        if (longNrDays < 0) {
            return;
        }
        Integer nrDays = (int) (longNrDays / (1000*60*60*24));
        Integer leftPages = Integer.valueOf(nrPages.getText().toString());
        Random r = new Random();
        for (Integer i = 1; i <= nrDays; i++) {
            Integer x = r.nextInt(leftPages);
            leftPages -= x;
            entries.add(new BarEntry(Float.valueOf(i), Float.valueOf(x)));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "Pages per day");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
    }
}
