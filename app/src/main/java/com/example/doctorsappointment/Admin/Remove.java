package com.example.doctorsappointment.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Remove extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    String Speciality;
    private String spec;
    private String hospital;
    private String name;
    DocumentReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        //Spinner Item Selection
        Spinner speciality=(Spinner)findViewById(R.id.specialities);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.specialities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciality.setAdapter(adapter);
        speciality.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Speciality=((TextView) view).getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Remove(View v)
    {
        spec=Speciality;
        name=((EditText)findViewById(R.id.name)).getText().toString();
        hospital=((EditText)findViewById(R.id.hospital)).getText().toString();

         reference= FirebaseFirestore.getInstance().collection(spec).document(hospital);
         reference.delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
             @Override
             public void onComplete(Task<Void> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(getApplicationContext(), "Doctor Removed SuccessFully!",
                               Toast.LENGTH_SHORT).show();
                       finish();
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Doctor Not Removed Try Again!",
                               Toast.LENGTH_SHORT).show();
                   }
             }
         });
    }
}