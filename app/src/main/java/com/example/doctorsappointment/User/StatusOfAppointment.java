package com.example.doctorsappointment.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class StatusOfAppointment extends AppCompatActivity {

    private long token;
    private String specialization;
    private String hospital;

    TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_appointment);

        Intent intent=getIntent();
        token=intent.getLongExtra("Token",0l);
        specialization=intent.getStringExtra("Specialization");
        hospital=intent.getStringExtra("Hospital");
        tv1=findViewById(R.id.userToken);
        tv2=findViewById(R.id.ApproxTime);
        tv3=findViewById(R.id.token);

        tv1.setText("YOUR TOKEN NO: "+token);
        tv2.setText("APPROXIMATE TIME:\n"+getTime(token));


        FirebaseFirestore.getInstance().collection(specialization).document(hospital).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable  FirebaseFirestoreException error) {
                if (error != null) {
                    Log.d("Exception", "Listen failed.");
                    return;
                }

                if (snapshot != null && snapshot.exists())
                {
                    Long runningToken= (Long) snapshot.getData().get("current");
                    setText(runningToken);
                    tv3.setText(runningToken+"");
                }

            }
        });
    }

    public void setText(long currentToken)
    {
        if(currentToken>=100)
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,150);
        else if(currentToken>=10)
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,200);
        else
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,300);
    }

    public  String getTime(long token)
    {
        long timeInMinutes=token*15;
        long hrs=timeInMinutes/60;
        long min=timeInMinutes%60;
        String pm_am;
        if(hrs>3)
        {
            hrs=hrs-3;
            pm_am=" PM";
        }
        else
        {
            hrs=hrs+9;
            pm_am=" AM";
        }
        String HRS=String.valueOf(hrs);
        String MIN=String.valueOf(min);

        if(HRS.length()==1)
            HRS="0"+HRS;
        if(MIN.length()==1)
            MIN="0"+MIN;
        return HRS+":"+MIN+pm_am;


    }
}