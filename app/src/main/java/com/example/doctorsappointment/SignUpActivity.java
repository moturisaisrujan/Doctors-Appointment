package com.example.doctorsappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctorsappointment.User.User;
import com.example.doctorsappointment.User.UserModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private EditText username;
    private EditText emailid;
    private EditText password;

    public static String person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.username);
        emailid=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

    }


    public void createUser(View v) {
        final ProgressDialog progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");


        final String email=emailid.getText().toString().trim();
        final String pass=password.getText().toString().trim();
        final String user=username.getText().toString().trim();

        if (user.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter Username...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            User newuser=new User(user,email,pass);
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(newuser);
                            Toast.makeText(getApplicationContext(), "Registration successful!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, UserModule.class);
                            intent.putExtra("person",person);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
}