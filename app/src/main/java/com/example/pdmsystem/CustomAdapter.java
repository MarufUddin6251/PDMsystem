package com.example.pdmsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Upload2> {

    private Activity context;
    private List<Upload2>tutionAdList;

    public CustomAdapter(Activity context, List<Upload2> tutionAdList) {
        super(context, R.layout.sample_layout2,tutionAdList);
        this.context = context;
        this.tutionAdList = tutionAdList;//may cause problem


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view=  layoutInflater.inflate(R.layout.sample_layout2,null,true);
        Upload2 upload2 = tutionAdList.get(position);
        TextView t1 = view.findViewById(R.id.nameTextId);
        TextView t2 = view.findViewById(R.id.instituteNameTextId);
        TextView t3 = view.findViewById(R.id.subNameTextId);
        TextView t4 = view.findViewById(R.id.yearTextId);
        TextView t5 = view.findViewById(R.id.classRangeTextId);
        TextView t6 = view.findViewById(R.id.honoraryRangeText);
        TextView t7 = view.findViewById(R.id.contactTextId);


        t1.setText("Name : "+upload2.getName());
        t2.setText("Institute Name : "+upload2.getInstituteName());
        t3.setText("Subject Name : "+upload2.getSubName());
        t4.setText("Class Range : "+upload2.getClassRange());
        t5.setText("Honorary Range : "+upload2.getHonoraryRange());
        t6.setText("Contact number : "+upload2.getContactNumber());



        return view;

    }
}
