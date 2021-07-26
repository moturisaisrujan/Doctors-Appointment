package com.example.doctorsappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth=FirebaseAuth.getInstance();


    }

    public void login(View v)
    {
        Intent intent=new Intent(this,LoginActivity.class);
        if(v.getId()==R.id.user)
        {
           intent.putExtra("person","User");
        }
        else if(v.getId()==R.id.doctor)
        {
            intent.putExtra("person","Doctor");
        }
        else if(v.getId()==R.id.admin)
        {
            intent.putExtra("person","Admin");
        }
        startActivity(intent);
    }


}