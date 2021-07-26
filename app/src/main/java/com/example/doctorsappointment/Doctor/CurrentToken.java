package com.example.doctorsappointment.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.doctorsappointment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CurrentToken extends AppCompatActivity {

    private long currentToken;
    TextView token;
    private String speciality;
    private String hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_token);

        Intent intent=getIntent();
        currentToken=intent.getLongExtra("current",Integer.MIN_VALUE);
        speciality=intent.getStringExtra("specialization");
        hospital=intent.getStringExtra("hospital");
        token=findViewById(R.id.token);

        setText();
        token.setText(currentToken+"");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

    public void nextToken(View v)
    {
        currentToken++;
        setText();
        token.setText(currentToken+"");
        DocumentReference ref=FirebaseFirestore.getInstance().collection(speciality).document(hospital);
        ref.update("current",currentToken);
    }

    public void previousToken(View v)
    {
        currentToken--;
        if(currentToken<0)
        {
            currentToken++;
            return;
        }
        setText();
        token.setText(currentToken+"");
        DocumentReference ref=FirebaseFirestore.getInstance().collection(speciality).document(hospital);
        ref.update("current",currentToken);

    }

    public void setText()
    {
        if(currentToken>=100)
            token.setTextSize(TypedValue.COMPLEX_UNIT_SP,150);
        else if(currentToken>=10)
            token.setTextSize(TypedValue.COMPLEX_UNIT_SP,200);
        else
            token.setTextSize(TypedValue.COMPLEX_UNIT_SP,300);
    }


}