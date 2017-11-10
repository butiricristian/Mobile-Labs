package com.example.crist.mobileandroid.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crist.mobileandroid.BookListActivity;
import com.example.crist.mobileandroid.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.emailText = (EditText) findViewById(R.id.emailText);
        this.passwordText = (EditText) findViewById(R.id.passwordText);
        this.loginButton = (Button)findViewById(R.id.loginButton);
        this.loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"butiri.cristian@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Auto generated e-mail");
        intent.putExtra(Intent.EXTRA_TEXT   , "Hello " + this.emailText.getText() + " and welcome to the Booky app");
        intent.setType("message/rfc822");

        Intent i = new Intent(this, BookListActivity.class);
        startActivity(i);

        try {
            startActivity(Intent.createChooser(intent, "Choose"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
