package com.example.firstprogrammingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class quiz extends AppCompatActivity {
    int c = 1, score = 0;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView q = (TextView) findViewById(R.id.question);
        final Intent fs = new Intent(this, finalscore.class);

        final HashMap<String, String> question = new HashMap<>();
        question.put("Q1", "What is 2 + 2?");
        question.put("a1", "4");

        question.put("Q2", "What is 100 - 23?");
        question.put("a2", "77");

        question.put("Q3", "What is 34 * 5?");
        question.put("a3", "170");

        question.put("Q4", "What is 120 / 6?");
        question.put("a4", "20");

        question.put("Q5", "What is 12 + 23 * 32 / (13 + 3)?");
        question.put("a5", "58");

        q.setText(question.get("Q1"));

        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ans = (EditText) findViewById(R.id.answer);
                String ansc = "a"+c;
                if(ans.getText().toString().equals(question.get(ansc))){
                    score += 1;
                }
                c += 1;
                if(c == 6){
                    mAuth = FirebaseAuth.getInstance();
                    String emailID = mAuth.getCurrentUser().getEmail().toString();
                    String userID = returnUsername(emailID);
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Users").child(userID).child("score").setValue(score);
                    fs.putExtra("score", score);
                    startActivity(fs);
                }
                String ques = "Q"+c;
                TextView q = (TextView) findViewById(R.id.question);
                q.setText(question.get(ques));
                ans.setText("");
            }
        });


    }

    public String returnUsername(String email){
        return email.substring(0, email.indexOf("@")).replaceAll("[. &#/*%$!)(^{}\\\\\\[\\]]","_");
    }
}
