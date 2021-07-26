package com.example.doctorsappointment.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAppointments extends AppCompatActivity {

    private ListView listView;
    List<EachAppointment> allAppointments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointments);

        allAppointments=new ArrayList<>();

        listView = (ListView) findViewById(R.id.list_view);
        Log.d("Here","Reached");
        getAllAppointments();

    }

    public void getAllAppointments()
    {
        String UID=FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection(UID).get().addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        Map<String, Object> map = doc.getData();
                        EachAppointment eachAppointment = new EachAppointment((Long) map.get("Token"),(String)map.get("Hospital"),(String)map.get("Specialization"));
                        allAppointments.add(eachAppointment);
                        UserAdapter userAdapter = new UserAdapter(getApplicationContext(), allAppointments);
                        listView.setAdapter(userAdapter);
                    }
                }
            }
        });
    }
}

class EachAppointment
{
    long token;
    String hospital;
    String specialization;
    EachAppointment(long token, String hospital,String specialization)
    {
        this.token=token;
        this.hospital=hospital;
        this.specialization=specialization;
    }
}