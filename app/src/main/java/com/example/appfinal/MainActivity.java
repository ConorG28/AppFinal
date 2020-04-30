package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ClientInfoStatus;

public class MainActivity extends AppCompatActivity {
    //code taken from mobile technology module in semester 1
    RadioButton accounttype_client, accounttype_PT;
    private DatabaseReference databaseReference;



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        //new code
        accounttype_client = (RadioButton) findViewById(R.id.client_account);
        accounttype_PT = (RadioButton) findViewById(R.id.PersonalTrainer_account);




    }
    public void loginUser(View View){
        String email = ((EditText) findViewById(R.id.editText_Log_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText_Log_password)).getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return;

        }



        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() || accounttype_client.isChecked()){
                    //new code below
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), NoticeBoardActivity.class));
                    finish();
                } if ( accounttype_PT.isChecked()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, PTNoticeBoardActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });



        // new code below



    }
    // new code below , logging in pt or client





    public void goForgottenPassword(View view){
        startActivity(new Intent(MainActivity.this, ForgottenPassword.class));
    }

    public void goRegister(View view){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}


//my code

 /*public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void loginUser(View view){
        String email = ((EditText) findViewById(R.id.editText_Log_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText_Log_password)).getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), NoticeBoardActivity.class));
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void goForgottenPassword(View view){
        startActivity(new Intent(MainActivity.this, ForgottenPassword.class));
    }



    public void goRegister(View view){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
} */