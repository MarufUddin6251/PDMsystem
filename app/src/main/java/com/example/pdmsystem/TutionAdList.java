package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutionAdList extends AppCompatActivity {

    private ListView listView;
    private List<Upload2>tutionAdList;
    private CustomAdapter customAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tution_ad_list);

        databaseReference = FirebaseDatabase.getInstance().getReference("Upload2");
        tutionAdList = new ArrayList<>();

        customAdapter = new CustomAdapter(TutionAdList.this,tutionAdList);


        listView = findViewById(R.id.tutionAdListViewId);

    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tutionAdList.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Upload2 upload2 = dataSnapshot1.getValue(Upload2.class);
                    tutionAdList.add(upload2);
                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
