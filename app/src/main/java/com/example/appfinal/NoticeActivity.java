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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NoticeActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView abc;
    Button ViewNotice;
    // code taken from mobile technology module in semester1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        //client viewing notice from PT
        abc = (TextView) findViewById(R.id.abc);
        ViewNotice = (Button) findViewById(R.id.viewnotice);

        ViewNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Notices");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String NewNotice = dataSnapshot.child("New Notice").getValue().toString();
                        abc.setText(NewNotice);
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
            startActivity(new Intent(NoticeActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.cpt){
            startActivity(new Intent(NoticeActivity.this, ContactPTActivity.class));
        }else if (item.getItemId()==R.id.PWS){
            startActivity(new Intent(NoticeActivity.this, SurveyActivity.class));
        } else if (item.getItemId()==R.id.work){
            startActivity(new Intent(NoticeActivity.this, WorkoutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
