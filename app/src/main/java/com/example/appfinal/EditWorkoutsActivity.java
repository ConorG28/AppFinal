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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditWorkoutsActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workouts);






        databaseReference = FirebaseDatabase.getInstance().getReference("Workouts");

    }

    // Personal trainer submits workout to the database
    // code taken from mobile technology module in semester 1
    public void submitworkout(View view){
        String exercise1 = ((EditText) findViewById(R.id.edit1)).getText().toString();
        String exercise2 = ((EditText) findViewById(R.id.edit2)).getText().toString();
        String exercise3 = ((EditText) findViewById(R.id.edit3)).getText().toString();
        String exercise4 = ((EditText) findViewById(R.id.edit4)).getText().toString();
        String exercise5 = ((EditText) findViewById(R.id.edit5)).getText().toString();

        databaseReference.child("Exercise 1").setValue(exercise1);
        databaseReference.child("Exercise 2").setValue(exercise2);
        databaseReference.child("Exercise 3").setValue(exercise3);
        databaseReference.child("Exercise 4").setValue(exercise4);
        databaseReference.child("Exercise 5").setValue(exercise5);

        Toast.makeText(EditWorkoutsActivity.this, "Workouts Uploaded", Toast.LENGTH_SHORT).show();


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
          startActivity(new Intent(EditWorkoutsActivity.this, MainActivity.class));
      }else if (item.getItemId()==R.id.CC){
          startActivity(new Intent(EditWorkoutsActivity.this, ContactClientActivity.class));
      }else if (item.getItemId()==R.id.editn){
          startActivity(new Intent(EditWorkoutsActivity.this, EditnoticeActivity.class));
      } else if (item.getItemId()==R.id.sur){
          startActivity(new Intent(EditWorkoutsActivity.this, SurveyResultsActivity.class));
      }

        return super.onOptionsItemSelected(item);
    }
    //logging out user
 //   public void SignOut(View view){
        //logging out user
   //     firebaseAuth.signOut();
        //closing activity
    //  finish();
        //starting login activity
     // startActivity(new Intent(EditWorkoutsActivity.this, MainActivity.class));
   // }
}
