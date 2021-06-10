package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutionMediaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button postAdButton1;
    private EditText nameEditText,instituteEditText,yearEditText,classRangeEditText,honoraryRangeEditText,contactEditText;
    private EditText subNameEditText;

    //DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tution_media);

        //databaseReference= FirebaseDatabase.getInstance().getReference("Upload2");


        postAdButton1 = findViewById(R.id.postAdId1);
        nameEditText = findViewById(R.id.nameEditText2Id);
        instituteEditText = findViewById(R.id.instituteNameEditTextId);
        subNameEditText = findViewById(R.id.subNameEditTextId);
        yearEditText = findViewById(R.id.yearEditTextId);
         classRangeEditText= findViewById(R.id.classRangeId);
         honoraryRangeEditText= findViewById(R.id.honoraryRangeId);
        contactEditText = findViewById(R.id.phoneNumberEditTextId);



        postAdButton1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.postAdId1:
                saveData();

                break;




        }

    }

    private void saveData() {

        String name = nameEditText.getText().toString().trim();
        String instituteName = instituteEditText.getText().toString().trim();
        String subName= subNameEditText.getText().toString().trim();
        String year = yearEditText.getText().toString().trim();
        String classRange= classRangeEditText.getText().toString().trim();

        String honoraryRange = honoraryRangeEditText.getText().toString().trim();
        String contactNumber = contactEditText.getText().toString().trim();


        //String key = databaseReference.push().getKey();

        Upload2 upload2= new Upload2(name,instituteName,subName,year,classRange,honoraryRange,contactNumber);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Upload2");
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(upload2);
        Toast.makeText(getApplicationContext(),"Advertisement is posted successfully",Toast.LENGTH_SHORT).show();



    }
}
