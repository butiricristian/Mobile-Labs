package com.example.crist.mobileandroid;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crist.mobileandroid.book_details.AddBookActivity;
import com.example.crist.mobileandroid.book_details.BookDetailsActivity;
import com.example.crist.mobileandroid.database.AppDatabase;
import com.example.crist.mobileandroid.database.BookDao;
import com.example.crist.mobileandroid.database.DatabaseProvider;
import com.example.crist.mobileandroid.login.MainActivity;
import com.example.crist.mobileandroid.model.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ValueEventListener {

    List<Book> books;
    BookAdapter ba;
    BookDao bookDao;
    FloatingActionButton addBook;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    Button logOutBtn;
    Boolean isPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView booksList = (ListView) findViewById(R.id.books_list);
        addBook = (FloatingActionButton) findViewById(R.id.add_book);
        logOutBtn = (Button) findViewById(R.id.logOut);
        firebaseAuth = FirebaseAuth.getInstance();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
            }
        });

        AppDatabase db = DatabaseProvider.getDatabaseInstance(getApplicationContext());
        bookDao = db.getBookDao();
        bookDao.deleteAll();

        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference dbReference2 = firebaseDatabase.getReference("users");
        final DatabaseReference dbReference = dbReference2.child(firebaseAuth.getCurrentUser().getUid()).child("books");
        dbReference.addValueEventListener(this);

        books = new ArrayList<>();
        ba = new BookAdapter(this, books);
        booksList.setAdapter(ba);


        booksList.setOnItemClickListener(this);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference2.child(firebaseAuth.getCurrentUser().getUid()).child("premium").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        isPremium = dataSnapshot.getValue(Boolean.class);
                        if(isPremium) {
                            startActivity(new Intent(getApplicationContext(), AddBookActivity.class));
                        }
                        else{
                            if(books.size() < 5){
                                startActivity(new Intent(getApplicationContext(), AddBookActivity.class));
                            }
                            else{
                                Toast.makeText(BookListActivity.this, "You are not allowed to add more than 5 books since you are not a premium user",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("EXTRA_ID", books.get(i).getId());
        intent.putExtra("EXTRA_TITLE", books.get(i).getTitle());
        intent.putExtra("EXTRA_AUTHOR", books.get(i).getAuthor());
        intent.putExtra("EXTRA_ISBN", books.get(i).getISBN());
        intent.putExtra("EXTRA_NR_PAGES", String.valueOf(books.get(i).getNrPages()));
        intent.putExtra("EXTRA_END_DATE", books.get(i).getEndDate());
        startActivity(intent);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot bookSnapshot : dataSnapshot.getChildren()){
            Book book = bookSnapshot.getValue(Book.class);
            if(bookDao.findOne(book.getISBN()) == null) {
                bookDao.save(book);
            }
            else{
                bookDao.update(book);
            }
        }
        books.clear();
        books.addAll(bookDao.findAll());
        ba.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

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
