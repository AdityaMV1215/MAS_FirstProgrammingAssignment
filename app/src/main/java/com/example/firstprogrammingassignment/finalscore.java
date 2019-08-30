package com.example.firstprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class finalscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalscore);
        Intent fs = getIntent();
        int score = fs.getExtras().getInt("score");
        TextView sc = (TextView) findViewById(R.id.finalscore);
        sc.setText("Your final score is "+score+" / 5");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent logout = new Intent(this, loginsuccess.class);
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(logout);
        }
        return super.onKeyDown(keyCode, event);
    }
}
