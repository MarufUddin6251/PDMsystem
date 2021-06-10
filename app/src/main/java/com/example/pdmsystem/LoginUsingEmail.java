package com.example.pdmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginUsingEmail extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmailEditText,loginPasswordEditText;
    private Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_using_email);

        loginEmailEditText = findViewById(R.id.logInEmailEditTextId);
        loginPasswordEditText = findViewById(R.id.logInPasswordEditTextId);

        logInButton = findViewById(R.id.logInId);

        logInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logInId:
                Intent intent= new Intent(getApplicationContext(),MajorFunctionalityActivity.class);
                startActivity(intent);

                break;
        }
    }
}
