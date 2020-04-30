package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgottenPassword extends AppCompatActivity {
    EditText email;
    Button sendemail;

    FirebaseAuth firebaseAuth;

// code taken from assessment from mobile technolgies module in semester 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        email = findViewById(R.id.editText_user_email);
        sendemail = findViewById(R.id.button_send_email);
        firebaseAuth = FirebaseAuth.getInstance();

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString();

                if (useremail.equals("")){
                    Toast.makeText(ForgottenPassword.this, "Please enter your registered email", Toast.LENGTH_LONG).show();
                } else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgottenPassword.this, "Reset Email sent to User Account", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(ForgottenPassword.this, MainActivity.class));
                            } else {
                                Toast.makeText(ForgottenPassword.this, "Error in sending Password Reset Email", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }


            }
        });

    }
}
