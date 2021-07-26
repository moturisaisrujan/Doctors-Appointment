package com.example.doctorsappointment.User;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.doctorsappointment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Iterator;

public class GridAdapter extends BaseAdapter {

    Context context;
    int[] tokens={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
    LayoutInflater inflater;
    public static String specialization;
    public static String hospital;
    private FirebaseFirestore db;


    public GridAdapter(Context context,String specialization,String hospital) {
        this.context = context;
        this.specialization=specialization;
        this.hospital=hospital;

    }

    @Override
    public int getCount() {
        return tokens.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(inflater==null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.each_grid_button,null);
        }

        Button button=convertView.findViewById(R.id.token);
        button.setText(tokens[position]+"");

        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    Intent intent = new Intent(context, Status.class);
                    intent.putExtra("Specialization", specialization);
                    intent.putExtra("Hospital", hospital);
                    intent.putExtra("Token", b.getText());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        return convertView;
    }

    public void set(Button button)
    {
        button.setEnabled(false);
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (100 * scale + 0.5f);
        button.setHeight(pixels);
        button.setWidth(pixels);
        button.setBackgroundColor(Color.rgb(178,190,181));
    }

    public void bookedAlready(final int seat)
    {

        Log.d("token",seat+"");
        db.collection(specialization).document(hospital).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists() && document.contains("Appointments")) {
                        ArrayList<Integer> list = (ArrayList<Integer>) document.get("Appointments");
                        Log.d("token1",false+"");
                        if(list.contains(seat))
                        {
                            Log.d("token2",true+"");
                        }
                    }
                }
            }
        });

    }
}
