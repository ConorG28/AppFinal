package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;

public class SurveyActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);



        databaseReference = FirebaseDatabase.getInstance().getReference("Survey Results");




    }
    //saving to the realtime database
 public void submitsurvey(View view){

       // String ClientName = ((EditText) findViewById(R.id.nameclient)).getText().toString();
        String Date = ((EditText) findViewById(R.id.sdate)).getText().toString();
        SmileyRating.Type OverallSession = ((SmileyRating) findViewById(R.id.smile_rating1)).getSelectedSmiley();
        SmileyRating.Type SessionDifficulty = ((SmileyRating) findViewById(R.id.smile_rating2)).getSelectedSmiley();
        SmileyRating.Type SessionEnjoyment = ((SmileyRating) findViewById(R.id.smile_rating3)).getSelectedSmiley();
        SmileyRating.Type Overallmood = ((SmileyRating) findViewById(R.id.smile_rating5)).getSelectedSmiley();


    // databaseReference.child("Client Name ").setValue(ClientName);
     databaseReference.child("Name and Date").setValue(Date);
     databaseReference.child("OverallSession").setValue(OverallSession);
     databaseReference.child("Session Difficulty").setValue(SessionDifficulty);
     databaseReference.child("Session Enjoyment").setValue(SessionEnjoyment);
     databaseReference.child("Overall mood").setValue(Overallmood);

     Toast.makeText(SurveyActivity.this, "Thank you for submitting the survey", Toast.LENGTH_SHORT).show();

     startActivity(new Intent(SurveyActivity.this, NoticeBoardActivity.class));




 }













    //code for options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logg){
            startActivity(new Intent(SurveyActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.cpt){
            startActivity(new Intent(SurveyActivity.this, ContactPTActivity.class));
        }else if (item.getItemId()==R.id.Not){
            startActivity(new Intent(SurveyActivity.this, NoticeActivity.class));
        } else if (item.getItemId()==R.id.work){
            startActivity(new Intent(SurveyActivity.this, WorkoutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
