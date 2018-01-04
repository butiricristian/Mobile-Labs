package com.example.crist.mobileandroid.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crist.mobileandroid.R;
import com.example.crist.mobileandroid.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button saveBtn;
    EditText nameText, emailText, passText;
    CheckBox isPremium;
    FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.signUpNameText);
        emailText = (EditText) findViewById(R.id.signUpEmailText);
        passText = (EditText) findViewById(R.id.signUpPasswordText);
        saveBtn = (Button) findViewById(R.id.signUpButton);
        isPremium = (CheckBox) findViewById(R.id.checkBox);
        firebaseAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("users");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passText.getText().toString())
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    dbRef.child(firebaseAuth.getCurrentUser().getUid()).setValue(new User(
                                            nameText.getText().toString(),
                                            emailText.getText().toString(),
                                            passText.getText().toString(),
                                            isPremium.isChecked()
                                    ));
                                    Intent i = new Intent(SignUp.this, MainActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(SignUp.this, "Registration failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}
