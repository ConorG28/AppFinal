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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smileyrating.SmileyRating;

public class SurveyResultsActivity extends AppCompatActivity {
    Button btn;
    DatabaseReference databaseReference;
    TextView clientclient,date, R1,R2,R3,R4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_results);

        //Pt viewing the results from clients
        // reference: code taken from mobile technology from semester 1

     // clientclient = (TextView) findViewById(R.id.clientclient);
      date = (TextView) findViewById(R.id.date);
      R1 = (TextView) findViewById(R.id.R1);
      R2 = (TextView) findViewById(R.id.R2);
      R3 = (TextView) findViewById(R.id.R3);
      R4 = (TextView) findViewById(R.id.R4);
      btn = (Button)  findViewById(R.id.showresults);

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              databaseReference = FirebaseDatabase.getInstance().getReference("Survey Results");
              databaseReference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //  String ClientName = dataSnapshot.child("Client Name").getValue(String.class);
                      String Date = dataSnapshot.child("Name and Date").getValue(String.class);
                      String overallmood = dataSnapshot.child("Overall mood").getValue(String.class);
                      String overallsession = dataSnapshot.child("OverallSession").getValue(String.class);
                      String SessionD = dataSnapshot.child("Session Difficulty").getValue(String.class);
                      String sessione = dataSnapshot.child("Session Enjoyment").getValue(String.class);
                    //  clientclient.setText(ClientName);
                      date.setText(Date);
                      R1.setText(overallmood);
                      R2.setText(overallsession);
                      R3.setText(SessionD);
                      R4.setText(sessione);
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
          }
      });





    }
    //code for options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.loout){
            startActivity(new Intent(SurveyResultsActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.CC){
            startActivity(new Intent(SurveyResultsActivity.this, ContactClientActivity.class));
        }else if (item.getItemId()==R.id.editn){
            startActivity(new Intent(SurveyResultsActivity.this, EditnoticeActivity.class));
        } else if (item.getItemId()==R.id.edw){
            startActivity(new Intent(SurveyResultsActivity.this, EditWorkoutsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }





}
