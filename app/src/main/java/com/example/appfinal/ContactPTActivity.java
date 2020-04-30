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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactPTActivity extends AppCompatActivity {
    Button det;
    TextView one, two, three;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_pt);

        //declaring the variables
         one = (TextView) findViewById(R.id.name1);
         two = (TextView) findViewById(R.id.email1);
         three = (TextView) findViewById(R.id.mobile1);
         det = (Button) findViewById(R.id.details);

         //button to pull down information from the database
         det.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 databaseReference = FirebaseDatabase.getInstance().getReference("User").child("icQUh9ywUkQguPUAEkRITUuLLDJ3");
                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         String name = dataSnapshot.child("fullname").getValue(String.class);
                         String email = dataSnapshot.child("email").getValue(String.class);
                         String mobile = dataSnapshot.child("MobileNumber").getValue(String.class);
                         one.setText(name);
                         two.setText(email);
                         three.setText(mobile);
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
        getMenuInflater().inflate(R.menu.menuitem1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logg){
            startActivity(new Intent(ContactPTActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.Not){
            startActivity(new Intent(ContactPTActivity.this, NoticeActivity.class));
        }else if (item.getItemId()==R.id.PWS){
            startActivity(new Intent(ContactPTActivity.this, SurveyActivity.class));
        } else if (item.getItemId()==R.id.work){
            startActivity(new Intent(ContactPTActivity.this, WorkoutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
