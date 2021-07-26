package com.example.doctorsappointment.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.Doctor.Doctor;
import com.example.doctorsappointment.LoginActivity;
import com.example.doctorsappointment.R;
import com.example.doctorsappointment.SignUpActivity;
import com.example.doctorsappointment.User.User;
import com.example.doctorsappointment.User.UserModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddDoctor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button[] buttons=new Button[4];
    int[] btn_id = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4};
    Button btn_unfocus;
    String Speciality;
    private String spec;
    private int exp;
    private String name;
    private String email;
    private String password;
    private String hospital;
    private String location;
    private String phone;
    private String fees;
    private FirebaseAuth mAuth;
    private FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        setTitle("Add Doctor");

        //Spinner Item Selection
        Spinner speciality=(Spinner)findViewById(R.id.specialities);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.specialities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciality.setAdapter(adapter);
        speciality.setOnItemSelectedListener(this);

        //Buttons Selection
        for(int i = 0; i <buttons.length; i++){
            buttons[i] = (Button) findViewById(btn_id[i]);
            buttons[i].setOnClickListener(this);
        }
        btn_unfocus=buttons[0];

        //Getting Details
        mAuth=FirebaseAuth.getInstance();
        database= FirebaseFirestore.getInstance();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                setFocus(btn_unfocus, buttons[0]);
                break;
            case R.id.btn2:
                setFocus(btn_unfocus, buttons[1]);
                break;

            case R.id.btn3:
                setFocus(btn_unfocus, buttons[2]);
                break;

            case R.id.btn4:
                setFocus(btn_unfocus, buttons[3]);
                break;
        }
    }

        public void setFocus(Button btn_unfocus,Button btn_focus)
        {
            btn_unfocus.setTextColor(Color.rgb(0, 0, 0));
            btn_unfocus.setBackgroundColor(Color.rgb(255, 255, 255));
            btn_focus.setTextColor(Color.rgb(255, 255, 255));
            btn_focus.setBackgroundColor(Color.rgb(254, 44, 84));
            this.btn_unfocus = btn_focus;
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Speciality=((TextView) view).getText().toString();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Add(View v)
    {

        //Getting Details
        spec=Speciality;
        if(btn_unfocus.getId()==R.id.btn1) exp=5;
        else if(btn_unfocus.getId()==R.id.btn2) exp=10;
        else if(btn_unfocus.getId()==R.id.btn3) exp=15;
        else exp=16;

        name=((EditText)findViewById(R.id.name)).getText().toString();
        email=((EditText)findViewById(R.id.email)).getText().toString();
        password=((EditText)findViewById(R.id.password)).getText().toString();
        hospital=((EditText)findViewById(R.id.hospital)).getText().toString();
        location=((EditText)findViewById(R.id.location)).getText().toString();
        phone=((EditText)findViewById(R.id.number)).getText().toString();
        fees=((EditText)findViewById(R.id.fees)).getText().toString();



        final ProgressDialog progressDialog=new ProgressDialog(AddDoctor.this);
        progressDialog.setTitle("Adding Details");
        progressDialog.setMessage("Adding...");

        if (this.email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter password...", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Doctor newdoctor=new Doctor(spec,exp,name,email,password,hospital,location,phone,fees,0l);
                            String id=task.getResult().getUser().getUid();
                            database.collection(spec).document(hospital).set(newdoctor);
                            Toast.makeText(getApplicationContext(), "Doctor Added SuccessFully!",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Doctor Not Added",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}

