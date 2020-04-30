package com.example.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
   EditText txt_fullname, txt_email, txt_mobilenumber, txt_repassword, txt_password;
   Button btn_register;
   RadioButton radioJobClient, radioJobPT;
   DatabaseReference databaseReference;
   FirebaseDatabase firebaseDatabase;
   String job ="";
   FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_fullname = (EditText) findViewById(R.id.editText_fullname);
        txt_email = (EditText) findViewById(R.id.editText_UserEmail);
        txt_password = (EditText) findViewById(R.id.editText_Password);
        txt_repassword = (EditText) findViewById(R.id.editText_rePassword);
        txt_mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        btn_register = (Button) findViewById(R.id.button_reg);
        radioJobClient = (RadioButton) findViewById(R.id.radio_Client);
        radioJobPT = (RadioButton) findViewById(R.id.radio_PersonalTrainer);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
       // new code

        firebaseAuth = FirebaseAuth.getInstance();
        //saving user to realtime firebase database
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String  fullname = txt_fullname.getText().toString();
                final String  email = txt_email.getText().toString();
                final String  mobilenumber = txt_mobilenumber.getText().toString();
                final String  password = txt_password.getText().toString();
                final String rePassword = txt_repassword.getText().toString();
                if (radioJobClient.isChecked()){
                     job = "Client";
                }
                if (radioJobPT.isChecked()){
                    job = "PT";

                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(fullname)){
                    Toast.makeText(RegisterActivity.this, "Please enter fullname", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(password)){ Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    User user = new User(
                                            fullname,
                                            email,
                                            mobilenumber,
                                            password,
                                            job
                                    );



                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                            
                                        }
                                    });

                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });




            }
        });


    }



    //to go back to login activity
    public void goLogin (View view){
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

}





// mDatabase = FirebaseDatabase.getInstance().getReference("users");



   //code that works below
    /*
    public void basicWrite(View view){
        String fulllname = ((EditText) findViewById(R.id.editText_fullname)).getText().toString();
        String email = ((EditText) findViewById(R.id.editText_UserEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();
        String mobileNumber = ((EditText) findViewById(R.id.mobilenumber)).getText().toString();

        mDatabase.child("fullname").setValue(fulllname);
        mDatabase.child("email").setValue(email);
        mDatabase.child("password").setValue(password);
        mDatabase.child("mobilenumber").setValue(mobileNumber);

    }
 */

  // code that works
    /*
    public void registerUser(View view){
        String email = ((EditText) findViewById(R.id.editText_UserEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }else{
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(RegisterActivity.this, "Register Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
*/


   // my code
  /* public class RegisterActivity extends AppCompatActivity {
       private FirebaseAuth firebaseAuth;
       public RadioGroup radioGroup;
       private static TextView signin;
       private static View view;
       private static EditText fullname, email, password, Username, reEnterpassword;
       private DatabaseReference mDatabase;
       private DatabaseReference databaseReference;
       private RadioButton Client, PersonalTrainer;
       private FirebaseUser currentUser;
       private static Button button_reg;
       private String firebaseUserID;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_register);


           databaseReference = FirebaseDatabase.getInstance().getReference();
           firebaseAuth = FirebaseAuth.getInstance();
           mDatabase = FirebaseDatabase.getInstance().getReference("User");
       }

       public void registerUser(View view) {
           String fullname = ((EditText) findViewById(R.id.editText_fullname)).getText().toString();
           String email = ((EditText) findViewById(R.id.editText_UserEmail)).getText().toString();
           String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();
           radioGroup = (RadioGroup) findViewById(R.id.radiogroup_Client_PT);
           Client = (RadioButton) view.findViewById(R.id.radiobutton_Client);
           PersonalTrainer = (RadioButton) view.findViewById(R.id.radiobutton_PersonalTrainer);
           String Renterpassword = ((EditText) findViewById(R.id.editText_rePassword)).getText().toString();

           mDatabase.child("fullname").setValue(fullname);
           mDatabase.child("Email").setValue(email);
           mDatabase.child("Password").setValue(password);
           mDatabase.child("UserType").setValue(radioGroup);


           if (TextUtils.isEmpty(email)) {
               Toast.makeText(this, "PLease enter email", Toast.LENGTH_LONG).show();
               return;
           }
           if (TextUtils.isEmpty(password)) {
               Toast.makeText(this, "PLease enter password", Toast.LENGTH_LONG).show();
               return;
           }
           if (radioGroup.getCheckedRadioButtonId() == -1) {
               //no buttons are selected
               Toast.makeText(this, "Please select Client or Persoanl Trainer ", Toast.LENGTH_SHORT).show();
               return;
           }


           //creating a new user
           firebaseAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           //checking of successful
                           if (task.isSuccessful()) {
                               Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                               finish();
                           } else {
                               //display some message here
                               FirebaseAuthException e = (FirebaseAuthException) task.getException();
                               Toast.makeText(RegisterActivity.this, "Failed to Register" + e.getMessage(), Toast.LENGTH_LONG).show();
                           }
                       }
                   });
       }
   }
   */














   // start of code i wasnt using
    //private void saveUserInformation(){
      //  String fullname = ((EditText) findViewById(R.id.editText_fullname)).getText().toString();
        //String email = ((EditText) findViewById(R.id.editText_UserEmail)).getText().toString();
       // String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();
       // String usertype = new String();

       // currentUser = firebaseAuth.getCurrentUser();
       // firebaseUserID = currentUser.getUid();

       // if(Client.isChecked()){

         //   usertype = Client.getText().toString();
       // } else if (PersonalTrainer.isChecked()){
         //   usertype = PersonalTrainer.getText().toString();
       // }

       // User userInformation = new Userinformation();
       // userInformation.setfullname(fullname);
        //userInformation.setemail(email);
        //userInformation.setusertype(usertype);
        //userInformation.setFirebaseUserID(firebaseUserID);

        //databaseReference.child("User").child(firebaseUserID).setValue(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
          //  @Override
           // public void onComplete(@NonNull Task<Void> task) {
            //  if (task.isSuccessful()){
              //    firebaseAuth.signOut();
                //  startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                  //finish();
              //}
            //}
        //});


    //}


//}
