package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TutionFunction extends AppCompatActivity implements View.OnClickListener {

    private CardView tutorCard,studentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tution_function);

        tutorCard=findViewById(R.id.tutorCardViewid);
        studentCard=findViewById(R.id.studentCardViewid);

        tutorCard.setOnClickListener(this);
        studentCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.tutorCardViewid:
                Intent intent =new Intent(getApplicationContext(),TutionMediaActivity.class);
                startActivity(intent);
                break;
            case R.id.studentCardViewid:
                Intent intent1 =new Intent(getApplicationContext(),TutionAdList.class);
                startActivity(intent1);



                break;

        }

    }
}
