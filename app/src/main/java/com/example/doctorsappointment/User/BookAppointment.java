package com.example.doctorsappointment.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.doctorsappointment.MainActivity;
import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BookAppointment extends AppCompatActivity {

    FirebaseFirestore db;
    public static String specialization;
    public static String hospital;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Intent intent=getIntent();
        specialization=intent.getStringExtra("Specialization");
        hospital=intent.getStringExtra("Hospital");
        gridView=(GridView)findViewById(R.id.gridView);
        setTitle(hospital);
        db=FirebaseFirestore.getInstance();
        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(),specialization,hospital);
        gridView.setAdapter(gridAdapter);

        /*db.collection(specialization).document(hospital).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    ArrayList<Integer> list =null;
                    if (document.exists() && document.contains("Appointments")) {
                        list=(ArrayList<Integer>) document.get("Appointments");
                        if(list!=null)
                            Log.d("list",list.toString());
                        else
                            Log.d("list",null+" ");
                        GridAdapter gridAdapter = new GridAdapter(getApplicationContext(),specialization,hospital,list);
                        gridView.setAdapter(gridAdapter);
                    }
                }
            }
        });*/







    }
}