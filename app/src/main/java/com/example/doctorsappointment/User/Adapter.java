package com.example.doctorsappointment.User;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorsappointment.Admin.Find;
import com.example.doctorsappointment.Doctor.Doctor;
import com.example.doctorsappointment.LoginActivity;
import com.example.doctorsappointment.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    public String Specialization;
    private Context context;
    List<EachDoctor> allDoctors;

    public Adapter(Context activity, List<EachDoctor> allNotes,String specialization) {
        this.context = activity;
        this.allDoctors = allNotes;
        this.Specialization=specialization;
    }


    @Override
    public int getCount() {
        return allDoctors.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.each_doctor_row, null);

        }

        TextView hospital = (TextView)v.findViewById(R.id.hospital);
        TextView doctor = (TextView)v.findViewById(R.id.doctor);
        Button book= (Button)v.findViewById(R.id.book_appointment);

        book.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(context, BookAppointment.class);
                intent.putExtra("Specialization",Specialization);
                intent.putExtra("Hospital",allDoctors.get(i).hospital);
                Log.d("Inside Adapter:",allDoctors.get(i).hospital);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }

        });

        hospital.setText(allDoctors.get(i).hospital);
        doctor.setText("NAME: "+allDoctors.get(i).doctor+"\nFEES: "+allDoctors.get(i).fee+"rs");

        return v;
    }

}
