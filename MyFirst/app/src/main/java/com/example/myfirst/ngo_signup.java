package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ngo_signup extends AppCompatActivity implements View.OnClickListener{

    private TextView ngo_signup, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button Signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_signup);

        ngo_signup = (TextView) findViewById(R.id.ngo_Signup);
        ngo_signup.setOnClickListener(this);

        Signin = (Button) findViewById(R.id.Signin);
        Signin.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.ngoEmail);
        editTextPassword = (EditText) findViewById(R.id.ngoPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.ForgotPassword);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ngo_Signup:
                startActivity(new Intent(ngo_signup.this, ngo_registration.class));
                break;

            case R.id.Signin:
                ngoLogin();
                break;

            case R.id.ForgotPassword: // id name
                startActivity(new Intent(this, ngoForgotPassword.class));
                break;
        }
    }

    private void ngoLogin() {
        final String ngoEmail = editTextEmail.getText().toString().trim();
        final String ngoPassword = editTextPassword.getText().toString().trim();

        if(ngoEmail.isEmpty()) {
            editTextEmail.setError("Email Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(ngoEmail).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(ngoPassword.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if(ngoPassword.length()<6) {
            editTextPassword.setError("Minimum Password Length should be 6 character");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(ngoEmail, ngoPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    FirebaseUser ngo = FirebaseAuth.getInstance().getCurrentUser();

                    if(ngo.isEmailVerified()) {
                        // Redirect to NGO profile
                        startActivity(new Intent(ngo_signup.this, ngoProfileActivity.class));
                        progressBar.setVisibility(View.GONE);
                    }else {
                        ngo.sendEmailVerification();
                        Toast.makeText(ngo_signup.this, "Check your Email to verify your account", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(ngo_signup.this, "Failed to Login, Check your email and password", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
    }
}