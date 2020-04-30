package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NoticeBoardActivity extends AppCompatActivity {
    //firebase auth object
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        //intialising firebase auth object
        firebaseAuth =   FirebaseAuth.getInstance();
        // getting current user
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        // intoalaing views
        TextView textViewUserEmail = (TextView) findViewById(R.id.textView_pro_title);
        //displaying logged in user name
        textViewUserEmail.setText("Welcome" + " " + currentUser.getEmail());




    }
    public void goContactPT(View view){
        startActivity(new Intent(NoticeBoardActivity.this, ContactPTActivity.class));
    }
    public void goSurvey(View view){
        startActivity(new Intent(NoticeBoardActivity.this, SurveyActivity.class));
    }
    public void goWorkout(View view){
        startActivity(new Intent(NoticeBoardActivity.this, WorkoutActivity.class));
    }
    public void goNotice (View view){
        startActivity(new Intent(NoticeBoardActivity.this, NoticeActivity.class));
    }

    public void logOut(View view){
        //logging out user
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(NoticeBoardActivity.this, MainActivity.class));
    }
}


//my code
  /*public class NoticeBoardActivity extends AppCompatActivity {
    //firebase auth object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        //intialising firebase auth object
        firebaseAuth =   FirebaseAuth.getInstance();
        // getting current user
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        // intoalaing views
        TextView textViewUserEmail = (TextView) findViewById(R.id.textView_pro_title);
        //displaying logged in user name
        textViewUserEmail.setText("Welcome" + currentUser.getEmail());

    }

    public void logOut(View view){
        //logging out user
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(NoticeBoardActivity.this, MainActivity.class));
    }
} */

