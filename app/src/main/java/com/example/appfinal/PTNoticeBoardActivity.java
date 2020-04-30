package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PTNoticeBoardActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
// code taken from mobile technology module from semester 1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptnotice_board);
        //intialising firebase auth object
        firebaseAuth =   FirebaseAuth.getInstance();
        // getting current user
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        // intoalaing views
        TextView textViewUserEmail = (TextView) findViewById(R.id.textView_pro_title);
        //displaying logged in user name
        textViewUserEmail.setText("Welcome" + " " + currentUser.getEmail());

    }
    public void goContactClient (View view){
        startActivity(new Intent(PTNoticeBoardActivity.this, ContactClientActivity.class));
    }
    public void goAddNotice(View view){
        startActivity(new Intent(PTNoticeBoardActivity.this, EditnoticeActivity.class));
    }
    public void goEditWorkouts (View view){
        startActivity(new Intent(PTNoticeBoardActivity.this, EditWorkoutsActivity.class));
    }
    public void goSurveyResults(View view){
        startActivity(new Intent(PTNoticeBoardActivity.this, SurveyResultsActivity.class));
    }
    public void logOut(View view){
        //logging out user
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(PTNoticeBoardActivity.this, MainActivity.class));
    }
}
