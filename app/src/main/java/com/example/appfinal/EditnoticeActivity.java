package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditnoticeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnotice);

        databaseReference = FirebaseDatabase.getInstance().getReference("Notices");
    }

    // allows the personal trainer to upload new notices
    public void Addnewnotice (View view){
        String NewNotice = ((EditText) findViewById(R.id.newnotice)).getText().toString();

        databaseReference.child("New Notice").setValue(NewNotice);

        Toast.makeText(EditnoticeActivity.this, "New Notice has been Added", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(EditnoticeActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.CC){
            startActivity(new Intent(EditnoticeActivity.this, ContactClientActivity.class));
        }else if (item.getItemId()==R.id.edw){
            startActivity(new Intent(EditnoticeActivity.this, EditWorkoutsActivity.class));
        } else if (item.getItemId()==R.id.sur){
            startActivity(new Intent(EditnoticeActivity.this, SurveyResultsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
