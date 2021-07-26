package com.example.doctorsappointment.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doctorsappointment.R;

public class AdminModule extends AppCompatActivity {

    public static String person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_module);

        Intent intent=getIntent();
        person=intent.getStringExtra("person");
        setTitle(person);
    }
    
    public void addDoctor(View v)
    {
        Intent intent=new Intent(getApplicationContext(),AddDoctor.class);
        startActivity(intent);
    }

    public void remove(View v)
    {
        Intent intent=new Intent(getApplicationContext(),Remove.class);
        startActivity(intent);
    }

    public void find(View v)
    {
        Intent intent=new Intent(getApplicationContext(),Find.class);
        startActivity(intent);
    }
}