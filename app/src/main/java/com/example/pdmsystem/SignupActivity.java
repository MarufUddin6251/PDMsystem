package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signupButton;
    private EditText emailSignUp,passwordSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        signupButton=findViewById(R.id.signUpButtonId);
        emailSignUp=findViewById(R.id.emailSignupEditTextId);
        passwordSignUp=findViewById(R.id.passwordSignupEditTextId);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.signinBtnId:

                break;
            case R.id.signUpButtonId:
                userRegister();
                

                 break;
        }

    }

    private void userRegister() {

        String email = emailSignUp.getText().toString().trim();
        String password = passwordSignUp.getText().toString().trim();


        if(email.isEmpty()){

            emailSignUp.setError("Enter email address");
            emailSignUp.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {

            emailSignUp.setError("Enter valid email address");
            emailSignUp.requestFocus();
            return;

        }
        if(password.isEmpty()){

            passwordSignUp.setError("Enter password");
            passwordSignUp.requestFocus();
            return;

        }
        if(password.length()<6){

            passwordSignUp.setError("Minimum password length is 6");
            passwordSignUp.requestFocus();
            return;

        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();

                    emailSignUp.setText("");
                    passwordSignUp.setText("");

                }
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }
}
