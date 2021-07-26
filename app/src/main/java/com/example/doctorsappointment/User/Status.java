package com.example.doctorsappointment.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Status extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    public static String specialization;
    public static String hospital;
    public static String token;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);

        Intent intent=getIntent();
        specialization=intent.getStringExtra("Specialization");
        hospital=intent.getStringExtra("Hospital");
        token=intent.getStringExtra("Token");
        db=FirebaseFirestore.getInstance();

        tv1.setText("Specialization: "+specialization);
        tv2.setText("Hospital: "+hospital);
        tv3.setText("Token No: "+token);


    }

    public void confirm(View v)
    {
        DocumentReference reference=FirebaseFirestore.getInstance().collection(specialization).document(hospital);
        reference.update("Appointments", FieldValue.arrayUnion(token));
        HashMap<String,Object> hs=new HashMap<>();
        hs.put("Token",Long.parseLong(token));
        hs.put("Hospital",hospital);
        hs.put("Specialization",specialization);
        FirebaseFirestore.getInstance().collection(FirebaseAuth.getInstance().getUid()).document(hospital).set(hs);
        finish();
        Toast.makeText(getApplicationContext(), "Booked Successfully!", Toast.LENGTH_SHORT).show();
    }
}

/*
 db.collection(specialization).document(hospital).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DocumentSnapshot> task) {
                 if(task.isSuccessful())
                 {
                     DocumentSnapshot document = task.getResult();
                     if (document.exists()) {
                         ArrayList<Integer> list = (ArrayList<Integer>) document.get("Appointments");
                         Log.d("IMP", list.toString()+""+list.contains(1));
                     }
                 }
            }
        });
 */