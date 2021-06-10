package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUpEmailButtonId,termsAndConditionId,signInLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        signUpEmailButtonId=findViewById(R.id.signUpEmailId);
        termsAndConditionId=findViewById(R.id.termsAndConditionId);
        signInLoginButton= findViewById(R.id.signInLoginButtonId);



        signUpEmailButtonId.setOnClickListener(this);
        termsAndConditionId.setOnClickListener(this);
        signInLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signUpEmailId:
                Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
                break;

            case R.id.termsAndConditionId:

                break;

            case R.id.signInLoginButtonId:
                Intent intent1 = new Intent(getApplicationContext(),LoginUsingEmail.class);
                startActivity(intent1);

                break;
        }

    }
}
