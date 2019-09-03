package com.example.firstprogrammingassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class viewaverage extends AppCompatActivity{
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewscore);
        mAuth = FirebaseAuth.getInstance();
        String emailID = mAuth.getCurrentUser().getEmail().toString();
        String userID = returnUsername(emailID);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long avgscore = calculateAverage((Map<String, Object>) dataSnapshot.getValue());
                TextView ps = (TextView) findViewById(R.id.previousscore);
                ps.setText("The average score is "+avgscore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public String returnUsername(String email){
        return email.substring(0, email.indexOf("@")).replaceAll("[. &#/*%$!)(^{}\\\\\\[\\]]","_");
    }

    public Long calculateAverage(Map<String, Object> users){
        Long c = new Long(0);
        Long s = new Long( 0);
        for(Map.Entry<String, Object> entry : users.entrySet()){
            Map singleuser = (Map) entry.getValue();
            if((Long) singleuser.get("score") != -1){
                s = s + (Long) singleuser.get("score");
                c += 1;
            }

        }
        return s / c;
    }
}
