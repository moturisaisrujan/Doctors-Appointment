package com.example.doctorsappointment.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doctorsappointment.Doctor.Doctor;
import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Doctors_List extends AppCompatActivity {

    public static String Specialization;
    private ListView listView;
    List<EachDoctor> allDoctors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        allDoctors=new ArrayList<>();
        Intent intent=getIntent();
        Specialization=intent.getStringExtra("Specialization");

        setTitle(Specialization);


        listView = (ListView) findViewById(R.id.list_view);
        Log.d("Here","Reached");
        getAllDoctors();
    }

    /*protected void onResume() {
        super.onResume();
         getAllDoctors();
    }*/


     public void getAllDoctors()
     {
         FirebaseFirestore.getInstance().collection(Specialization).get().addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(Task<QuerySnapshot> task) {
                 if(task.isSuccessful()) {
                     for (QueryDocumentSnapshot doc : task.getResult()) {
                         Map<String, Object> map = doc.getData();
                         EachDoctor eachdoc = new EachDoctor((String) map.get("hospital"), (String) map.get("name"),(String)map.get("fees"));
                         allDoctors.add(eachdoc);
                         Adapter adapter = new Adapter(getApplicationContext(), allDoctors,Specialization);
                         listView.setAdapter(adapter);
                     }
                 }
                 else
                   Log.d("Here","Unsuccessfull");
             }
         });
     }


}
class EachDoctor
{
    String hospital;
    String doctor;
    String fee;

    EachDoctor(String hospital,String doctor,String fees)
    {
        this.hospital=hospital;
        this.doctor=doctor;
        this.fee=fees;
    }
}