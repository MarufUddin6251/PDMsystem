package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PropertyCategory extends AppCompatActivity implements View.OnClickListener {

    private Button apartmentAndFlats,houses,plotsAndLand,commercialProperty,rooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_category);

        apartmentAndFlats = findViewById(R.id.apartmentAndFlatsId);
        houses = findViewById(R.id.housesId);
        plotsAndLand = findViewById(R.id.plotsAndLandId);
        commercialProperty = findViewById(R.id.commercialPropertyId);


        apartmentAndFlats.setOnClickListener(this);
        houses.setOnClickListener(this);
        plotsAndLand.setOnClickListener(this);
        commercialProperty.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.apartmentAndFlatsId:
                Intent intent = new Intent(getApplicationContext(),AdDetails.class);
                startActivity(intent);
                break;
            case R.id.housesId:
                    Intent intent1 = new Intent(getApplicationContext(),TutionMediaActivity.class);
                    startActivity(intent1);
                break;
            case R.id.plotsAndLandId:
                    Intent intent2 = new Intent(getApplicationContext(),AdDetails.class);
                    startActivity(intent2);
                break;
            case R.id.commercialPropertyId:
                    Intent intent3 = new Intent(getApplicationContext(),AdDetails.class);
                    startActivity(intent3);
                        break;
        }

    }
}
