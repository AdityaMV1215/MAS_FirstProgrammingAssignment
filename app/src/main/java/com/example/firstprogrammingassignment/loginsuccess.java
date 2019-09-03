package com.example.firstprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

public class loginsuccess extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);
        mAuth = FirebaseAuth.getInstance();
        final Intent quiz = new Intent(this, com.example.firstprogrammingassignment.quiz.class);
        final Intent views = new Intent(this, viewscore.class);
        String user = mAuth.getCurrentUser().getEmail().toString();
        TextView welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome "+user);

        Button takequiz = (Button) findViewById(R.id.takequiz);
        takequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(quiz);
            }
        });

        Button viewscore = (Button) findViewById(R.id.checkscore);
        viewscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(views);
            }
        });

        Button viewaverage = (Button) findViewById(R.id.averagescore);
        viewaverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(views);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent logout = new Intent(this, MainActivity.class);
        mAuth = FirebaseAuth.getInstance();
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            startActivity(logout);
        }
        return super.onKeyDown(keyCode, event);
    }
}
