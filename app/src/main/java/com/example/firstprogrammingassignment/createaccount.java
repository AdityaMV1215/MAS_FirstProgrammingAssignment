package com.example.firstprogrammingassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class createaccount extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        mAuth = FirebaseAuth.getInstance();
        final Button create_b = (Button) findViewById(R.id.createaccount);
        create_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.email);
                EditText password = (EditText) findViewById(R.id.password);
                final String edit_email = email.getText().toString();
                final String edit_password = password.getText().toString();
                if(!edit_email.isEmpty() && !edit_password.isEmpty()){
                    create(edit_email, edit_password);
                }
                else{
                    Toast.makeText(createaccount.this, "Either email or password filed is blank!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void create(String email, String password){
        final Intent login = new Intent(this, MainActivity.class);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(createaccount.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(login);
                }
                else{
                    Toast.makeText(createaccount.this, "User creation failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
