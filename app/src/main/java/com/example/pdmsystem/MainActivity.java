package com.example.pdmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rellay1,rellay2;
    private Button signinbtn,signupbtn;
    private EditText emailLogin,passwordLogin;
    private FirebaseAuth mAuth;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
      rellay1.setVisibility(View.VISIBLE);
      rellay2.setVisibility(View.VISIBLE);

        }
    };
  /*  private Button postAdButton,mayBeLaterButton;*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);
        signinbtn = findViewById(R.id.signinBtnId);
        signupbtn = findViewById(R.id.signupBtnId);
        emailLogin = findViewById(R.id.emailLoginEditTextId);
        passwordLogin = findViewById(R.id.passwordLoginEditTextId);

        handler.postDelayed(runnable,2000);

        signinbtn.setOnClickListener(this);
        signupbtn.setOnClickListener(this);

   /*     postAdButton = findViewById(R.id.postAdId);
        mayBeLaterButton = findViewById(R.id.mayBeLaterId);



        postAdButton.setOnClickListener(this);
        mayBeLaterButton.setOnClickListener(this);
*/
    }
    //AIzaSyBR-gJKCUVrk2r9bK0j3YPFt1hbL6TuQnE

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.signinBtnId:
                userLogin();

                break;
                case R.id.signupBtnId:

                    Intent intent =new Intent(getApplicationContext(),SignupActivity.class);
                    startActivity(intent);
                break;
        }
    /*    switch(v.getId()) {
            case R.id.postAdId:
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                break;

            case R.id.mayBeLaterId:

                break;
                //AIzaSyBR-gJKCUVrk2r9bK0j3YPFt1hbL6TuQnE

        }*/
    }

    private void userLogin() {


        String email=emailLogin.getText().toString().trim();
        String password=passwordLogin.getText().toString().trim();


        if(email.isEmpty()){

            emailLogin.setError("Enter email address");
            passwordLogin.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {

            emailLogin.setError("Enter valid email address");
            emailLogin.requestFocus();
            return;

        }
        if(password.isEmpty()){

            passwordLogin.setError("Enter password");
            passwordLogin.requestFocus();
            return;

        }
        if(password.length()<6){

            passwordLogin.setError("Minimum password length is 6");
            passwordLogin.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {

                    Intent intent=new Intent(getApplicationContext(),MajorFunctionalityActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    emailLogin.setText("");
                    passwordLogin.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
