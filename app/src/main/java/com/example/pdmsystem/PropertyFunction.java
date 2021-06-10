package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PropertyFunction extends AppCompatActivity implements View.OnClickListener {
private CardView ownerCard,renterCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_function);

        ownerCard=findViewById(R.id.ownerCardView);
        renterCard=findViewById(R.id.renterCardView);

        ownerCard.setOnClickListener(this);
        renterCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ownerCardView:
                Intent intent =new Intent(getApplicationContext(),AdDetails.class);
                startActivity(intent);
                break;
            case R.id.renterCardView:
                Intent intent1 =new Intent(getApplicationContext(),ImageActivity.class);
                startActivity(intent1);



                break;

        }


    }
}
