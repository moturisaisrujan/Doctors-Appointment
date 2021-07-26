package com.example.doctorsappointment.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctorsappointment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserModule extends AppCompatActivity implements View.OnClickListener{

    public static String person;
    CardView c1,c2,c3,c4,c5,c6,c7,c8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_module);

        Intent intent=getIntent();
        person=intent.getStringExtra("person");
        setTitle(person);

        c1=(CardView)findViewById(R.id.cd1);
        c2=(CardView)findViewById(R.id.cd2);
        c3=(CardView)findViewById(R.id.cd3);
        c4=(CardView)findViewById(R.id.cd4);
        c5=(CardView)findViewById(R.id.cd5);
        c6=(CardView)findViewById(R.id.cd6);
        c7=(CardView)findViewById(R.id.cd7);
        c8=(CardView)findViewById(R.id.cd8);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);
        c8.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

           Intent i;
           i=new Intent(UserModule.this,Doctors_List.class);
          switch(v.getId()){
                 case R.id.cd1:
                  i.putExtra("Specialization","General Physician");
                  break;
                  case R.id.cd2:
                      i.putExtra("Specialization","Dentist");
                      break;
                  case R.id.cd3:
                      i.putExtra("Specialization","Orthopedic");
                      break;
                  case R.id.cd4:
                      i.putExtra("Specialization","Pediatrician");
                        break;
                  case R.id.cd5:
                      i.putExtra("Specialization","Dermatologist");
                         break;
                  case R.id.cd6:
                      i.putExtra("Specialization","Cardiologist");
                      break;
                  case R.id.cd7:
                      i.putExtra("Specialization","ENT specialist");
                      break;
                  case R.id.cd8:
                      i.putExtra("Specialization","Others");
                      break;
        }

        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();

        if(id==R.id.appointments)
        {
            Intent intent=new Intent(this,UserAppointments.class);
            startActivity(intent);
        }
        else if(id==R.id.SignOut)
        {
           finish();
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}


