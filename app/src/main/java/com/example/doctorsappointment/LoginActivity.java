package com.example.doctorsappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.Admin.AdminModule;
import com.example.doctorsappointment.Doctor.DoctorModule;
import com.example.doctorsappointment.User.UserModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    private EditText emailid;
    private EditText password;

    public static String person;
    private String Speciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Previous activity value
        Intent intent=getIntent();
        person=intent.getStringExtra("person");
        setTitle(person);

        emailid=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
       mAuth=FirebaseAuth.getInstance();
       Spinner speciality=findViewById(R.id.specialities1);
         if(person.equals("Doctor")) {
             speciality.setVisibility(View.VISIBLE);
             //Spinner Selection
             ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                     R.array.specialities, android.R.layout.simple_spinner_item);
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             speciality.setAdapter(adapter);
             speciality.setOnItemSelectedListener(this);
         }

    }
    /*public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(LoginActivity.this, UserModule.class);
            startActivity(intent);
            finish();
        }
    }*/

    public void logIn(View v)
    {
        final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Log In");
        progressDialog.setMessage("Logging In...");

         final String email=emailid.getText().toString().trim();
         String pass=password.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            Intent intent;
                            if(person.equals("User")) {
                                intent = new Intent(LoginActivity.this, UserModule.class);
                            }
                            else if(person.equals("Doctor"))
                            {
                                intent= new Intent(LoginActivity.this, DoctorModule.class);
                                intent.putExtra("speciality",Speciality);
                                intent.putExtra("email",email);
                            }
                            else
                                intent= new Intent(LoginActivity.this, AdminModule.class);

                            intent.putExtra("person",person);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Wrong Email Or Password",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public void signUp(View v)
    {
        if(person.equals("User"))
        {
            Intent intent=new Intent(this,SignUpActivity.class);
            intent.putExtra("person",person);
            startActivity(intent);
        }
        else if(person.equals("Doctor"))
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Sorry Doctors Can't Create Account",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Sorry Admins Can't Create Account",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Speciality=((TextView) view).getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}