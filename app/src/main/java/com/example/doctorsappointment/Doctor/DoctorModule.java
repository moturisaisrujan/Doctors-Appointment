package com.example.doctorsappointment.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.R;
import com.example.doctorsappointment.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DoctorModule extends AppCompatActivity {

    public static String person;
    public static String speciality;
    private static String email;


    public String hospital;
    public long current=Long.MIN_VALUE;

    TextView tv1,tv2,tv3,tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_module);

        Intent intent=getIntent();
        person=intent.getStringExtra("person");
        speciality=intent.getStringExtra("speciality");
        email=intent.getStringExtra("email");
        setTitle(person);

        tv1=findViewById(R.id.name);
        tv2=findViewById(R.id.hospital);
        tv3=findViewById(R.id.location);
        tv4=findViewById(R.id.phone);

       FirebaseFirestore.getInstance().collection(speciality).whereEqualTo("email",email)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot doc:task.getResult())
                    {
                        Map<String,Object> hs=doc.getData();
                        Log.d("DATA: ",hs.toString());
                        try {
                            tv1.setText("NAME: "+(String)hs.get("name"));
                            tv2.setText("HOSPITAL: "+(String)hs.get("hospital"));
                            tv3.setText("LOCATION: "+(String)hs.get("location"));
                            tv4.setText("PHONE: "+(String)hs.get("phone"));
                            current=(Long)hs.get("current");
                            hospital=(String)hs.get("hospital");
                        }
                        catch (Exception e)
                        {
                            Log.d("Exception: ",e.getMessage());
                        }
                    }
                }
            }
        });








    }

    public void start(View v)
    {
        Intent intent=new Intent(this,CurrentToken.class);
        intent.putExtra("current", current);
        intent.putExtra("specialization",speciality);
        intent.putExtra("hospital",hospital);
        startActivity(intent);
    }



}