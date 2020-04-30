package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkoutActivity extends AppCompatActivity {
    TextView a,b,c,d,e;
    Button workdone;
    DatabaseReference databaseReference;
    //reference: code taken from mobile technology module from semester 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);



        //clients being able to view workouts from PT
        a = (TextView) findViewById(R.id.a);
        b = (TextView) findViewById(R.id.b);
        c = (TextView) findViewById(R.id.c);
        d = (TextView) findViewById(R.id.d);
        e = (TextView) findViewById(R.id.e);
        workdone = (Button) findViewById(R.id.showworkouts);

        workdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              databaseReference = FirebaseDatabase.getInstance().getReference().child("Workouts");
              databaseReference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Exercise1 = dataSnapshot.child("Exercise 1").getValue().toString();
                    String Exercise2 = dataSnapshot.child("Exercise 2").getValue().toString();
                    String Exercise3 = dataSnapshot.child("Exercise 3").getValue().toString();
                    String Exercise4 = dataSnapshot.child("Exercise 4").getValue().toString();
                    String Exercise5 = dataSnapshot.child("Exercise 5").getValue().toString();
                    a.setText(Exercise1);
                    b.setText(Exercise2);
                    c.setText(Exercise3);
                    d.setText(Exercise4);
                    e.setText(Exercise5);

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
            }
        });
    }
    public void goBoard(View view){
        startActivity(new Intent(WorkoutActivity.this, NoticeBoardActivity.class));
        Toast.makeText(WorkoutActivity.this, "Well done on completing todays Workout", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(WorkoutActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.cpt){
            startActivity(new Intent(WorkoutActivity.this, ContactPTActivity.class));
        }else if (item.getItemId()==R.id.PWS){
            startActivity(new Intent(WorkoutActivity.this, SurveyActivity.class));
        } else if (item.getItemId()==R.id.Not){
            startActivity(new Intent(WorkoutActivity.this, NoticeActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
