package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MajorFunctionalityActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView propertyCv,tutionCv,serviceCv;
    //private Button sellProperty,rentProperty,buyProperty,takeRentProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_functionality);


        propertyCv=findViewById(R.id.propertyCardViewId);
        tutionCv=findViewById(R.id.tutionCardViewID);
        serviceCv=findViewById(R.id.serviceCardViewId);

        propertyCv.setOnClickListener(this);
        tutionCv.setOnClickListener(this);
        serviceCv.setOnClickListener(this);
/*
        sellProperty = findViewById(R.id.sellPropertyId);
        rentProperty = findViewById(R.id.rentPropertyId);
        buyProperty = findViewById(R.id.buyPropertyId);
        takeRentProperty = findViewById(R.id.takeRentPropertyId);



        sellProperty.setOnClickListener(this);
        rentProperty.setOnClickListener(this);
        buyProperty.setOnClickListener(this);
        takeRentProperty.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.propertyCardViewId:
                Intent intent = new Intent(getApplicationContext(),PropertyFunction.class);
                startActivity(intent);

                break;
            case R.id.tutionCardViewID:
                Intent intent1 = new Intent(getApplicationContext(),TutionFunction.class);
                startActivity(intent1);

                break;
            case R.id.serviceCardViewId:
                Intent intent2 = new Intent(getApplicationContext(),ServiceFunction.class);
                startActivity(intent2);

                break;

        }

    }
}
