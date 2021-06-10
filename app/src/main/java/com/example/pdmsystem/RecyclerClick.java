package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecyclerClick extends AppCompatActivity {

    DatabaseReference phoneRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_click);

        Intent intent =getIntent();
        final String phoneGetFromImageActivity= intent.getStringExtra("PhoneFromImageActivity");
      /*  String location= intent.getStringExtra("Location :");*/
        final TextView textView1= findViewById(R.id.recyclerTextId1);
        final TextView textView2= findViewById(R.id.recyclerTextId2);
        final TextView textView3= findViewById(R.id.recyclerTextId3);
        final TextView textView4= findViewById(R.id.recyclerTextId4);
        final TextView textView5= findViewById(R.id.recyclerTextId5);
        final TextView textView6= findViewById(R.id.recyclerTextId6);
        final TextView textView7= findViewById(R.id.recyclerTextId7);
        /*TextView textView2= findViewById(R.id.recyclerTextId2);*/
        //textView1.setText(name);
        /*textView2.setText(location);*/


        String uploadid=phoneRef.push().getKey();
        Toast.makeText(this, "Upload Id "+uploadid, Toast.LENGTH_LONG).show();
        phoneRef = FirebaseDatabase.getInstance().getReference().child("Upload");

        phoneRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                //for (final DataSnapshot mSnapShotSaving : dataSnapshot.getChildren())
                //{

                //String uploadid=phoneRef.push().getKey();
                Upload latitudeLongitudeDroDriverAvailableNode = dataSnapshot.getValue(Upload.class);

                /*if(latitudeLongitudeDroDriverAvailableNode.getPhoneNumber().equals(phoneGetFromImageActivity))
                {


                    String add = latitudeLongitudeDroDriverAvailableNode.getAddresses();
                    String email = latitudeLongitudeDroDriverAvailableNode.getEmail();
                    String imgName = latitudeLongitudeDroDriverAvailableNode.getImagename();
                    String imgUri = latitudeLongitudeDroDriverAvailableNode.getImageuri();
                    String loc = latitudeLongitudeDroDriverAvailableNode.getLocation();
                    String rent = latitudeLongitudeDroDriverAvailableNode.getRentAmount();

                    textView1.setText(add);
                    textView2.setText(email);
                    textView3.setText(imgName);
                    textView4.setText(imgUri);
                    textView5.setText(loc);
                    textView6.setText(rent);
                    //textView7.setText(add);



                }
                else
                {*/
                    Toast.makeText(RecyclerClick.this, ""+latitudeLongitudeDroDriverAvailableNode.getPhoneNumber(), Toast.LENGTH_LONG).show();
                //}






                // }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
