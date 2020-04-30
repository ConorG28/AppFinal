package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactClientActivity extends AppCompatActivity {
    TextView textView;
    AlertDialog dialog;
    EditText editText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_client);


        // code to allow the Personal trainer to add a new client
        textView = (TextView) findViewById(R.id.textview_addclient);
        dialog = new AlertDialog.Builder(this).create();
        editText = new EditText(this);

        dialog.setTitle("Add a new client");
        dialog.setView(editText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Add Client", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(editText.getText());

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(textView.getText());
                dialog.show();
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
            startActivity(new Intent(ContactClientActivity.this, MainActivity.class));
        }else if (item.getItemId()==R.id.edw){
            startActivity(new Intent(ContactClientActivity.this, EditWorkoutsActivity.class));
        }else if (item.getItemId()==R.id.editn){
            startActivity(new Intent(ContactClientActivity.this, EditnoticeActivity.class));
        } else if (item.getItemId()==R.id.sur){
            startActivity(new Intent(ContactClientActivity.this, SurveyResultsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
