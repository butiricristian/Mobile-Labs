package com.example.crist.mobileandroid;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.crist.mobileandroid.book_details.AddBookActivity;
import com.example.crist.mobileandroid.book_details.BookDetailsActivity;
import com.example.crist.mobileandroid.database.AppDatabase;
import com.example.crist.mobileandroid.database.BookDao;
import com.example.crist.mobileandroid.database.DatabaseProvider;
import com.example.crist.mobileandroid.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Book> books;
    BookAdapter ba;
    BookDao bookDao;
    FloatingActionButton addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView booksList = (ListView) findViewById(R.id.books_list);
        addBook = (FloatingActionButton) findViewById(R.id.add_book);

        AppDatabase db = DatabaseProvider.getDatabaseInstance(getApplicationContext());
        bookDao = db.getBookDao();
        books = bookDao.findAll();

        ba = new BookAdapter(this, books);
        booksList.setAdapter(ba);


        booksList.setOnItemClickListener(this);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddBookActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.books.clear();
        this.books.addAll(bookDao.findAll());
        ba.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("EXTRA_TITLE", books.get(i).getTitle());
        intent.putExtra("EXTRA_AUTHOR", books.get(i).getAuthor());
        intent.putExtra("EXTRA_ISBN", books.get(i).getISBN());
        intent.putExtra("EXTRA_NR_PAGES", String.valueOf(books.get(i).getNrPages()));
        intent.putExtra("EXTRA_END_DATE", books.get(i).getEndDate());
        startActivity(intent);
    }

    private class BookAdapter extends ArrayAdapter<Book> {

        private final Context context;
        private final List<Book> values;

        BookAdapter(Context context, List<Book> values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_layout, parent, false);
            TextView bookTitle = (TextView) rowView.findViewById(R.id.book_title);
            TextView nrPages = (TextView) rowView.findViewById(R.id.book_nr_pages);
            ImageView bookIcon = (ImageView) rowView.findViewById(R.id.book_image);

            bookTitle.setText(values.get(position).getTitle());
            nrPages.setText(String.valueOf(values.get(position).getNrPages()));
            bookIcon.setImageResource(R.mipmap.ic_launcher);

            return rowView;

        }
    }
}
