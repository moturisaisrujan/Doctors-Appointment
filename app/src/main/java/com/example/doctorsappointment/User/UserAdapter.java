package com.example.doctorsappointment.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctorsappointment.R;

import java.util.List;

public class UserAdapter extends BaseAdapter
{
    private Context context;
    List<EachAppointment> allAppointments;

    public UserAdapter(Context context, List<EachAppointment> allAppointments) {
        this.context = context;
        this.allAppointments = allAppointments;
    }

    @Override
    public int getCount() {
        return allAppointments.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.each_appointment, null);

        }

        TextView hospital = (TextView)v.findViewById(R.id.hospital);
        TextView specialization = (TextView)v.findViewById(R.id.specialization);
        Button checkStatus= (Button)v.findViewById(R.id.check_status);

        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, StatusOfAppointment.class);
                intent.putExtra("Token",allAppointments.get(position).token);
                intent.putExtra("Specialization",allAppointments.get(position).specialization);
                intent.putExtra("Hospital",allAppointments.get(position).hospital);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        hospital.setText(allAppointments.get(position).hospital);
        specialization.setText(allAppointments.get(position).specialization);
        return v;
    }
}
