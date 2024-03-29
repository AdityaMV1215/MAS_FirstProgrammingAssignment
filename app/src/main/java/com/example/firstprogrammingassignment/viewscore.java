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

public class viewscore extends AppCompatActivity {
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
        mDatabase.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User usr = dataSnapshot.getValue(User.class);
                if(usr.score == -1){
                    TextView ps = (TextView) findViewById(R.id.previousscore);
                    ps.setText("You haven't given the Quiz yet, please submit the Quiz atleast once to see your previous score!");
                }
                else{
                    TextView ps = (TextView) findViewById(R.id.previousscore);
                    ps.setText("Your previous score is "+usr.score+" / 5");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public String returnUsername(String email){
        return email.substring(0, email.indexOf("@")).replaceAll("[. &#/*%$!)(^{}\\\\\\[\\]]","_");
    }
}
